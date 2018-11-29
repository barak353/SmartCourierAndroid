package com.raghdak.wardm.smartcourier;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raghdak.wardm.smartcourier.SQL.DatabaseHelper;
import com.raghdak.wardm.smartcourier.model.Courier;
import com.raghdak.wardm.smartcourier.model.Shipment;
import com.raghdak.wardm.smartcourier.protocol.response.LoginResponse;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    ProgressDialog progressDialog;
    private EditText loginInputEmail, loginInputPassword;
    private Button btnlogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginInputEmail = (EditText) findViewById(R.id.login_input_email);
        loginInputPassword = (EditText) findViewById(R.id.login_input_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        //--------------------------------------------------------------
        databaseHelper = DatabaseHelper.getInstance(this);
        databaseHelper.addCourier(new Courier("test","1234",
               "Ward","Mohanna","Peqiin","+972546697971"));
        //--------------------------------------------------------------
        //--------------------------------------------------------------
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   if(isEmailValid(loginInputEmail.getText().toString())) {
                loginUser(loginInputEmail.getText().toString(),
                        loginInputPassword.getText().toString());
            /*    }
                else{
                    Log.d("Login","input isnt valid Email format");
                    Toast.makeText(getApplicationContext(),
                            "Invalid Email", Toast.LENGTH_LONG).show();
                }*/
            }
        });

    }

    private void loginUser( final String email, final String password) {
        // Tag used to cancel the request
        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();
        LoginResponse loginResponse = databaseHelper.checkUser(email,password);
        if (loginResponse.getText().equals("Login Successfull!")) {
            Courier courier = loginResponse.getCourier();
            // Launch User activity
            Intent intent = new Intent(
                    LoginActivity.this,
                    MainActivity.class);
            intent.putExtra("courier", courier);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(),
                    loginResponse.getText(), Toast.LENGTH_LONG).show();
            hideDialog();
        }
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
    public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

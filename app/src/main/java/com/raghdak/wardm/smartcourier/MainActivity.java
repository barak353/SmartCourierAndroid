package com.raghdak.wardm.smartcourier;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.raghdak.wardm.smartcourier.SQL.DatabaseHelper;
import com.raghdak.wardm.smartcourier.tools.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button btnLogOut;
    private Button btnNewShipment;
    private Button btnViewShipemtns;
    private Button btnViewUrgentShipments;
    private TextView allShipmentsTextView;
    private TextView deliveredShipmentsTextView;
    private DatabaseHelper databaseHelper;
    List<Address> addressesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        //btnNewShipment = (Button) findViewById(R.id.btnNewShipment);
        btnViewShipemtns = (Button) findViewById(R.id.btnViewShipments);
        btnViewUrgentShipments = (Button) findViewById(R.id.btnViewUrgentShipments);
        allShipmentsTextView = (TextView) findViewById(R.id.allShipmentsTextView);
        deliveredShipmentsTextView = (TextView) findViewById(R.id.deliveredShipmentsTextView);
        databaseHelper = DatabaseHelper.getInstance(this);
        allShipmentsTextView.setText("" + databaseHelper.countAllShipments());
        deliveredShipmentsTextView.setText("" + databaseHelper.countDeliveredShipments());
        //--------------------------------------------------------------------
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        //----------------------------------------------------------------
        /*btnNewShipment.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewShipmentActivity.class);
                startActivity(i);
                finish();
            }
        });*/

        //----------------------------------------------------------------
        btnViewShipemtns.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegionActivity.class);
                startActivity(i);
                finish();
            }
        });

        /*btnViewUrgentShipments.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(i);
                finish();
            }
        });*/
    }


}

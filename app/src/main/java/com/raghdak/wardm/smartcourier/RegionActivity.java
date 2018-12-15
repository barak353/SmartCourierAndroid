package com.raghdak.wardm.smartcourier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.raghdak.wardm.smartcourier.SQL.DatabaseHelper;
import com.raghdak.wardm.smartcourier.model.Region;
import com.raghdak.wardm.smartcourier.model.Shipment;
import com.raghdak.wardm.smartcourier.model.User;
import com.raghdak.wardm.smartcourier.protocol.response.RegionResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class RegionActivity extends AppCompatActivity {

    private static final String TAG = "RegionActivity";


    private Button btnBack;
    private Button btnViewShipments;
    private Spinner spinner;
    ArrayList<Region> regions = new ArrayList<Region>();
    ArrayList<String> regionsNames = new ArrayList<String>();
    private Context context = this;
    private DatabaseHelper databaseHelper;
    private ArrayList<Shipment> shipments;
    private ArrayList<String> subRegionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        spinner = (Spinner) findViewById(R.id.spnrRegion);
        //databaseHelper = DatabaseHelper.getInstance(this);
        User user = User.currentUser;
        RequestQueue queue = Volley.newRequestQueue(this); // this = context
        final String url = "http://10.0.2.2:8080/region/getRegions/" + user.getId();

        // prepare the Request
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {
                        //JSONArray jArray = (JSONArray)response;
                        if(response.length() > 0)
                            for(int countItem = 0; countItem < response.length(); countItem++)
                            {
                                try {
                                    JSONObject region = response.getJSONObject(countItem);
                                    Gson gson=new Gson();
                                    Region regionItem = gson.fromJson(region.toString(), Region.class);
                                    regions.add(regionItem);
                                    for(Region regionIt: regions)
                                        regionsNames.add(regionIt.getRegionName());
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, regionsNames);
                                    spinner.setAdapter(adapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);


        /*for(Region region: regions)
            regionsNames.add(region.getRegionName());
        spinner = (Spinner) findViewById(R.id.spnrRegion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, regionsNames);
        spinner.setAdapter(adapter);*/
        //subRegions = (Spinner) findViewById(R.id.spnrSubRegion);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnViewShipments = (Button) findViewById(R.id.btnGetTrack);
        /*(shipments = getRegionList(regions.getSelectedItem().toString());
        if (shipments == null) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.no_shipments_in_region), Toast.LENGTH_LONG).show();
        } else {
            //subRegionsList = new ArrayList<String>();
            //subRegionsList.add(getResources().getString(R.string.all_shipments));
            //for (Shipment shipment : shipments) {
            //    subRegionsList.add(shipment.getSubArea());
            //}
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, subRegionsList);
            subRegions.setAdapter(adapter);
        }*/
        //--------------------------------------------------------------------------------------
        btnBack.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               /* String selectedRegion = (String) parentView.getItemAtPosition(position);
                shipments = getRegionList(regions.getSelectedItem().toString());
                subRegionsList = new ArrayList<String>();
                subRegionsList.add( getResources().getString(R.string.all_shipments));
                if (shipments == null) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.no_shipments_in_region), Toast.LENGTH_LONG).show();
                } else {
                    for (Shipment shipment : shipments) {
                        if(!subRegionsList.contains(shipment.getSubArea()))
                            subRegionsList.add(shipment.getSubArea());
                    }
                }
                ArrayAdapter<String> updatedAdapter = new ArrayAdapter<String>(RegionActivity.this,
                        android.R.layout.simple_spinner_item, subRegionsList);
                subRegions.setAdapter(updatedAdapter);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //--------------------------------------------------------------------------------------
        btnViewShipments.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                /*shipments = getRegionList(regions.getSelectedItem().toString());
                if (shipments == null) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.no_shipments_in_region), Toast.LENGTH_LONG).show();
                }
                else {
                    if (subRegions.getSelectedItem() != getResources().getString(R.string.all_shipments)) {
                        for (Shipment shipment : shipments) {
                            if (!shipment.getSubArea().equals(subRegions.getSelectedItem())) {
                                shipments.remove(shipment);
                            }
                        }
                    }
                // Launch User activity
                Intent intent = new Intent(getApplicationContext(), ViewShipmentsActivity.class);
                intent.putExtra("shipments", shipments);
                startActivity(intent);
                finish();
                }*/
            }
        });

    }

    private ArrayList<Shipment> getRegionList(String region) {
        RegionResponse regionResponse = databaseHelper.getRegionShipments(region);
        if (regionResponse.getText().equals("OK")) {
            shipments = regionResponse.getShipments();
            return shipments;
        } else {
            //Toast.makeText(getApplicationContext(),
            //        regionResponse.getText(), Toast.LENGTH_LONG).show();
            return null;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}



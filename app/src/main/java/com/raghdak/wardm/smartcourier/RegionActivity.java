package com.raghdak.wardm.smartcourier;

import android.app.Activity;
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

import com.raghdak.wardm.smartcourier.SQL.DatabaseHelper;
import com.raghdak.wardm.smartcourier.model.Shipment;
import com.raghdak.wardm.smartcourier.protocol.response.RegionResponse;

import java.util.ArrayList;


public class RegionActivity extends AppCompatActivity {

    private static final String TAG = "RegionActivity";


    private Button btnBack;
    private Button btnViewShipments;
    private Spinner regions;
    private Spinner subRegions;
    private DatabaseHelper databaseHelper;
    private ArrayList<Shipment> shipments;
    private ArrayList<String> subRegionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        databaseHelper = DatabaseHelper.getInstance(this);
        regions = (Spinner) findViewById(R.id.spnrRegion);
        subRegions = (Spinner) findViewById(R.id.spnrSubRegion);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnViewShipments = (Button) findViewById(R.id.btnGetTrack);
        shipments = getRegionList(regions.getSelectedItem().toString());
        if (shipments == null) {
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.no_shipments_in_region), Toast.LENGTH_LONG).show();
        } else {
            subRegionsList = new ArrayList<String>();
            subRegionsList.add(getResources().getString(R.string.all_shipments));
            for (Shipment shipment : shipments) {
                subRegionsList.add(shipment.getSubArea());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, subRegionsList);
            subRegions.setAdapter(adapter);
        }
        //--------------------------------------------------------------------------------------
        btnBack.setOnClickListener(new View.OnClickListener() {
            //--------------------------------------------------------------------------------------
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        regions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedRegion = (String) parentView.getItemAtPosition(position);
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
                subRegions.setAdapter(updatedAdapter);
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
                shipments = getRegionList(regions.getSelectedItem().toString());
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
                }
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



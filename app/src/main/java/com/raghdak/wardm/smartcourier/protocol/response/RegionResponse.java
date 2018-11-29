package com.raghdak.wardm.smartcourier.protocol.response;

/**
 * Created by wardm on 23/11/2017.
 */


import com.raghdak.wardm.smartcourier.model.Shipment;

import java.util.ArrayList;


public class RegionResponse {
    String text;
    private ArrayList<Shipment> shipments;
    public RegionResponse(String text) {
        super();
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public ArrayList<Shipment> getShipments() {
        return shipments;
    }
    public void setShipments(ArrayList<Shipment> shipments) {
        this.shipments = shipments;
    }
    public static RegionResponse NO_Shipment() {
        return new RegionResponse("No shipments found!");
    }
    public static RegionResponse OK() {
        return new RegionResponse("OK");
    }

    @Override
    public String toString() {
        return "RegionResponse{" +
                "text='" + text + '\'' +
                ", shipments=" + shipments.toString() +
                '}';
    }
}

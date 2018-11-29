package com.raghdak.wardm.smartcourier.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wardm on 23/11/2017.
 */

public class Shipment implements Serializable {
    private String id;
    private double lat;
    private double lng;
    private String area;
    private String subArea;
    private String address;
    private String status;
    private String urgent;
    private String claimant;
    private String name;
    private String phone;
    private String box;
    private Date dueDate;
    private Date date;
    private String notFound;
    private String receiverName;
    private String floor;
    private String entrance;
    private String numOfFloors;
    private String privateHouse;
    private String signed;
    private String pastedOnDoor;
    private String text;
    private String courierID;
    private ArrayList<String> images_path;
    private ArrayList<String> images_text;

    public ArrayList<String> getImages_path() {
        return images_path;
    }

    public void setImages_path(ArrayList<String> images_path) {
        this.images_path = images_path;
    }

    public ArrayList<String> getImages_text() {
        return images_text;
    }

    public void setImages_text(ArrayList<String> images_text) {
        this.images_text = images_text;
    }

    public Shipment(String id, ArrayList<String> images_path, ArrayList<String> images_text) {
        this.id = id;
        this.images_path = images_path;
        this.images_text = images_text;
    }

    public Shipment(String id, String notFound, String receiverName, String floor, String entrance, String numOfFloors, String privateHouse, String signed, String pastedOnDoor) {
        this.id = id;
        this.notFound = notFound;
        this.receiverName = receiverName;
        this.floor = floor;
        this.entrance = entrance;
        this.numOfFloors = numOfFloors;
        this.privateHouse = privateHouse;
        this.signed = signed;
        this.pastedOnDoor = pastedOnDoor;
    }

    public Shipment(double lat, double lng, String area, String subArea, String address, String urgent, String claimant, String name, String phone, String box, Date dueDate, String courierID) {
        this.lat = lat;
        this.lng = lng;
        this.area = area;
        this.subArea = subArea;
        this.address = address;
        this.urgent = urgent;
        this.claimant = claimant;
        this.name = name;
        this.phone = phone;
        this.box = box;
        this.dueDate = dueDate;
        this.courierID = courierID;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", area='" + area + '\'' +
                ", area='" + subArea + '\'' +
                ", area='" + address + '\'' +
                ", status='" + status + '\'' +
                ", urgent='" + urgent + '\'' +
                ", claimant='" + claimant + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", box='" + box + '\'' +
                ", dueDate=" + dueDate +
                ", date=" + date +
                ", notFound='" + notFound + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", floor='" + floor + '\'' +
                ", entrance='" + entrance + '\'' +
                ", numOfFloors='" + numOfFloors + '\'' +
                ", privateHouse='" + privateHouse + '\'' +
                ", signed='" + signed + '\'' +
                ", pastedOnDoor='" + pastedOnDoor + '\'' +
                ", text='" + text + '\'' +
                ", courierID='" + courierID + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setCity(String subArea) {
        this.subArea = subArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotFound() {
        return notFound;
    }

    public void setNotFound(String notFound) {
        this.notFound = notFound;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(String numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public String getPrivateHouse() {
        return privateHouse;
    }

    public void setPrivateHouse(String privateHouse) {
        this.privateHouse = privateHouse;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }

    public String getPastedOnDoor() {
        return pastedOnDoor;
    }

    public void setPastedOnDoor(String pastedOnDoor) {
        this.pastedOnDoor = pastedOnDoor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCourierID() {
        return courierID;
    }

    public void setCourierID(String courierID) {
        this.courierID = courierID;
    }
}


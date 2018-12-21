package com.raghdak.wardm.smartcourier.model;


import java.io.Serializable;

/**
 * Created by wardm on 22/11/2017.
 */
public class Delivery implements Serializable {
    private Long id;
    private String name;
    private Integer isUrgent;
    private Double latitude;
    private Double longitude;
    private Integer type;

    public Delivery(Long id, String name, Integer isUrgent, Double latitude, Double longitude, Integer type) {
        super();
        this.id = id;
        this.name = name;
        this.isUrgent = isUrgent;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getIsUrgent() {
        return isUrgent;
    }
    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer longitude) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Delivery [id=" + id + ", name=" + name + ", isUrgent=" + isUrgent + ", latitude=" + latitude
                + ", longitude=" + longitude + ", type=" + type + "]";
    }

}


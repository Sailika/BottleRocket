package com.app.bottlerocket.mvvm.model;

import java.io.Serializable;

/**
 * Created by saili on 3/22/2018.
 */

public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private String storeLogoURL;

    private String phone;

    private String address;

    private String zipcode;

    private String name;

    private String state;

    private String longitude;

    private String storeID;

    private String latitude;

    private String city;


    public String getStoreLogoURL() {
        return storeLogoURL;
    }

    public void setStoreLogoURL(String storeLogoURL) {
        this.storeLogoURL = storeLogoURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ClassPojo [storeLogoURL = " + storeLogoURL + ", phone = " + phone + ", address = " + address + ", zipcode = " + zipcode + ", name = " + name + ", state = " + state + ", longitude = " + longitude + ", storeID = " + storeID + ", latitude = " + latitude + ", city = " + city + "]";
    }
}

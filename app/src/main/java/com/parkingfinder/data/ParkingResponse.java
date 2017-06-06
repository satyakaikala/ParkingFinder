package com.parkingfinder.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kaika on 6/5/2017.
 */

public class ParkingResponse {

    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;
    @SerializedName("locations")
    private int locations;
    @SerializedName("parking_listings")
    private ArrayList<ParkingList> parkingLists;

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

    public int getLocations() {
        return locations;
    }

    public void setLocations(int locations) {
        this.locations = locations;
    }

    public ArrayList<ParkingList> getParkingLists() {
        return parkingLists;
    }

    public void setParkingLists(ArrayList<ParkingList> parkingLists) {
        this.parkingLists = parkingLists;
    }
}

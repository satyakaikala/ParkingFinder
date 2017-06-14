package com.parkingfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kaika on 6/5/2017.
 * Class which hold information about list of Parking Location nearby.
 */
public class ParkingList implements Parcelable {

    @SerializedName("location_name")
    private String locationName;
    @SerializedName("location_id")
    private String locationId;
    @SerializedName("lat")
    private float lat;
    @SerializedName("lng")
    private float lng;
    @SerializedName("start")
    private long startTime;
    @SerializedName("end")
    private long endTime;
    @SerializedName("parkwhiz_url")
    private String parkwhiz_url;
    @SerializedName("api_url")
    private String api_url;
    @SerializedName("available_spots")
    private String available_spots;
    @SerializedName("price")
    private String price;

    protected ParkingList(Parcel in) {
        locationName = in.readString();
        lat = in.readFloat();
        lng = in.readFloat();
        locationId = in.readString();
        startTime = in.readLong();
        endTime = in.readLong();
        parkwhiz_url = in.readString();
        api_url = in.readString();
        available_spots = in.readString();
        price = in.readString();
    }

    public static final Creator<ParkingList> CREATOR = new Creator<ParkingList>() {
        @Override
        public ParkingList createFromParcel(Parcel in) {
            return new ParkingList(in);
        }

        @Override
        public ParkingList[] newArray(int size) {
            return new ParkingList[size];
        }
    };

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getParkwhiz_url() {
        return parkwhiz_url;
    }

    public void setParkwhiz_url(String parkwhiz_url) {
        this.parkwhiz_url = parkwhiz_url;
    }

    public String getApi_url() {
        return api_url;
    }

    public void setApi_url(String api_url) {
        this.api_url = api_url;
    }

    public String getAvailable_spots() {
        return available_spots;
    }

    public void setAvailable_spots(String available_spots) {
        this.available_spots = available_spots;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationName);
        dest.writeFloat(lat);
        dest.writeFloat(lng);
        dest.writeString(locationId);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeString(parkwhiz_url);
        dest.writeString(api_url);
        dest.writeString(available_spots);
        dest.writeString(price);
    }
}

package com.parkingfinder.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kaika on 6/5/2017.
 */

/**
 * Class which hold Selected Parking Location information
 */
public class ParkingLocation implements Parcelable{

    @SerializedName("location_name")
    private String locationName;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("zip")
    private String zip;
    @SerializedName("type")
    private String type;
    @SerializedName("timezone")
    private String time_zone;
    @SerializedName("directions")
    private String directions;
    @SerializedName("description")
    private String description;
    @SerializedName("recommendations")
    private String recommendations;
    @SerializedName("www_reserve_url")
    private String www_reserve_url;
    @SerializedName("api_reserve_url")
    private String api_reserve_url;
    @SerializedName("listing_id")
    private int listing_id;
    @SerializedName("price")
    private String price;
    @SerializedName("start_utc")
    private String start_utc;
    @SerializedName("end_utc")
    private String end_utc;
    @SerializedName("available_spots")
    private String available_spots;

    protected ParkingLocation(Parcel in) {
        locationName = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        zip = in.readString();
        type = in.readString();
        time_zone = in.readString();
        directions = in.readString();
        description = in.readString();
        recommendations = in.readString();
        www_reserve_url = in.readString();
        api_reserve_url = in.readString();
        listing_id = in.readInt();
        price = in.readString();
        start_utc = in.readString();
        end_utc = in.readString();
        available_spots = in.readString();
    }

    public static final Creator<ParkingLocation> CREATOR = new Creator<ParkingLocation>() {
        @Override
        public ParkingLocation createFromParcel(Parcel in) {
            return new ParkingLocation(in);
        }

        @Override
        public ParkingLocation[] newArray(int size) {
            return new ParkingLocation[size];
        }
    };

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getWww_reserve_url() {
        return www_reserve_url;
    }

    public void setWww_reserve_url(String www_reserve_url) {
        this.www_reserve_url = www_reserve_url;
    }

    public String getApi_reserve_url() {
        return api_reserve_url;
    }

    public void setApi_reserve_url(String api_reserve_url) {
        this.api_reserve_url = api_reserve_url;
    }

    public int getListing_id() {
        return listing_id;
    }

    public void setListing_id(int listing_id) {
        this.listing_id = listing_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart_utc() {
        return start_utc;
    }

    public void setStart_utc(String start_utc) {
        this.start_utc = start_utc;
    }

    public String getEnd_utc() {
        return end_utc;
    }

    public void setEnd_utc(String end_utc) {
        this.end_utc = end_utc;
    }

    public String getAvailable_spots() {
        return available_spots;
    }

    public void setAvailable_spots(String available_spots) {
        this.available_spots = available_spots;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationName);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(zip);
        dest.writeString(type);
        dest.writeString(time_zone);
        dest.writeString(directions);
        dest.writeString(description);
        dest.writeString(recommendations);
        dest.writeString(www_reserve_url);
        dest.writeString(api_reserve_url);
        dest.writeInt(listing_id);
        dest.writeString(price);
        dest.writeString(start_utc);
        dest.writeString(end_utc);
        dest.writeString(available_spots);
    }
}

package com.parkingfinder.network;

import com.parkingfinder.data.ParkingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kaika on 6/6/2017.
 */

public interface ParkingFetchInterface {

//    http://api.parkwhiz.com/search/?lat=41.8857256&lng=-87.6369590&start=1496768525&end=1496779325&key=

    @GET("search")
    Call<ParkingResponse> getParkingResponse(@Query("lat") String lat, @Query("lng") String lng, @Query("start") String startTime, @Query("end") String endTime, @Query("key") String key);

}

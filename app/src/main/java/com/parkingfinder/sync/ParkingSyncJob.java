package com.parkingfinder.sync;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.parkingfinder.data.ParkingList;
import com.parkingfinder.data.ParkingResponse;
import com.parkingfinder.network.NetworkClient;
import com.parkingfinder.network.ParkingFetchInterface;
import com.parkingfinder.ui.MapsActivity;
import com.parkingfinder.ui.ResponseHandler;
import com.parkingfinder.ui.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.parkingfinder.ui.Utility.APIKEY;
import static com.parkingfinder.ui.Utility.END_TIME_KEY;
import static com.parkingfinder.ui.Utility.LAT_KEY;
import static com.parkingfinder.ui.Utility.LNG_KEY;
import static com.parkingfinder.ui.Utility.START_TIME_KEY;

/**
 * Created by kaika on 6/6/2017.
 */

public class ParkingSyncJob {

    private static final String TAG = ParkingSyncJob.class.getSimpleName();
    public static final String ACTION_DATA_UPDATED = "com.parkingfinder.ACTION_DATA_UPDATED";
    private static final int INITIAL_BACKOFF = 10000;
    private static final int ONE_OFF_ID = 1;
    private static ParkingFetchInterface parkingFetchInterface;
    private static ArrayList<ParkingList> parkingLists;
    private static ResponseHandler responseHandler;


    static void getParkingSearchResponse(final Context context) {
        responseHandler = new MapsActivity();
        Log.d(TAG, "Making service call in getParkingSearchResponse");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        float lat = preferences.getFloat(LAT_KEY, 0);
        float lng = preferences.getFloat(LNG_KEY, 0);
        long startTime = preferences.getLong(START_TIME_KEY, 0L);
        long endTime = preferences.getLong(END_TIME_KEY, 0L);
        parkingFetchInterface = NetworkClient.getRetrofit().create(ParkingFetchInterface.class);
        Call<ParkingResponse> call = parkingFetchInterface.getParkingResponse((float) 33.757162, (float)-84.392797, startTime, endTime, APIKEY);
        call.enqueue(new Callback<ParkingResponse>() {
            @Override
            public void onResponse(Call<ParkingResponse> call, Response<ParkingResponse> response) {
                parkingLists = response.body().getParkingLists();
                Log.d(TAG, "Parking response : " + parkingLists);
                responseHandler.updateParkingList(parkingLists);
            }

            @Override
            public void onFailure(Call<ParkingResponse> call, Throwable t) {
                Toast.makeText(context, "No parking lots found nearby. Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void getParkingList(Context context) {
        if (Utility.isOnline(context)) {
            Intent intent = new Intent(context, ParkingIntentService.class);
            context.startService(intent);
        } else {
            JobInfo.Builder builder = new JobInfo.Builder(ONE_OFF_ID, new ComponentName(context, ParkingJobService.class));
            builder.setBackoffCriteria(INITIAL_BACKOFF, JobInfo.BACKOFF_POLICY_EXPONENTIAL);

            JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            scheduler.schedule(builder.build());
        }
    }
}

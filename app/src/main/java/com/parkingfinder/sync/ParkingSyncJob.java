package com.parkingfinder.sync;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.parkingfinder.data.ParkingList;
import com.parkingfinder.data.ParkingResponse;
import com.parkingfinder.network.NetworkClient;
import com.parkingfinder.network.ParkingFetchInterface;
import com.parkingfinder.ui.Utility;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.parkingfinder.ui.Utility.APIKEY;

/**
 * Created by kaika on 6/6/2017.
 */

public class ParkingSyncJob {

    public static final String ACTION_DATA_UPDATED = "com.parkingfinder.ACTION_DATA_UPDATED";
    private static final int INITIAL_BACKOFF = 10000;
    private static final int ONE_OFF_ID = 1;
    private static ParkingFetchInterface parkingFetchInterface;
    public static ArrayList<ParkingList> parkingLists;
    private ParkingSyncJob() {
        parkingFetchInterface = NetworkClient.getRetrofit().create(ParkingFetchInterface.class);
    }

    static void getParkingSearchResponse(final Context context) {
        Call<ParkingResponse> call = parkingFetchInterface.getParkingResponse("", "", "", "", APIKEY);
        call.enqueue(new Callback<ParkingResponse>() {
            @Override
            public void onResponse(Call<ParkingResponse> call, Response<ParkingResponse> response) {
                parkingLists = response.body().getParkingLists();
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

            JobScheduler scheduler = (JobScheduler) context.getSystemService(context.JOB_SCHEDULER_SERVICE);
            scheduler.schedule(builder.build());
        }
    }
}

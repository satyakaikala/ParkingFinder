package com.parkingfinder.sync;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by kaika on 6/6/2017.
 */

public class ParkingJobService extends JobService {

    private static final String TAG = ParkingJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Intent Handled");
        Intent intent = new Intent(getApplicationContext(), ParkingIntentService.class);
        getApplicationContext().startService(intent);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

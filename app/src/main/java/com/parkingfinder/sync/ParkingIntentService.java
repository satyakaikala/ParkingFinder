package com.parkingfinder.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by kaika on 6/6/2017.
 */

public class ParkingIntentService extends IntentService {
    private static final String TAG = ParkingIntentService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ParkingIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "ParkingIntentService called onHandleIntent");
        ParkingSyncJob.getParkingSearchResponse(getApplicationContext());
    }
}

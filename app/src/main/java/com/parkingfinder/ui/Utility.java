package com.parkingfinder.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.parkingfinder.BuildConfig;

/**
 * Created by kaika on 6/6/2017.
 */

public class Utility {

    public static final String APIKEY = BuildConfig.API_KEY;
    public static final String BASE_URL = "https://api.parkwhiz.com";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

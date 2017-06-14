package com.parkingfinder.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import com.parkingfinder.BuildConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kaika on 6/6/2017.
 */

public class Utility {

    public static final String APIKEY = BuildConfig.API_KEY;
    public static final String BASE_URL = "https://api.parkwhiz.com/";
    public static final String LAT_KEY = "lat_key";
    public static final String LNG_KEY = "lng_key";
    public static final String START_TIME_KEY = "start_time_key";
    public static final String END_TIME_KEY = "end_time_key";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    static void saveLatLngprefs(Context context, float lat, float lng) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(LAT_KEY, lat);
        editor.putFloat(LNG_KEY, lng);
        editor.apply();
    }

    public static void saveTimePrefs(Context context, long startTime, long endTime) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(START_TIME_KEY, startTime);
        editor.putLong(END_TIME_KEY, endTime);
        editor.apply();
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static long getFromTimeAndDateInLong(String date, String time) {
        String dateAndTime = date + " " + time;
//        "2009-07-20 05:33";
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy hh:mm");
        Date dt = null;
        try {
            dt = df.parse(dateAndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long l = 0L;
        if (dt != null) {
            l = dt.getTime();
        }
        return l/1000;
    }
}

package com.parkingfinder.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.parkingfinder.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaika on 6/6/2017.
 */

public class SearchParkingDialog extends DialogFragment {

    private static final String TAG = SearchParkingDialog.class.getSimpleName();
    private String selectedFromDate, selectedFromTime, selectedToDate, selectedToTime;
    @BindView(R.id.from_date)
    Button fromDate;
    @BindView(R.id.from_time)
    Button fromTime;
    @BindView(R.id.to_date)
    Button toDate;
    @BindView(R.id.to_time)
    Button toTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "SearchParkingDialog called");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams") View custom = inflater.inflate(R.layout.search_parking_dialog, null);

        ButterKnife.bind(this, custom);

        builder.setView(custom);

        builder.setMessage(R.string.dialog_title);
        builder.setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                searchParkingLots();
            }
        });
        builder.setNegativeButton(R.string.dialog_negative_button, null);
        Dialog dialog = builder.create();

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFromDateSelected();
            }
        });

        fromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFromTimeSelected();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleToDateSelected();
            }
        });

        toTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleToTimeSelected();
            }
        });

        return dialog;
    }

    private void searchParkingLots() {
        Activity parent = getActivity();
        if (parent instanceof MapsActivity) {
            saveSelectedTimes();
            Log.d(TAG, "searchParkingLots starting search");
            ((MapsActivity) parent).searchParkingLots();
        }
        dismissAllowingStateLoss();
    }

    private void saveSelectedTimes() {
        long startTime = Utility.getFromTimeAndDateInLong(selectedFromDate, selectedFromTime);
        long endTime = Utility.getFromTimeAndDateInLong(selectedToDate, selectedToTime);
        Utility.saveTimePrefs(getActivity(), startTime, endTime);
        Log.d(TAG, "save time prefs: START TIME : " + startTime + " END TIME : " + endTime);
    }

    private void handleFromDateSelected() {
        int year = Utility.getCalendar().get(Calendar.YEAR);
        int month = Utility.getCalendar().get(Calendar.MONTH);
        int day = Utility.getCalendar().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                yyyy-MM-dd hh-mm
                selectedFromDate =  (month +1) + "-" + dayOfMonth + "-" + year;
                fromDate.setText(selectedFromDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void handleFromTimeSelected() {
        int hour = Utility.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minute = Utility.getCalendar().get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedFromTime = hourOfDay + ":" + minute;
                fromTime.setText(selectedFromTime);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    private void handleToDateSelected() {
        int year = Utility.getCalendar().get(Calendar.YEAR);
        int month = Utility.getCalendar().get(Calendar.MONTH);
        int day = Utility.getCalendar().get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                MM-dd-yyyy hh-mm
                selectedToDate = (month +1) + "-" + dayOfMonth + "-" + year;
                toDate.setText(selectedToDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void handleToTimeSelected() {
        int hour = Utility.getCalendar().get(Calendar.HOUR_OF_DAY);
        int minute = Utility.getCalendar().get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectedToTime = hourOfDay + ":" + minute;
                toTime.setText(selectedToTime);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}

package com.parkingfinder.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.parkingfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaika on 6/6/2017.
 */

public class SearchParkingDialog extends DialogFragment {

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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams")View custom = inflater.inflate(R.layout.search_parking_dialog, null);

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
            ((MapsActivity) parent).searchParkingLots();
        }
        dismissAllowingStateLoss();
    }

    private void handleFromDateSelected() {

    }

    private void handleFromTimeSelected() {
    }

    private void handleToDateSelected() {
    }

    private void handleToTimeSelected() {
    }
}

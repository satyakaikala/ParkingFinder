package com.parkingfinder.ui;

import com.parkingfinder.data.ParkingList;

import java.util.ArrayList;

/**
 * Created by kaika on 6/7/2017.
 */

public interface ResponseHandler {
    void updateParkingList(ArrayList<ParkingList> parkingLists);
}

package com.twinvaves.darshan.zoomcar;

/**
 * Created by darshan on 12/09/15.
 */
public class Data {

    String image;
    String type;
    String rate_hour;
    String rating;
    String seater;
    String ac;
    String lat,lon;
    String name;

    int carRating;
    int seatNumber;
    boolean AC;

    public void convertData() {
        carRating = Integer.parseInt(rating);
        seatNumber = Integer.parseInt(seater);
        if (ac.equals("0"))
            AC = false;
        else AC = true;
    }

}

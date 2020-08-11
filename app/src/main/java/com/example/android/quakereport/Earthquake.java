package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliSeconds;
    private String mUrl;

    public Earthquake(double magnitude,String location,long date,String Url){
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliSeconds = date;
        mUrl = Url;
    }

    public double getMagnitude(){
        return mMagnitude;
    }
    public String getLocation(){
        return mLocation;
    }
    public long getTimeInMilliSeconds(){
        return mTimeInMilliSeconds;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl(){
        return mUrl;
    }
}

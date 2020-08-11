package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View listItemView = convertView;
       if(listItemView == null){
           listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);

       }

       Earthquake currentEarthquake = getItem(position);



       TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
       //now to format the magnitude to include only 1 decimal place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
       magnitudeView.setText(formattedMagnitude);
       // Set the proper background color on the magnitude circle.
       // Fetch the background from the TextView, which is a GradientDrawable.
        // gradientdrawable3 is used to gain the background of the view
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        


        //location to be split into two parts one for offset and another for primary
        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation;
        String locationOffSet;

        //check if the location contains the offset or the separator value of "off" if not there user "near by"

        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffSet = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];

        }else {
            locationOffSet = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

       TextView primarylocationView = (TextView) listItemView.findViewById(R.id.primary_location);
       primarylocationView.setText(primaryLocation);

       TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
       locationOffsetView.setText(locationOffSet);



        Date dateObject = new Date(currentEarthquake.getTimeInMilliSeconds());

       TextView dateView = (TextView) listItemView.findViewById(R.id.date);
       String formattedDate = formatDate(dateObject);
       dateView.setText(formattedDate);

       TextView timeView = (TextView) listItemView.findViewById(R.id.time);

       String formattedTime = formatTime(dateObject);

       timeView.setText(formattedTime);

       return listItemView;

    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3 ;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
        
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
         return decimalFormat.format(magnitude);
        
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(dateObject);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}

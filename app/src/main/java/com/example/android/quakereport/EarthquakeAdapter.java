package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> list){
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if (listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);

        }

        Earthquake currentItem = getItem(position);
        //////////////////////////////////////////////////////////
        DecimalFormat format = new DecimalFormat("0.0");
        String magnatude = format.format(currentItem.getLevel());

        TextView levelTextView = (TextView)listView.findViewById(R.id.quake_level);

        GradientDrawable magnitudeCircle = (GradientDrawable)levelTextView.getBackground();
        int magnitudeColor = getMagnatudeColor(currentItem.getLevel());
        magnitudeCircle.setColor(magnitudeColor);
        levelTextView.setText(magnatude);
        ////////////////////////////////////////////////////////////////
        String originalLocation = currentItem.getLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }
        else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView locationOffsetTextView = (TextView)listView.findViewById(R.id.quake_location_offset);
        locationOffsetTextView.setText(locationOffset);

        TextView locationTextView = (TextView)listView.findViewById(R.id.quake_location);
        locationTextView.setText(primaryLocation);

        Date date = new Date(currentItem.getDate());

        TextView dateTextView = (TextView)listView.findViewById(R.id.quake_date);
        String formatDate = formateDate(date);
        dateTextView.setText(formatDate);


        TextView timeTextView = (TextView)listView.findViewById(R.id.quake_time);
        String formateTime = formateTime(date);
        timeTextView.setText(formateTime);

        return listView;
    }

    private int getMagnatudeColor(double magnitudeLavel){
        int color = 0;
        int magnitudeLavelInt = (int)Math.floor(magnitudeLavel);
        switch (magnitudeLavelInt){
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
            case 3:
                color = R.color.magnitude3;
                break;
            case 4:
                color = R.color.magnitude4;
                break;
            case 5:
                color = R.color.magnitude5;
                break;
            case 6:
                color = R.color.magnitude6;
                break;
            case 7:
                color = R.color.magnitude7;
                break;
            case 8:
                color = R.color.magnitude8;
                break;
            case 9:
                color = R.color.magnitude9;
                break;
            case 10:
                color = R.color.magnitude10Plus;
                break;
            default:
                color = R.color.magnitude10Plus;
                break;
        }
        return ContextCompat.getColor(getContext(), color);
    }

    private String formateDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    private String formateTime(Date date){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }
}

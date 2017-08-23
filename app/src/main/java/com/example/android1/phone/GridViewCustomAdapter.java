package com.example.android1.phone;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Android1 on 7/21/2017.
 */


public class GridViewCustomAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    int[] androidColors;
    Context context;
    customTextViewListener customListner;
    private ArrayList<FavouriteModelClass> favouriteModelClasses;

    public GridViewCustomAdapter(FragmentActivity mainActivity, ArrayList<FavouriteModelClass> favouriteModelClasses) {
        // TODO Auto-generated constructor stub

        context = mainActivity;

        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.favouriteModelClasses = favouriteModelClasses;
        androidColors = context.getResources().getIntArray(R.array.androidcolors);

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return favouriteModelClasses.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View rowView;

        rowView = inflater.inflate(R.layout.favourite_gridview_layout, null);

        TextView namealpa = (TextView) rowView.findViewById(R.id.nameaplha);
        TextView name = (TextView) rowView.findViewById(R.id.name);


        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        namealpa.setBackgroundColor(randomAndroidColor);
        namealpa.setText(favouriteModelClasses.get(position).getName_().charAt(0) + " ");
        name.setText(favouriteModelClasses.get(position).getName_());

        final String temp = favouriteModelClasses.get(position).getPhone_();

        namealpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onTextviewClickListner(position, temp);
                }
            }
        });

        return rowView;
    }

    public void onTextviewClickListner(customTextViewListener listener) {
        this.customListner = listener;
    }

    public interface customTextViewListener {
        void onTextviewClickListner(int position, String value);
    }
}
package com.example.android1.phone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Android1 on 7/5/2017.
 */

public class HistoryListAdapter extends ArrayAdapter<ContactList> {
    private final Context context;
    private final ArrayList<ContactList> itemsArrayList;
    customButtonListener customListner;
    String TAG = "Contact";
    int[] androidColors;

    public HistoryListAdapter(Context context, ArrayList<ContactList> itemsArrayList) {
        super(context, R.layout.historylistitem, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
        androidColors = context.getResources().getIntArray(R.array.androidcolors);

    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.historylistitem, parent, false);

//        int randomAndroidColorfirstname = androidColors[new Random().nextInt(androidColors.length)];

        TextView firstname = (TextView) rowView.findViewById(R.id.firstname);
//        Log.d(TAG, itemsArrayList.get(position).getCallerName().charAt(0) + "  jfj");
        firstname.setText(itemsArrayList.get(position).getCallerName().toUpperCase().charAt(0) + " ");


        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

        GradientDrawable bgShape = (GradientDrawable) firstname.getBackground();
        bgShape.setColor(randomAndroidColor);

        TextView callername = (TextView) rowView.findViewById(R.id.callername);
        callername.setText(itemsArrayList.get(position).getCallerName());

        TextView callerphone = (TextView) rowView.findViewById(R.id.callerphone);
        callerphone.setText(itemsArrayList.get(position).getCallerPhone());

        final String temp = itemsArrayList.get(position).callerPhone;
        ImageView phone = (ImageView) rowView.findViewById(R.id.phone);


        ImageView calltype = (ImageView) rowView.findViewById(R.id.calltype);

        if (itemsArrayList.get(position).callerType.equals("MISSED")) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_call_missed_black_18dp);
            calltype.setImageBitmap(bitmap);
        } else if (itemsArrayList.get(position).callerType.equals("INCOMING")) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_call_received_black_18dp);
            calltype.setImageBitmap(bitmap);
        } else if (itemsArrayList.get(position).callerType.equals("OUTGOING")) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_call_made_black_18dp);
            calltype.setImageBitmap(bitmap);
        }


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(position, temp);
                }
            }
        });

        return rowView;
    }

    public interface customButtonListener {
        void onButtonClickListner(int position, String value);
    }
}
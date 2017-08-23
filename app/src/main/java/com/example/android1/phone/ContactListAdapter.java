package com.example.android1.phone;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Android1 on 7/5/2017.
 */

public class ContactListAdapter extends ArrayAdapter<ContactList> implements Filterable {
    private final Context context;
    String TAG = "Contact";

    private ArrayList<ContactList> itemsArrayList;

    public ContactListAdapter(Context context, ArrayList<ContactList> itemsArrayList) {
        super(context, R.layout.contactlistitem, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.contactlistitem, parent, false);


        TextView firstname = (TextView) rowView.findViewById(R.id.firstname);
        Log.d(TAG, itemsArrayList.get(position).getCallerName().charAt(0) + "  jfj");
        firstname.setText(itemsArrayList.get(position).getCallerName().toUpperCase().charAt(0) + " ");


        TextView callername = (TextView) rowView.findViewById(R.id.callername);
        callername.setText(itemsArrayList.get(position).getCallerName());

        TextView callerphone = (TextView) rowView.findViewById(R.id.callerphone);
        callerphone.setText(itemsArrayList.get(position).getCallerPhone());

        return rowView;
    }


}
package com.example.android1.phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class History extends Fragment implements HistoryListAdapter.customButtonListener {


    static ArrayList<ContactList> historylist = new ArrayList<ContactList>();
    String TAG = "History";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);


        listView.addHeaderView(new View(getActivity()));
        listView.addFooterView(new View(getActivity()));

        HistoryListAdapter adapter = new HistoryListAdapter(getActivity(), historylist);

        listView.setAdapter(adapter);
        adapter.setCustomButtonListner(this);
        return view;
    }


    @Override
    public void onButtonClickListner(int position, String value) {
        Toast.makeText(getActivity(), "click", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + value));
        startActivity(intent);
    }
}

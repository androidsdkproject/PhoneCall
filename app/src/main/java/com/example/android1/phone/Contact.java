package com.example.android1.phone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Contact extends Fragment {


    static ListView list;
    static ArrayList<ContactList> contactlist = new ArrayList<ContactList>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        list = (ListView) view.findViewById(R.id.listView);

        list.addHeaderView(new View(getActivity()));
        list.addFooterView(new View(getActivity()));

        list.setTextFilterEnabled(true);
        ContactListAdapter adapter = new ContactListAdapter(getActivity(), contactlist);
        list.setAdapter(adapter);
        return view;
    }


}

package com.example.android1.phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.android1.phone.SplashScreen.updatefavouriteModelClass;


public class Favourite extends Fragment implements GridViewCustomAdapter.customTextViewListener {


    GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourite, container, false);


        gv = (GridView) view.findViewById(R.id.grid_view);
        GridViewCustomAdapter adapter = new GridViewCustomAdapter(getActivity(), clearListFromDuplicateName(updatefavouriteModelClass));

        gv.setAdapter(adapter);

        adapter.onTextviewClickListner(this);

        return view;


    }


    private ArrayList<FavouriteModelClass> clearListFromDuplicateName(ArrayList<FavouriteModelClass> list1) {

        Map<String, FavouriteModelClass> cleanMap = new LinkedHashMap<String, FavouriteModelClass>();
        for (int i = 0; i < list1.size(); i++) {
            cleanMap.put(list1.get(i).getName_(), list1.get(i));
        }
        ArrayList<FavouriteModelClass> list = new ArrayList<FavouriteModelClass>(cleanMap.values());
        return list;
    }

    @Override
    public void onTextviewClickListner(int position, String value) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + value));
        startActivity(intent);
    }
}


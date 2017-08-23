package com.example.android1.phone;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SplashScreen extends Activity {
    private static final int PERMISSION_ALL = 0;
    static ArrayList<FavouriteModelClass> updatefavouriteModelClass;
    String[] PERMISSIONS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_CALL_LOG,
            android.Manifest.permission.INTERNET,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.CALL_PHONE,

    };
    private Handler h;
    private Runnable r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new LongOperation().execute("");

        r = new Runnable() {
            @Override
            public void run() {


                finish();
                startActivity(new Intent(SplashScreen.this, MainActivity.class));

            }
        };


        if (!hasPermissions(SplashScreen.this, PERMISSIONS)) {

            ActivityCompat.requestPermissions(SplashScreen.this, PERMISSIONS, PERMISSION_ALL);

        } else {

            h.postDelayed(r,5000);


        }
    }

    // Put the below OnRequestPermissionsResult code here

    private ArrayList<FavouriteModelClass> clearListFromDuplicateName(ArrayList<FavouriteModelClass> list1) {

        Map<String, FavouriteModelClass> cleanMap = new LinkedHashMap<String, FavouriteModelClass>();
        for (int i = 0; i < list1.size(); i++) {
            cleanMap.put(list1.get(i).getName_(), list1.get(i));
        }
        ArrayList<FavouriteModelClass> list = new ArrayList<FavouriteModelClass>(cleanMap.values());
        return list;
    }

    public boolean hasPermissions(Context context, String... permissions) {


        if (android.os.Build.VERSION.SDK_INT >= 21 && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private String getCallDetails() {

        StringBuffer sb = new StringBuffer();
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {


        }
        Cursor managedCursor = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);

        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);

        sb.append("Call Details :");
        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            String callername = managedCursor.getString(name);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);


            String dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }

            if (callername == null)
                callername = "Unknown";

            if (phNumber != null)
                History.historylist.add(new ContactList(callername, phNumber, callername, callDuration, dir));
            sb.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                    + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration
                    + "\n Caller Name :----" + callername);
            sb.append("\n----------------------------------");

            updatefavouriteModelClass.add(new FavouriteModelClass(callername, phNumber));

        }
        managedCursor.close();
        return sb.toString();

    }

    public void getContact() {
        ContentResolver cr = getApplicationContext().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));


                        Contact.contactlist.add(new ContactList(name, phoneNo));
                    }
                    pCur.close();
                }
            }
        }
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.d("contact", getCallDetails());
            getContact();

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            updatefavouriteModelClass = clearListFromDuplicateName(updatefavouriteModelClass);
        }

        @Override
        protected void onPreExecute() {


            updatefavouriteModelClass = new ArrayList<>();
            h = new Handler();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}


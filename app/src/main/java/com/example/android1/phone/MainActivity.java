package com.example.android1.phone;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {


    final public static int PERMISSION_ALL = 1;

    SearchView mSearchView;
    TabLayout tabLayout;
    ViewPager viewPager;


    String TAG = "Start";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int count = 0;


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        mSearchView = (SearchView) toolbar.findViewById(R.id.searchView);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);


        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_star_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_history_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_people_white_24dp);

        tabLayout.setOnTabSelectedListener(this);

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
//        Toast.makeText(getApplicationContext(), "unselect", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
//        Toast.makeText(getApplicationContext(), "select", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.callhistory:
                Toast.makeText(getApplicationContext(), "Call History", Toast.LENGTH_LONG).show();
                return true;
            case R.id.clear:
                Toast.makeText(getApplicationContext(), "Clear", Toast.LENGTH_LONG).show();
                return true;
            case R.id.newcontact:
                Toast.makeText(getApplicationContext(), "New Contact", Toast.LENGTH_LONG).show();
                return true;
            case R.id.blacklist:
                Toast.makeText(getApplicationContext(), "BlackList", Toast.LENGTH_LONG).show();
                return true;
            case R.id.settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}







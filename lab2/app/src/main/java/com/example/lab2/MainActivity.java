package com.example.lab2;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends FragmentActivity
        implements GroupListFragment.OnGroupListSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("deee", "were onCreate in main");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_about_stuff);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            Log.d("deee", "firstfragment");
            GroupListFragment firstFragment = new GroupListFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onObjectSelected(int position) {
        InfoFragment infoFrag = (InfoFragment)
                getSupportFragmentManager().findFragmentById(R.id.info_fragment);
        if (infoFrag != null) {
            infoFrag.updateInfoView(position);
        }
        else {
            InfoFragment newFragment = new InfoFragment();
            Bundle args = new Bundle();
            args.putInt(InfoFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}

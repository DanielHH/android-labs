package com.example.lab3;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends FragmentActivity
        implements GroupListFragment.OnGroupListSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_about_stuff);
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            GroupListFragment firstFragment = new GroupListFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void onObjectSelected(int position, String groupName) {
        InfoFragment infoFrag = (InfoFragment)
                getSupportFragmentManager().findFragmentById(R.id.info_fragment);
        if (infoFrag != null && getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            infoFrag.updateInfoView(position, groupName);
        }
        else {
            InfoFragment newFragment = new InfoFragment();
            Bundle args = new Bundle();
            args.putInt(InfoFragment.ARG_POSITION, position);
            args.putString(InfoFragment.ARG_NAME, groupName);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}

package com.example.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GroupListFragment extends android.support.v4.app.ListFragment {
    OnGroupListSelectedListener mCallback;

    public interface OnGroupListSelectedListener {
        public void onObjectSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> list_of_objects = new ArrayList<String>();
        list_of_objects.add("Sweden");
        list_of_objects.add("Norway");
        list_of_objects.add("Squarepants");
        list_of_objects.add("GreatStuff");
        ArrayAdapter<String> objects = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_of_objects);
        setListAdapter(objects);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.grouplist_view, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnGroupListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnGroupListSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCallback.onObjectSelected(position);

        getListView().setItemChecked(position, true);
    }
}

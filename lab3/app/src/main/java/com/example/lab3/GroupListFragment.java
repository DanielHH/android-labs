package com.example.lab3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupListFragment extends android.support.v4.app.ListFragment {
    OnGroupListSelectedListener mCallback;

    ArrayList<String> list_of_objects = new ArrayList<String>();

    public interface OnGroupListSelectedListener {
        void onObjectSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final String url = "https://tddd80server.herokuapp.com/grupper";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("grupper");
                            for (int i = 0; i < results.length(); i++) {
                                list_of_objects.add(results.get(i).toString());
                                ArrayAdapter<String> objects = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list_of_objects);
                                setListAdapter(objects);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("dee: ", error.toString());
                    }
                });
        queue.add(jsonObjectRequest);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.grouplist_view, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnGroupListSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
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

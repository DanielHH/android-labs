package com.example.lab3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class InfoFragment extends Fragment {
    final static String ARG_POSITION = "position";
    final static String ARG_NAME = "groupName";
    int mCurrentPosition = -1;
    String mCurrentGroupName = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.info_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            updateInfoView(args.getInt(ARG_POSITION), args.getString(ARG_NAME));
        } else if (mCurrentPosition != -1 && mCurrentGroupName != "") {
            updateInfoView(mCurrentPosition, mCurrentGroupName);
        }
    }

    public void updateInfoView(int position, String groupName) {
        final LinearLayout linearLayout;
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            linearLayout = getActivity().findViewById(R.id.info_layout_wide);
        } else {
            linearLayout = getActivity().findViewById(R.id.info_layout);
        }
        linearLayout.removeAllViews();
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        final String url = "https://tddd80server.herokuapp.com/medlemmar/" + groupName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("medlemmar");
                            for (int i = 0; i < results.length(); i++) {
                                TextView textView = new TextView(getContext());
                                JSONObject member = results.getJSONObject(i);
                                String email = member.get("epost").toString();
                                String name = member.get("namn").toString();
                                String answer;
                                if (member.has("svarade")) {
                                    answer = member.get("svarade").toString();
                                    textView.setText(email + ", " + name + ", " + answer);
                                } else {
                                    textView.setText(email + ", " + name);
                                }
                                linearLayout.addView(textView);
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
        mCurrentPosition = position;
        mCurrentGroupName = groupName;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}
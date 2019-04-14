package com.example.lab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;


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
            Log.d("deee", Integer.toString(args.getInt(ARG_POSITION)));
            updateInfoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            Log.d("deee", "hejehj");
            updateInfoView(mCurrentPosition);
        }
    }

    public void updateInfoView(int position) {
        TextView info = getActivity().findViewById(R.id.info);
        info.setText(Content.Info[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}
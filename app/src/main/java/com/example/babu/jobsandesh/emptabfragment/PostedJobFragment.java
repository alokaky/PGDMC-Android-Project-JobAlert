package com.example.babu.jobsandesh.emptabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babu.jobsandesh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostedJobFragment extends Fragment {


    public PostedJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posted_job, container, false);
    }

}

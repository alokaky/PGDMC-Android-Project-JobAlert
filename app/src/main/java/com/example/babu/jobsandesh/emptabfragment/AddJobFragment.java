package com.example.babu.jobsandesh.emptabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.babu.jobsandesh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddJobFragment extends Fragment {

    android.support.design.widget.TextInputEditText job_title;

    public AddJobFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_job, container, false);
        job_title=(android.support.design.widget.TextInputEditText)v.findViewById(R.id.et_job_title);
        return v;
    }

}

package com.example.babu.jobsandesh.tabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babu.jobsandesh.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalkinFragment extends Fragment {


    public WalkinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_walkin, container, false);
    }

}

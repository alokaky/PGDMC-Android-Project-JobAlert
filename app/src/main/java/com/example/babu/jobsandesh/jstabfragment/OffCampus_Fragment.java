package com.example.babu.jobsandesh.jstabfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.babu.jobsandesh.R;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffCampus_Fragment extends Fragment {

    public RecyclerView recyclerView_offcampus;

    CustomAdapter_offcampus customAdapter_offcampus;

    static ArrayList<JobseekerOffCampus_Cards> arrayList = new ArrayList<>();

    public OffCampus_Fragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_off_campus_, container, false);

        recyclerView_offcampus = (RecyclerView)view.findViewById(R.id.jobcontainer);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_offcampus.setLayoutManager(layoutManager);



        customAdapter_offcampus = new CustomAdapter_offcampus(getContext());
        recyclerView_offcampus.setAdapter(customAdapter_offcampus);
        return view;
    }
}
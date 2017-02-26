package com.example.babu.jobsandesh.jstabfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.babu.jobsandesh.R;

import static com.example.babu.jobsandesh.jstabfragment.OffCampus_Fragment.arrayList;

/**
 * Created by Alok on 25/Jan/17.
 */

public class CustomAdapter_offcampus extends RecyclerView.Adapter<CustomAdapter_offcampus.ViewHolder>
{
    private Context context;


    public CustomAdapter_offcampus(Context context) {
        this.context = context;
    }

    @Override
    public CustomAdapter_offcampus.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_jobdetail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter_offcampus.ViewHolder holder, int position)
    {
        holder.jobtitle.setText(arrayList.get(position).getEmp_id());
        holder.cmpname.setText(arrayList.get(position).getCom_name());
        holder.evetime.setText(arrayList.get(position).getPost());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView jobtitle,cmpname,evetime,evedate;

        public ViewHolder(View itemView) {
            super(itemView);
            jobtitle = (TextView)itemView.findViewById(R.id.tv_jobtitle);
            cmpname = (TextView)itemView.findViewById(R.id.tv_cmp_name);
            evetime = (TextView)itemView.findViewById(R.id.tv_evetime);
            evedate = (TextView)itemView.findViewById(R.id.tv_event_date);
        }
    }
}

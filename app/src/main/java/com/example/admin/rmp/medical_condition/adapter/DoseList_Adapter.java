package com.example.admin.rmp.medical_condition.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.model.Dose;

import java.util.ArrayList;

/**
 * Created by Ashwin on 29-Jan-18.
 */

public class DoseList_Adapter  extends RecyclerView.Adapter<DoseList_Adapter.ViewHolder>
{
    private ArrayList<Dose> doseList;
    private Context mContext;

    public DoseList_Adapter(ArrayList<Dose> doseList, Context mContext){
        this.doseList=doseList;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int pos) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.dose_list_row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.doseName.setText(doseList.get(position).getDoseName());
        holder.doseFrequency.setText(doseList.get(position).getDoseFrequency());
        holder.days.setText(doseList.get(position).getDoseNoOfDays());


    }


    @Override
    public int getItemCount() {
        try {
            return doseList.size();
        }
        catch (Exception e)
        {
            return 0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView doseName, doseFrequency, days, numbetTxt;

        public ViewHolder(final View itemView)
        {
            super(itemView);

            numbetTxt = (TextView)itemView.findViewById(R.id.number_txt);
            doseName = (TextView) itemView.findViewById(R.id.et_prescription_dose);
            doseFrequency = (TextView) itemView.findViewById(R.id.et_frequency);
            days = (TextView) itemView.findViewById(R.id.et_days);


        }


    }

}

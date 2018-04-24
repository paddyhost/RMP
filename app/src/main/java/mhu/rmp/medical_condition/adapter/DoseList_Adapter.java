package mhu.rmp.medical_condition.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.medical_condition.model.Dose;

import java.util.ArrayList;


public class DoseList_Adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<Dose> doseList;

    private Context mContext;


    public static final int PRESCRIBE_HEADER = 0;
    public static final int NORMAL = 1;



    public DoseList_Adapter(ArrayList<Dose> doseList, Context mContext){
        this.doseList=doseList;
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dose_list_row, parent, false);
            return new MyViewHolder(itemView);
        } else if (viewType == PRESCRIBE_HEADER) {
            //Inflating header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescribe_header, parent, false);
            return new HeaderViewHolder(itemView);
        }  else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.headerTitle.setText("Medicine Name\t\t\t\t\t\t\t\t\t\t\t\t" + "Freq\t\t" + "Days");

        }
        else if(holder instanceof MyViewHolder)
        {
            Dose dose = doseList.get(position-1);
            MyViewHolder myViewHolder = (MyViewHolder) holder;

            /*myViewHolder.doseName.setText(doseList.get(position).getDoseName());
            myViewHolder.doseFrequency.setText(doseList.get(position).getDoseFrequency());
            myViewHolder.days.setText(doseList.get(position).getDoseNoOfDays());*/

            myViewHolder.doseName.setText(dose.getDoseName());
            myViewHolder.doseFrequency.setText(dose.getDoseFrequency());
            myViewHolder.days.setText(dose.getDoseNoOfDays());

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return PRESCRIBE_HEADER;
        } else  {
            return NORMAL;
        }

    }

    @Override
    public int getItemCount() {
        //return subTestList.size() + 2;
        return doseList==null?1:doseList.size()+1;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        public HeaderViewHolder(View view) {
            super(view);
            headerTitle = (TextView) view.findViewById(R.id.prescribe_header_name);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView doseName, doseFrequency, days;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);

            doseName = (TextView) itemView.findViewById(R.id.et_prescription_dose);
            doseFrequency = (TextView) itemView.findViewById(R.id.et_frequency);
            days = (TextView) itemView.findViewById(R.id.et_days);
            this.itemView = itemView;
        }
    }



  /*  @Override
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
        TextView doseName, doseFrequency, days;


        public ViewHolder(final View itemView)
        {
            super(itemView);

            doseName = (TextView) itemView.findViewById(R.id.et_prescription_dose);
            doseFrequency = (TextView) itemView.findViewById(R.id.et_frequency);
            days = (TextView) itemView.findViewById(R.id.et_days);

        }
    }
*/
}

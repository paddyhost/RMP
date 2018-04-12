package mhu.rmp.mhu_test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mhu.rmp.R;
import mhu.rmp.mhu_test.model.SubTest;

/**
 * Created by Nikam on 10/04/2018.
 */

public class SubTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubTest> subTestList;

        public static final int Header = 0;
        public static final int Normal = 1;




   public SubTestAdapter(List<SubTest> subTestList) {
        this.subTestList = subTestList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Normal) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_test_list_row, parent, false);
            return new MyViewHolder(itemView);
        } else if (viewType == Header) {
            //Inflating header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header, parent, false);
            return new HeaderViewHolder(itemView);
        }  else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.headerTitle.setText("Sub Test Name\t\t\t\t\t" + "ReferenceValue\t\t" + "ReadingValue");

        }
        else if(holder instanceof MyViewHolder)
        {
            SubTest subTest = subTestList.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.subtest_name.setText(subTest.getName());
            myViewHolder.referenceValue.setText(subTest.getRefrance());
            myViewHolder.readingValue.setText(subTest.getReding());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Header;
        } else  {
            return Normal;
        }

    }

    @Override
    public int getItemCount() {
        //return subTestList.size() + 2;
        return subTestList==null?0:subTestList.size();
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerTitle;

        public HeaderViewHolder(View view) {
            super(view);
            headerTitle = (TextView) view.findViewById(R.id.header_name);
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subtest_name,referenceValue,readingValue;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);

            subtest_name = (TextView) itemView.findViewById(R.id.subtest_name);
            referenceValue = (TextView) itemView.findViewById(R.id.txt_reference_value);
            readingValue = (TextView) itemView.findViewById(R.id.txt_reading_value);

            this.itemView = itemView;
        }
    }



   /* public SubTestAdapter(List<SubTest> subTestList) {
        this.subTestList = subTestList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_test_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SubTest subTest = subTestList.get(position);
        holder.subtest_name.setText(subTest.getName());
        holder.referenceValue.setText(subTest.getRefrance());
        holder.readingValue.setText(subTest.getReding());
    }

    @Override
    public int getItemCount() {
        return subTestList==null?0:subTestList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subtest_name,referenceValue,readingValue;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);

            subtest_name = (TextView) itemView.findViewById(R.id.subtest_name);
            referenceValue = (TextView) itemView.findViewById(R.id.txt_reference_value);
            readingValue = (TextView) itemView.findViewById(R.id.txt_reading_value);


            this.itemView = itemView;
        }
    }*/
}

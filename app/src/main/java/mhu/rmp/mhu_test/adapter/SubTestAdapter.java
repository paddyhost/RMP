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

public class SubTestAdapter extends RecyclerView.Adapter<SubTestAdapter.MyViewHolder> {

    private List<SubTest> subTestList;


    public SubTestAdapter(List<SubTest> subTestList) {
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
}

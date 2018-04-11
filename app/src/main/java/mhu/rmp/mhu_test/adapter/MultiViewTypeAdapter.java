package mhu.rmp.mhu_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mhu.rmp.R;
import mhu.rmp.mhu_test.model.Model;

/**
 * Created by Nikam on 11/04/2018.
 */

public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;


    public static class TestViewHolder extends RecyclerView.ViewHolder {
        TextView txtTestName;

        public TestViewHolder(View itemView) {
            super(itemView);

            this.txtTestName = (TextView) itemView.findViewById(R.id.test_name);

        }

    }

    public static class SubTestViewHolder extends RecyclerView.ViewHolder {


        TextView txtSubTestName,referenceValue,readingValue;

        public SubTestViewHolder(View itemView) {
            super(itemView);

            this.txtSubTestName = (TextView) itemView.findViewById(R.id.subtest_name);
            this.referenceValue = (TextView) itemView.findViewById(R.id.txt_reference_value);
            this.readingValue = (TextView) itemView.findViewById(R.id.txt_reading_value);

        }

    }



    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        this.total_types = dataSet.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEST_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list_row, parent, false);
                return new TestViewHolder(view);
            case Model.SUB_TEST_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_test_list_row, parent, false);
                return new SubTestViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.TEST_TYPE;
            case 1:
                return Model.SUB_TEST_TYPE;

        }

        return -1;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Model object = dataSet.get(listPosition);
        if (object != null) {
            switch (object.type) {
                case Model.TEST_TYPE:
                    ((TestViewHolder) holder).txtTestName.setText(object.text);

                    break;
                case Model.SUB_TEST_TYPE:
                    ((SubTestViewHolder) holder).txtSubTestName.setText(object.text);
                    ((SubTestViewHolder) holder).referenceValue.setText(object.data);
                    ((SubTestViewHolder) holder).readingValue.setText(object.data);
                    break;

            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}

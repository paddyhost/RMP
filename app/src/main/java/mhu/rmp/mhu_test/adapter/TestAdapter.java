package mhu.rmp.mhu_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mhu.rmp.R;
import mhu.rmp.mhu_test.model.MHU_Test;

/**
 * Created by Nikam on 10/04/2018.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private List<MHU_Test> mhuTestList;
    private RecyclerView recyclerView;

    public TestAdapter(List<MHU_Test> mhuTestList, RecyclerView recyclerView) {
        this.mhuTestList = mhuTestList;
        this.recyclerView=recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MHU_Test test = mhuTestList.get(position);
        holder.test_name.setText(test.getTestName());

    }

    @Override
    public int getItemCount() {
        return mhuTestList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView test_name;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);

            test_name = (TextView) itemView.findViewById(R.id.test_name);


            this.itemView = itemView;
        }
    }
}

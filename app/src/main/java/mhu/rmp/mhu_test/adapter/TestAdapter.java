package mhu.rmp.mhu_test.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
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
    private  Context context;


    public TestAdapter(List<MHU_Test> mhuTestList) {
        this.mhuTestList = mhuTestList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_list_row, parent, false);
        context=parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            MHU_Test test = mhuTestList.get(position-1);
            holder.test_name.setText(test.getTestName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, OrientationHelper.VERTICAL, false);

            holder.recyclerview.setLayoutManager(linearLayoutManager);
            holder.recyclerview.setItemAnimator(new DefaultItemAnimator());
            holder.recyclerview.setAdapter(new SubTestAdapter(mhuTestList.get(position-1).getSubtests()));
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public int getItemCount() {
        //return mhuTestList.size();

        return mhuTestList==null?1:mhuTestList.size()+1;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView test_name;
        RecyclerView recyclerview;
        View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);

            test_name = (TextView) itemView.findViewById(R.id.test_name);
            recyclerview= (RecyclerView) itemView.findViewById(R.id.recyclerview);

            this.itemView = itemView;
        }
    }
}

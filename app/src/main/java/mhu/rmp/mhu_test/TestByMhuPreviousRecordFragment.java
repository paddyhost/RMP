package mhu.rmp.mhu_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mhu.rmp.R;
import mhu.rmp.activity.PreviousRecordsActivity;
import mhu.rmp.mhu_test.adapter.MultiViewTypeAdapter;
import mhu.rmp.mhu_test.adapter.SubTestAdapter;
import mhu.rmp.mhu_test.adapter.TestAdapter;
import mhu.rmp.mhu_test.model.MHU_Test;
import mhu.rmp.mhu_test.model.Model;
import mhu.rmp.mhu_test.model.SubTest;


public class TestByMhuPreviousRecordFragment extends Fragment {


    private RecyclerView testRecyclerView;
    private TestAdapter testAdapter;


    public TestByMhuPreviousRecordFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.fragment_test_by_mhu_previous_record, container, false);



        testAdapter=new TestAdapter(PreviousRecordsActivity.previousRecords.getTestList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        testRecyclerView= (RecyclerView) rootview.findViewById(R.id.test_recyclerview);
        testRecyclerView.setLayoutManager(linearLayoutManager);
        testRecyclerView.setItemAnimator(new DefaultItemAnimator());
        testRecyclerView.setAdapter(testAdapter);
        return rootview;
    }
}

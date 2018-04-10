package mhu.rmp.mhu_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mhu.rmp.R;
import mhu.rmp.mhu_test.adapter.SubTestAdapter;
import mhu.rmp.mhu_test.adapter.TestAdapter;
import mhu.rmp.mhu_test.model.MHU_Test;
import mhu.rmp.mhu_test.model.SubTest;


public class TestByMhuPreviousRecordFragment extends Fragment {

    private List<MHU_Test> mhuTestList;
    private List<SubTest> subTestList;
    private RecyclerView testRecyclerView,subTestRecyclerView;
    private TestAdapter testAdapter;
    private SubTestAdapter subTestAdapter;

    public TestByMhuPreviousRecordFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview =inflater.inflate(R.layout.fragment_test_by_mhu_previous_record, container, false);
        // Inflate the layout for this fragment
        testRecyclerView= (RecyclerView) rootview.findViewById(R.id.test_recyclerview);
        subTestRecyclerView= (RecyclerView) rootview.findViewById(R.id.subtest_recyclerview);

        mhuTestList = new ArrayList<>();
        testAdapter = new TestAdapter(mhuTestList,testRecyclerView);
        RecyclerView.LayoutManager testLayoutManager = new LinearLayoutManager(getContext());
        testRecyclerView.setLayoutManager(testLayoutManager);
        testRecyclerView.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();


        subTestList = new ArrayList<>();
        subTestAdapter = new SubTestAdapter(subTestList,subTestRecyclerView);
        RecyclerView.LayoutManager subTestLayoutManager = new LinearLayoutManager(getContext());
        subTestRecyclerView.setLayoutManager(subTestLayoutManager);
        subTestRecyclerView.setAdapter(subTestAdapter);
        subTestAdapter.notifyDataSetChanged();

        return rootview;
    }
}

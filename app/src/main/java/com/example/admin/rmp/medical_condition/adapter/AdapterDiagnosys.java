package com.example.admin.rmp.medical_condition.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.model.Diagnosys;

import java.util.ArrayList;
import java.util.List;

public class AdapterDiagnosys extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Diagnosys> diagnosysArrayLists;

    public AdapterDiagnosys(Context context, ArrayList<Diagnosys> diagnosysArrayLists) {
        ctx = context;
        this.diagnosysArrayLists = diagnosysArrayLists;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        try {
            return diagnosysArrayLists.size();
        }
        catch (Exception e)
        {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return diagnosysArrayLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_diagnosys, parent, false);
        }

        Diagnosys diagnosys = getDiagnosysData(position);

        TextView text = (TextView) view.findViewById(R.id.textView1);
        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.checkBox1);
        text.setText(diagnosys.name);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setTag(position);
        cbBuy.setChecked(diagnosys.selected);
        return view;
    }

    private Diagnosys getDiagnosysData(int position) {
        return ((Diagnosys) getItem(position));
    }

    public ArrayList<Diagnosys> getDiagnosys() {
        ArrayList<Diagnosys> diagnosysArrayList = new ArrayList<Diagnosys>();
        for (Diagnosys diagnosys : diagnosysArrayLists) {
            if (diagnosys.selected)
                diagnosysArrayList.add(diagnosys);
        }
        return diagnosysArrayList;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getDiagnosysData((Integer) buttonView.getTag()).selected = isChecked;
        }
    };
}

 /*extends ArrayAdapter
{
    Context context;
    List<String> modelItems;
    @SuppressWarnings("unchecked")

    public AdapterDiagnosys(Context context, List<String> resource)
    {
        super(context, R.layout.list_diagnosys,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_diagnosys, parent, false);
        TextView name = (TextView) convertView.findViewById(R.id.textView1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

        name.setText(modelItems.get(position));

        return convertView;
    }
}*/
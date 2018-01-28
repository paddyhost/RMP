package com.example.admin.rmp.medical_condition.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.rmp.R;

import java.util.List;

public class AdapterDiagnosys extends ArrayAdapter
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
}
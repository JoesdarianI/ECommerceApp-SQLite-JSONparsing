package com.example.projectmcs;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectmcs.Model.Transaction;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter<Transaction> {

private Context context;
private int Resource;

public HistoryAdapter(Context context, int resource, ArrayList<Transaction> data){
    super(context,resource,data);
    this.context = context;
    this.Resource = resource;
}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(Resource,parent,false);

        TextView TId = (TextView) convertView.findViewById(R.id.TransId);
        TextView pName = (TextView) convertView.findViewById(R.id.fName);
        TextView price = (TextView) convertView.findViewById(R.id.tPrice);
        TextView quantity = (TextView) convertView.findViewById(R.id.bQuantity);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        TId.setText(Integer.toString(getItem(position).getTransactionId()));
        pName.setText(getItem(position).getName());
        price.setText(Integer.toString(getItem(position).getPrice()));
        quantity.setText(Integer.toString(getItem(position).getQuantity()));
        date.setText(getItem(position).getDate());

    return convertView;
    }
}


package com.example.projectmcs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectmcs.Model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeAdapter extends ArrayAdapter<ProductModel> {

    private Context context;
    private int Resource;

    public HomeAdapter(Context context, int resource, ArrayList<ProductModel> data) {
        super(context,resource,data);
        this.context = context;
        this.Resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(Resource, parent, false);

        ImageView img = (ImageView) convertView.findViewById(R.id.itemImg);
        TextView name = (TextView) convertView.findViewById(R.id.itemName);
        TextView description = (TextView) convertView.findViewById(R.id.itemDesc);
        TextView price = (TextView) convertView.findViewById(R.id.itemPrice);
        TextView rating = (TextView) convertView.findViewById(R.id.itemRating);

        Picasso.get().load(getItem(position).getImg()).into(img);
        name.setText(getItem(position).getName());
        description.setText(getItem(position).getDecription());
        price.setText(Integer.toString(getItem(position).getPrice()));
        rating.setText(getItem(position).getRating());

        return convertView;
    }
}
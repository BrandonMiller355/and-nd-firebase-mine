package com.google.firebase.udacity.friendlychat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.udacity.friendlychat.R;
import com.google.firebase.udacity.friendlychat.models.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsAdapter extends ArrayAdapter<Restaurant> {

    @BindView(R.id.restaurantName) TextView nameTextView;
    //@BindView(R.id.category)

    public RestaurantsAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_restaurant, parent, false);
        }

        //TODO:Brandon - Is this the best way to do this?
        ButterKnife.bind(this, convertView);

        convertView.setClickable(true);

//TODO: Brandon - get this working
//        convertView.setOnClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

//        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
//        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        //TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        Restaurant restaurant = getItem(position);

        nameTextView.setText(restaurant.getName());

        return convertView;
    }
}

package com.google.firebase.udacity.friendlychat.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.udacity.friendlychat.R;
import com.google.firebase.udacity.friendlychat.models.Restaurant;

import java.util.List;

public class RestaurantsAdapter extends ArrayAdapter<Restaurant> {

    public RestaurantsAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_restaurant, parent, false);
        }

//        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
//        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        Restaurant restaurant = getItem(position);

        nameTextView.setText(restaurant.getName());

        return convertView;
    }
}

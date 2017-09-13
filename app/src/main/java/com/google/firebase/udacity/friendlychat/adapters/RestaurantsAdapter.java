package com.google.firebase.udacity.friendlychat.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    @BindView(R.id.restaurantName) TextView nameTextView;
    List<Restaurant> restaurantList;

    public RestaurantsAdapter(List<Restaurant> restaurants) {
        restaurantList = restaurants;
    }

    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        RestaurantsAdapter.ViewHolder viewHolder = new RestaurantsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantsAdapter.ViewHolder holder, int position) {
        //TODO: Brandon - here
        holder.restaurantName.setText(restaurantList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cvRestaurant) CardView cvRestaurant;
        @BindView(R.id.restaurantName) TextView restaurantName;

        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(view);

            //TODO: Brandon - add onItemClickListener here

        }
    }



//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_restaurant, parent, false);
//        }
//
//        //TODO:Brandon - Is this the best way to do this?
//        ButterKnife.bind(this, convertView);
//
//        convertView.setClickable(true);
//
////TODO: Brandon - get this working
////        convertView.setOnClickListener(new OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////
////            }
////        });
//
////        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
////        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
//        //TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
//
//        Restaurant restaurant = getItem(position);
//
//        nameTextView.setText(restaurant.getName());
//
//        return convertView;
//    }
}

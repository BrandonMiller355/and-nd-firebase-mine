package com.google.firebase.udacity.friendlychat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.udacity.friendlychat.R;
import com.google.firebase.udacity.friendlychat.adapters.RestaurantsAdapter;
import com.google.firebase.udacity.friendlychat.models.FriendlyMessage;
import com.google.firebase.udacity.friendlychat.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsFragment extends Fragment {

    private static final String TAG = RestaurantsFragment.class.getSimpleName();

    private ListView restaurantsListView;
    private FloatingActionButton floatingActionButton;

    private RestaurantsAdapter mRestaurantsAdapter;

    // Firebase instance variables.
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRestaurantsDatabaseReference;
    private ChildEventListener mChildEventListener;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public RestaurantsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);

        // Initialize Firebase components.
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRestaurantsDatabaseReference = mFirebaseDatabase.getReference().child("restaurants");

        //TODO: Brandon - this is probably not the best place for this...
        attachDatabaseReadListener();

        restaurantsListView = (ListView) view.findViewById(R.id.listview_restaurants);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_add_restaurant);

        // Initialize message ListView and its adapter
        List<Restaurant> restaurantList = new ArrayList<>();
        mRestaurantsAdapter = new RestaurantsAdapter(getActivity(), R.layout.item_restaurant, restaurantList);
        restaurantsListView.setAdapter(mRestaurantsAdapter);

        // FAB button sends a message and clears the EditText
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Oh hi", Toast.LENGTH_SHORT).show();

//                FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(),
//                        mUsername, null);
//                mMessagesDatabaseReference.push().setValue(friendlyMessage);
//
//                // Clear input box
//                mMessageEditText.setText("");
            }
        });

        return view;
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                    mRestaurantsAdapter.add(restaurant);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };

            mRestaurantsDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mRestaurantsDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }
}

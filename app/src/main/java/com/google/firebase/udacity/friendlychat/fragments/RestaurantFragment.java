package com.google.firebase.udacity.friendlychat.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.google.firebase.udacity.friendlychat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;

public class RestaurantFragment extends Fragment {

    private static final String TAG = RestaurantFragment.class.getSimpleName();

    public static String ARG_RESTAURANT_ID = "restaurantId";

    @BindView(R.id.name) EditText name;

    //TODO: Brandon - see if I need this or not
//    // Firebase instance variables.
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference mRestaurantsDatabaseReference;
//    private ChildEventListener mChildEventListener;

    public RestaurantFragment() {
    }

    public static RestaurantFragment newInstance(String restaurantId) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RESTAURANT_ID, restaurantId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        ButterKnife.bind(this, view);

        //TODO: Brandon - populate this from categories from db. Change these in Settings?
        String[] categories = {"Mexican", "Italian"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Find TextView control
//        AutoCompleteTextView acTextView = (AutoCompleteTextView) view.findViewById(R.id.category);
//        //Set the number of characters the user must type before the drop down list is shown
//        acTextView.setThreshold(1);
//        acTextView.showDropDown();
//        //Set the adapter
//        acTextView.setAdapter(adapter);

        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.category);
        spinner.setAdapter(adapter);



        name.requestFocus();
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
//
//        view.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


//        // Initialize Firebase components.
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//        mRestaurantsDatabaseReference = firebaseDatabase.getReference().child("restaurant_categories");

//        //TODO: Brandon - this is probably not the best place for this...
//        attachDatabaseReadListener();


//                                    Restaurant restaurant = new Restaurant(et.getText().toString());
//                                    mRestaurantsDatabaseReference.push().setValue(restaurant);
//                                    //TODO: Brandon - snackbar isn't showing up for some reason
//                                    Snackbar.make(getView(), R.string.restaurant_added, Snackbar.LENGTH_SHORT);


//                FriendlyMessage friendlyMessage = new FriendlyMessage(mMessageEditText.getText().toString(),
//                        mUsername, null);
//                mMessagesDatabaseReference.push().setValue(friendlyMessage);
//
//                // Clear input box
//                mMessageEditText.setText("");

        return view;
    }

//    private void attachDatabaseReadListener() {
//        if (mChildEventListener == null) {
//            mChildEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
//                    mRestaurantsAdapter.add(restaurant);
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                }
//            };
//
//            mRestaurantsDatabaseReference.addChildEventListener(mChildEventListener);
//        }
//    }
//
//    private void detachDatabaseReadListener() {
//        if (mChildEventListener != null) {
//            mRestaurantsDatabaseReference.removeEventListener(mChildEventListener);
//            mChildEventListener = null;
//        }
//    }
}

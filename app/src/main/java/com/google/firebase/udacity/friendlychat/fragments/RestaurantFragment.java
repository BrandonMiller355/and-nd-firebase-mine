package com.google.firebase.udacity.friendlychat.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.udacity.friendlychat.R;
import com.google.firebase.udacity.friendlychat.models.Restaurant;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;

public class RestaurantFragment extends Fragment {

    private static final String TAG = RestaurantFragment.class.getSimpleName();

    public static String ARG_RESTAURANT_ID = "restaurantId";
    public static String ARG_CATEGORY = "category";
    public static String ARG_CATEGORIES_LIST = "categories";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRestaurantsDatabaseReference;

    @BindView(R.id.name) EditText name;
    @BindView(R.id.category) Spinner category;

    private String getArgRestaurantId = "";
    private String getArgCategoryId = "";

    //TODO: Brandon - see if I need this or not
//    // Firebase instance variables.
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference mRestaurantsDatabaseReference;
//    private ChildEventListener mChildEventListener;

    public RestaurantFragment() {
    }

    public static RestaurantFragment newInstance(String restaurantId, String categoryId, List<String> categories) {
        RestaurantFragment fragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RESTAURANT_ID, restaurantId);
        args.putString(ARG_CATEGORY, categoryId);
        args.putString(ARG_CATEGORIES_LIST, new Gson().toJson(categories));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        ButterKnife.bind(this, view);

        // TODO - Brandon: Move all these somewhere else methinks
        // Initialize Firebase components.
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRestaurantsDatabaseReference = mFirebaseDatabase.getReference().child("restaurants");

        List<String> categories = (List<String>)Arrays.asList(getArguments().getSerializable(ARG_CATEGORIES_LIST).toString().split("\\s*\",\"\\s*"));
        categories.set(0, categories.get(0).replace("[", "").replace("\"", ""));
        categories.set(categories.size()-1, categories.get(categories.size()-1).replace("]", "").replace("\"", ""));

        getArgCategoryId = getArguments().getString(ARG_CATEGORY);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //TODO: Ideally, they could enter a new category here directly

        MaterialSpinner spinner = (MaterialSpinner) view.findViewById(R.id.category);
        spinner.setAdapter(adapter);

        //TODO: Brandon - this
//        if (categoryId != null) {
//            spinner.setSelection(getPositionOfgetArgCategoryId());
//        }

        //TODO: Brandon - keep this?
        if (getArgRestaurantId != null) {
            name.setText(getArgRestaurantId);
        } else {
            name.requestFocus();
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, 0);
        }
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.restaurant_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_restaurant_menu:
                // Save

                Restaurant restaurant = new Restaurant(name.getText().toString(), category.getSelectedItem().toString());
                mRestaurantsDatabaseReference.push().setValue(restaurant);

                Snackbar.make(getView(), R.string.restaurant_added, Snackbar.LENGTH_LONG).show();

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame, new RestaurantsFragment(), getResources().getString(R.string.restaurants))
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

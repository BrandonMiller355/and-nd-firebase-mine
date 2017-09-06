package com.google.firebase.udacity.friendlychat.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.udacity.friendlychat.R;
import com.google.firebase.udacity.friendlychat.adapters.RestaurantsAdapter;
import com.google.firebase.udacity.friendlychat.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.maxLength;

public class CategoriesFragment extends Fragment {

    private static final String TAG = CategoriesFragment.class.getSimpleName();

    private ListView categoriesListView;
    private FloatingActionButton fabAddCategory;

    //TODO: Brandon - shouldn't need custom adapter
    //private Adapter mCategoriesAdapter;
    private ArrayAdapter<String> mCategoriesAdapter;

    // Firebase instance variables.
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCategoriesDatabaseReference;
    private ChildEventListener mChildEventListener;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public CategoriesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

        // Initialize Firebase components.
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mCategoriesDatabaseReference = mFirebaseDatabase.getReference().child("categories");

//        //TODO: Brandon - this is probably not the best place for this...
        attachDatabaseReadListener();

        categoriesListView = (ListView) view.findViewById(R.id.listview_categories);
        fabAddCategory = (FloatingActionButton) view.findViewById(R.id.fab_add_category);

        //TODO: Brandon - clean this up
        // Initialize message ListView and its adapter
//        List<Restaurant> restaurantList = new ArrayList<>();
//        mRestaurantsAdapter = new RestaurantsAdapter(getActivity(), R.layout.item_restaurant, restaurantList);
//        restaurantsListView.setAdapter(mRestaurantsAdapter);

        List<String> categoryList = new ArrayList<>();

        //TODO: Brandon - see if can do this. Load list of categories into string array
        //categoryList = mCategoriesDatabaseReference.

        mCategoriesAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_category, categoryList);
        categoriesListView.setAdapter(mCategoriesAdapter);


        // FAB button sends a message and clears the EditText
        fabAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getFragmentManager().beginTransaction()
//                        .replace(R.id.frame, RestaurantFragment.newInstance("blah"))
//                        .addToBackStack(null)
//                        .commit();

                final EditText et = new EditText(getActivity());
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.leftMargin = lp.rightMargin = getResources().getDimensionPixelSize(R.dimen.standard_padding);;
                et.setInputType(InputType.TYPE_CLASS_TEXT);
                et.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
                et.setLayoutParams(lp);

                RelativeLayout rl = new RelativeLayout(getActivity());
                rl.addView(et);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.DialogTheme);
                builder.setTitle(getString(R.string.category_add));
                //builder.setMessage(getString(R.string.dialog_message));
                builder.setView(rl);
                String positiveText = getString(android.R.string.ok);
                builder.setPositiveButton(positiveText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // positive button logic
                                if(et.getText().toString().length() > 0) {
                                    dialog.dismiss();
                                    //Restaurant restaurant = new Restaurant(et.getText().toString());
                                    mCategoriesDatabaseReference.push().setValue(et.getText().toString());

                                    //TODO: Brandon - snackbar isn't showing up for some reason
                                    Snackbar.make(getView(), R.string.category_added, Snackbar.LENGTH_SHORT).show();
                                }
                            }
                        });

                String negativeText = getString(android.R.string.cancel);
                builder.setNegativeButton(negativeText,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // negative button logic
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                // display dialog
                dialog.show();

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
                    String category = dataSnapshot.getValue(String.class);
                    mCategoriesAdapter.add(category);
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

            mCategoriesDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            mCategoriesDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }
}

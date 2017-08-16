package com.google.firebase.udacity.friendlychat.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.udacity.friendlychat.R;

import butterknife.ButterKnife;


public class BaseFragment extends Fragment {

    protected FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
//    private ChildEventListener mChildEventListener;
//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public View onCreateView(int viewId, LayoutInflater inflater, ViewGroup container) {
        super.onCreateView(inflater, container);
        View view = inflater.inflate(getResources().getLayout(R.id.frame), container, false);


    }

    //TODO: Brandon - make this void, or return the ref?
    protected Void getDatabaseReference(String child) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(child);
        //return databaseReference;
    }

}

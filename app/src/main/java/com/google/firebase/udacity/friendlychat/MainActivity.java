/**
 * Copyright Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.firebase.udacity.friendlychat;

import android.app.Application;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.udacity.friendlychat.fragments.ChatFragment;
import com.google.firebase.udacity.friendlychat.fragments.RestaurantsFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            // Initializing Toolbar and setting it as the actionbar
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.app_name);

            //Initializing NavigationView
            navigationView = (NavigationView) findViewById(R.id.navigation_view);

            //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                // This method will trigger on item Click of navigation menu
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {

                    //Checking if the item is in checked state or not, if not make it in checked state
                    if(menuItem.isChecked()) menuItem.setChecked(false);
                    else menuItem.setChecked(true);

                    //Closing drawer on item click
                    drawerLayout.closeDrawers();

                    //Check to see which item was being clicked and perform appropriate action
                    switch (menuItem.getItemId()){


                        //Replacing the main content with ContentFragment Which is our Inbox View;
                        case R.id.chat:
                            toolbar.setTitle(R.string.chat);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.frame, new ChatFragment(), getResources().getString(R.string.chat))
                                    .addToBackStack(null)
                                    .commit();
                            return true;
                        case R.id.restaurants:
                            toolbar.setTitle(R.string.restaurants);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.frame, new RestaurantsFragment(), getResources().getString(R.string.restaurants))
                                    .addToBackStack(null)
                                    .commit();
                            return true;
                        default:
                            Toast.makeText(getApplicationContext(),"Error. Please notify developer.",Toast.LENGTH_SHORT).show();
                            return true;
                    }
                }
            });

            // Initializing Drawer Layout and ActionBarToggle
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.chat, R.string.chat) {

                @Override
                public void onDrawerClosed(View drawerView) {
                    // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                    super.onDrawerClosed(drawerView);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                    super.onDrawerOpened(drawerView);
                }
            };

            //Setting the actionbarToggle to drawer layout
            drawerLayout.setDrawerListener(actionBarDrawerToggle);

            //calling sync state is necessay or else your hamburger icon wont show up
            actionBarDrawerToggle.syncState();

            getFragmentManager().beginTransaction()
                    .replace(R.id.frame, new ChatFragment(), getResources().getString(R.string.chat))
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            //TODO: Brandon - add "are you sure you want to exit?" dialog / get this working correctly
            Fragment fragment = getFragmentManager().findFragmentByTag(getResources().getString(R.string.chat));

            if (fragment != null && fragment.isVisible()) {
                Toast.makeText(this, "Don't leave me!!!", Toast.LENGTH_SHORT).show();
            } else {
                super.onBackPressed();
            }
        }
    }
}


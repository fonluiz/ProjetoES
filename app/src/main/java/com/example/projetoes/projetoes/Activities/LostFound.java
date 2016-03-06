package com.example.projetoes.projetoes.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.projetoes.projetoes.Fragments.FoundItemFeed;
import com.example.projetoes.projetoes.Fragments.FoundThingFragment;
import com.example.projetoes.projetoes.Fragments.LostThingFragment;
import com.example.projetoes.projetoes.Fragments.ProfileFragment;

import com.example.projetoes.projetoes.Fragments.OnListFragmentInteractionListener;
import com.example.projetoes.projetoes.Fragments.OnFragmentInteractionListener;
import com.example.projetoes.projetoes.Fragments.dummy.DummyContent;
import com.example.projetoes.projetoes.R;

public class LostFound extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnListFragmentInteractionListener, OnFragmentInteractionListener {

    private FoundItemFeed foundItemFeed;
    private LostThingFragment lostThingFragment;
    private FoundThingFragment foundThingFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        foundItemFeed = new FoundItemFeed();
        lostThingFragment = new LostThingFragment();
        foundThingFragment = new FoundThingFragment();
        profileFragment = new ProfileFragment();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,
                foundItemFeed, FoundItemFeed.TAG).addToBackStack(FoundItemFeed.TAG).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment nextFrag = null;
        String nextFragTag = null;
        String  prevFragTag;


        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(
                getSupportFragmentManager().getBackStackEntryCount() - 1);
        prevFragTag = backEntry.getName();


        if (id == R.id.nav_feed) {

            nextFrag = foundItemFeed;
            nextFragTag = FoundItemFeed.TAG;

        } else if (id == R.id.nav_lost) {

            nextFrag = lostThingFragment;
            nextFragTag = LostThingFragment.TAG;

        } else if (id == R.id.nav_found) {

            nextFrag = foundThingFragment;
            nextFragTag = FoundThingFragment.TAG;

        } else if (id == R.id.nav_profile) {
            nextFrag = profileFragment;
            nextFragTag = ProfileFragment.TAG;

        }

        if (nextFrag!= null) {
            if (nextFragTag.equals(prevFragTag)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, nextFrag, nextFragTag).commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, nextFrag, nextFragTag)
                        .addToBackStack(nextFragTag).commit();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //TODO: Este metodo precisa ser implementado. Coloquei aqui so pra nao dar erro.
    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}

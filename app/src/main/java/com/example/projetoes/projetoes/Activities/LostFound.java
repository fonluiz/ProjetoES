package com.example.projetoes.projetoes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.projetoes.projetoes.Fragments.EditProfileFragment;
import com.example.projetoes.projetoes.Fragments.FeedFragment;
import com.example.projetoes.projetoes.Fragments.FoundItemFeed;
import com.example.projetoes.projetoes.Fragments.LostItemFeed;
import com.example.projetoes.projetoes.Fragments.ReportObjectFragment;
import com.example.projetoes.projetoes.Fragments.ProfileFragment;

import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;

public class LostFound extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.OnEditProfileFabClickedListener {

    private FoundItemFeed foundItemFeed;
    private LostItemFeed lostItemFeed;
    private FeedFragment feedFragment;
    private ReportObjectFragment lostThingFragment;
    private ReportObjectFragment foundThingFragment;
    private ProfileFragment profileFragment;
    private EditProfileFragment editProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        foundItemFeed = new FoundItemFeed();
        lostItemFeed = new LostItemFeed();
        feedFragment = new FeedFragment();
        lostThingFragment = ReportObjectFragment.newInstance(Status.PERDIDO);
        foundThingFragment = ReportObjectFragment.newInstance(Status.ENCONTRADO);
        profileFragment = new ProfileFragment();
        editProfileFragment = new EditProfileFragment();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,
                feedFragment, FeedFragment.TAG).addToBackStack(FeedFragment.TAG).commit();
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
        String prevFragTag;

        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(
                getSupportFragmentManager().getBackStackEntryCount() - 1);
        prevFragTag = backEntry.getName();

        if (id == R.id.nav_feed) {

            nextFrag = feedFragment;
            nextFragTag = FeedFragment.TAG;

        } else if (id == R.id.nav_lost) {

            nextFrag = lostThingFragment;
            nextFragTag = ReportObjectFragment.TAG;

        } else if (id == R.id.nav_found) {

            nextFrag = foundThingFragment;
            nextFragTag = ReportObjectFragment.TAG;

        } else if (id == R.id.nav_profile) {

            nextFrag = profileFragment;
            nextFragTag = ProfileFragment.TAG;

        }

        if (nextFrag != null) {

            if (nextFragTag.equals(prevFragTag)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, nextFrag, nextFragTag).commit();
            } else {
               getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, nextFrag, nextFragTag)
                        .addToBackStack(nextFragTag).commit();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Quando recebe o resultado de uma intenção, envia este resultado para o fragment
     * onde a intenção foi lançada.
     *
     * @param requestCode Código de requisição
     * @param resultCode  Código de resposta
     * @param data        Dados da resultado
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(ReportObjectFragment.TAG);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFabClicked() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.container_layout, editProfileFragment, EditProfileFragment.TAG)
                .addToBackStack(EditProfileFragment.TAG).commit();
    }
    
}

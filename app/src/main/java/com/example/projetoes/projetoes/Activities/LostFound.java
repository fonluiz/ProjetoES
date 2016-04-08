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
import android.view.View;

import com.example.projetoes.projetoes.Fragments.CardExpanded;
import com.example.projetoes.projetoes.Fragments.EditProfileFragment;
import com.example.projetoes.projetoes.Fragments.FeedFragment;
import com.example.projetoes.projetoes.Fragments.FoundItemFeed;
import com.example.projetoes.projetoes.Fragments.ReportObjectFragment;
import com.example.projetoes.projetoes.Fragments.ProfileFragment;

import com.example.projetoes.projetoes.Interfaces.OnCardClickedListener;
import com.example.projetoes.projetoes.Models.Card;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;

import java.util.ArrayList;
import java.util.List;

public class LostFound extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.OnEditProfileFabClickedListener, OnCardClickedListener {

    private FeedFragment feedFragment;
    private ReportObjectFragment lostThingFragment;
    private ReportObjectFragment foundThingFragment;
    private ProfileFragment profileFragment;
    private EditProfileFragment editProfileFragment;
    private CardExpanded expFoundItem;
    private CardExpanded expLostItem;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        feedFragment = new FeedFragment();
        lostThingFragment = ReportObjectFragment.newInstance(Status.PERDIDO);
        foundThingFragment = ReportObjectFragment.newInstance(Status.ENCONTRADO);
        profileFragment = new ProfileFragment();
        editProfileFragment = new EditProfileFragment();
        expFoundItem = new CardExpanded().newInstance(Status.ENCONTRADO);
        expLostItem = new CardExpanded().newInstance(Status.PERDIDO);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,
                feedFragment, FeedFragment.TAG).addToBackStack(FeedFragment.TAG).commit();



    }



    @Override
    public void onBackPressed() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setDrawerState(true);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
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
        FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(
                getSupportFragmentManager().getBackStackEntryCount() - 1);
        String prevFragTag = backEntry.getName();
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(prevFragTag);
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

    /**
     * Quando entrar na tela de editar perfil, esse método desabilita o menu.
     * @param isEnabled
     */
    public void setDrawerState(boolean isEnabled) {

        if ( isEnabled) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();

        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            mDrawerToggle.syncState();
        }
    }

    public List<Card> getCardList(int quantidade){
        String[] titulos = {"Celular modelo Glaxy", "Caneta Stylus especial", "Chaveiro contendo 7 chaves", "Bolsa de couro", "Documento de fulando de tal", "cachorro de raça"};
        String[] bairros = {"Centro", "Catole","UFCG","Prata", "Bodocongo", "Prata"};
        int[] fotos = {R.drawable.celular, R.drawable.caneta,R.drawable.chaves,R.drawable.bolsa,R.drawable.documento,R.drawable.cachorro};
        List<Card> listAux = new ArrayList<>();

        for (int i = 0; i<quantidade; i++){
            Card c = new Card(titulos[i % titulos.length],bairros[i % bairros.length], fotos[i%fotos.length]);
            listAux.add(c);
        }
        return listAux;
    }

    @Override
    public void onCardClicked(Status status) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (status.equals(Status.PERDIDO)) {
            transaction.replace(R.id.container_layout, expLostItem, CardExpanded.TAG)
                    .addToBackStack(CardExpanded.TAG).commit();
        } else {
            transaction.replace(R.id.container_layout, expFoundItem, CardExpanded.TAG)
                    .addToBackStack(CardExpanded.TAG).commit();
        }
    }
}

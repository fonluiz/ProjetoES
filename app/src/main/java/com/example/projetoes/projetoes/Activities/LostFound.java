package com.example.projetoes.projetoes.Activities;

import android.content.Intent;
import android.content.IntentSender;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetoes.projetoes.Utils.ProfileImageLoader;
import com.example.projetoes.projetoes.Fragments.CardExpanded;
import com.example.projetoes.projetoes.Fragments.EditProfileFragment;
import com.example.projetoes.projetoes.Fragments.FeedFragment;
import com.example.projetoes.projetoes.Fragments.ReportObjectFragment;
import com.example.projetoes.projetoes.Fragments.ProfileFragment;

import com.example.projetoes.projetoes.Interfaces.OnCardClickedListener;
import com.example.projetoes.projetoes.Models.Card;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;
import com.example.projetoes.projetoes.Utils.RoundedImageView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

import java.util.ArrayList;
import java.util.List;

public class LostFound extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProfileFragment.OnEditProfileFabClickedListener,
        OnCardClickedListener, GoogleApiClient.OnConnectionFailedListener {


    private static final int RC_SIGN_IN = 2;
    private static final String TAG = "LOST_FOUND_ACTIVITY";
    private FeedFragment feedFragment;
    private ReportObjectFragment lostThingFragment;
    private ReportObjectFragment foundThingFragment;
    private ProfileFragment profileFragment;
    private EditProfileFragment editProfileFragment;
    private CardExpanded expFoundItem;
    private CardExpanded expLostItem;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;


    private  NavigationView mNavigationView;

    GoogleSignInAccount mAccount;
    private boolean isSignedIn;
    MenuItem signInBtn;
    MenuItem signOutBtn;

    private ImageView userImage;
    private TextView userName;
    private TextView userEmail;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        feedFragment = new FeedFragment();
        lostThingFragment = ReportObjectFragment.newInstance(Status.PERDIDO);
        foundThingFragment = ReportObjectFragment.newInstance(Status.ENCONTRADO);
        profileFragment = new ProfileFragment();
        editProfileFragment = new EditProfileFragment();
        expFoundItem = new CardExpanded().newInstance(Status.ENCONTRADO);
        expLostItem = new CardExpanded().newInstance(Status.PERDIDO);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,
                feedFragment, FeedFragment.TAG).commit();

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

        signInBtn = (MenuItem) mNavigationView.getMenu().findItem(R.id.nav_login);
        signOutBtn = (MenuItem) mNavigationView.getMenu().findItem(R.id.nav_logout);
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
        String prevFragTag = null;

        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(
                    getSupportFragmentManager().getBackStackEntryCount() - 1);
            prevFragTag = backEntry.getName();
        }

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

        } else if (id == R.id.nav_login) {

            signIn();

        } else if (id == R.id.nav_logout) {

            signOut();
        }

        if (nextFrag != null) {

           removeLastFragFromBackStack();

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


    private void removeLastFragFromBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(
                    getSupportFragmentManager().getBackStackEntryCount() - 1);
            String prevFragTag = backEntry.getName();
            Fragment fragToRemove = getSupportFragmentManager().findFragmentByTag(prevFragTag);
            getSupportFragmentManager().beginTransaction().remove(fragToRemove).commit();
            getSupportFragmentManager().popBackStack();
        }
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
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            super.onActivityResult(requestCode, resultCode, data);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else if (requestCode == ReportObjectFragment.REQUEST_IMAGE_GET) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(
                    getSupportFragmentManager().getBackStackEntryCount() - 1);
            String prevFragTag = backEntry.getName();
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(prevFragTag);
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onFabClicked() {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        removeLastFragFromBackStack();
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

    public List<Card> getCardFoundList(int quantidade){
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
        removeLastFragFromBackStack();
        if (status.equals(Status.PERDIDO)) {
            transaction.replace(R.id.container_layout, expLostItem, CardExpanded.TAG)
                    .addToBackStack(CardExpanded.TAG).commit();
        } else {
            transaction.replace(R.id.container_layout, expFoundItem, CardExpanded.TAG)
                    .addToBackStack(CardExpanded.TAG).commit();
        }
    }

    public List<Card> getCardLostList(int quantidade){
        String[] titulos = {"cachorro de raça", "Chaveiro contendo 7 chaves", "Caneta Stylus especial", "Documento de fulando de tal", "Bolsa de couro", "Celular modelo Glaxy"};
        String[] bairros = {"Centro", "Catole","UFCG","Prata", "Bodocongo", "Prata"};
        int[] fotos = {R.drawable.cachorro,R.drawable.chaves ,R.drawable.caneta,R.drawable.documento,R.drawable.bolsa,R.drawable.celular};
        String[] recompensas = {"20R$", "40R$","10R$","30R$", "50R$", "15R$"};
        List<Card> listAux = new ArrayList<>();

        for (int i = 0; i<quantidade; i++){
            Card c = new Card(titulos[i % titulos.length],bairros[i % bairros.length], fotos[i%fotos.length], recompensas[i%recompensas.length]);
            listAux.add(c);
        }
        return listAux;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "Conexão falhou", Toast.LENGTH_LONG).show();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        // Clear the default account so that GoogleApiClient will not automatically
        // connect in the future.
        if (isSignedIn) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            updateUI(false);
            updateSignInOutBtns();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            mAccount = result.getSignInAccount();
            updateUI(true);
            updateSignInOutBtns();
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
            updateSignInOutBtns();
        }
    }

    private void updateUI(boolean isSignedIn) {
        this.isSignedIn = isSignedIn;

        userImage = (ImageView) mNavigationView.findViewById(R.id.user_acc_image);
        userName = (TextView) mNavigationView.findViewById(R.id.user_name);
        userEmail = (TextView) mNavigationView.findViewById(R.id.user_email);

        if (isSignedIn) {
            ProfileImageLoader loader = new ProfileImageLoader(userImage);
            loader.execute(String.valueOf(mAccount.getPhotoUrl()));
            userName.setText(mAccount.getDisplayName());
            userEmail.setText(mAccount.getEmail());
            userId = mAccount.getId();
        } else {
            userImage.setImageDrawable(null);
            userName.setText("");
            userEmail.setText("Por favor,entre com sua conta Google.");
        }
    }

    private void updateSignInOutBtns() {
        if (isSignedIn) {
            signInBtn.setVisible(false);
            signOutBtn.setVisible(true);
        } else {
            signInBtn.setVisible(true);
            signOutBtn.setVisible(false);
        }
    }

    public String getUserEmail() {
        if (mAccount != null)
            return mAccount.getEmail();
        else
            return null;
    }

    public String getUserID() {
        if (mAccount != null)
            return mAccount.getId();
        else
            return null;
    }
}

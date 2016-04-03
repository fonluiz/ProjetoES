package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.R;

/**
 * Fragment da tela de Mostrar informações do perfil
 */
public class ProfileFragment extends Fragment {

    public final static String TAG = "PROFILE_FRAGMENT";
    private View mView;
    OnEditProfileFabClickedListener editProfileCallback;

    private ImageView profileImage;
    private FloatingActionButton editProfileFab;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_profile, container, false);
        // Define o título na actionbar
        ((LostFound) this.getActivity()).getSupportActionBar().setTitle("Meu Perfil");

        startImageprofile();
        startEditProfileFab();

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            editProfileCallback = (OnEditProfileFabClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnEditProfileFabClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        editProfileCallback = null;
    }

    /**
     * Define o campo da imagem do perfil. Caso seja necessário.
     */
    private void startImageprofile() {
        profileImage = (ImageView) mView.findViewById(R.id.image_profile);
    }

    /**
     * Inicia as definições para quando o botão de editar perfil for clicado.
     */
    private void startEditProfileFab() {
        editProfileFab = (FloatingActionButton) mView.findViewById(R.id.user_settings_fab);
        editProfileFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileCallback.onFabClicked();
            }
        });
    }

    // Container Activity must implement this interface
    public interface OnEditProfileFabClickedListener {
        void onFabClicked();
    }
}

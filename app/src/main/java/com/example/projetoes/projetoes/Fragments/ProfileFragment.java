package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.BD.DBUtils;
import com.example.projetoes.projetoes.Models.Usuario;
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
    private TextView username_field;
    private TextView bairro_field;
    private TextView street_field;
    private TextView phone_field;
    private TextView email_field;

    private Uri userImage;
    private String username;
    private String bairro;
    private String street;
    private String phone;
    private String email;

    public SharedPreferences userData;


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

        startFields();
        startEditProfileFab();
        setFields();


        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setFields();
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

    private void startFields() {
        profileImage = (ImageView) mView.findViewById(R.id.image_profile);
        username_field = (TextView) mView.findViewById(R.id.username);
        bairro_field = (TextView) mView.findViewById(R.id.user_bairro);
        street_field = (TextView) mView.findViewById(R.id.user_street);
        phone_field = (TextView) mView.findViewById(R.id.user_phone);
        email_field = (TextView) mView.findViewById(R.id.user_email);
    }

    private void setFields() {
        userData = getActivity().getPreferences(Context.MODE_PRIVATE);

        String defaultImage = String.valueOf(getResources().getDrawable(R.drawable.ic_default_user_img));
        userImage = Uri.parse(userData.getString("foto", defaultImage));

        username = userData.getString("username", "Nome do usuário");
        username_field.setText(username);

        bairro = userData.getString("bairro", "");
        bairro_field.setText(bairro);

        street = userData.getString("rua", "");
        street_field.setText(street);

        phone = userData.getString("telefone", "");
        phone_field.setText(phone);

        email = ((LostFound) getActivity()).getUser().getEmail();
        email_field.setText(email);

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

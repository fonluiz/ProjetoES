package com.example.projetoes.projetoes.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.BD.DBUtils;
import com.example.projetoes.projetoes.Models.Usuario;
import com.example.projetoes.projetoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    public final static String TAG = "EDIT_PROFILE_FRAGMENT";
    public static final int REQUEST_IMAGE_GET = 1;

    private View mView;
    private ImageView photoSelector;
    private EditText username_field;
    private EditText bairro_field;
    private EditText street_field;
    private EditText phone1_field;
    private EditText email_field;

    private Uri userImage;
    private String username;
    private String bairro;
    private String street;
    private String phone1;
    private String email;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        modifyActioonBar();
        startPhotoSelector();
        startFields();

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.save_profile_information, menu);
    }


    @Override
    public void onResume() {
        modifyActioonBar();
        super.onResume();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save_profile_info) {
            saveInformations();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inicia o botão de selecionar imagem e define o listener para ele
     */
    private void startPhotoSelector() {
        photoSelector = (ImageView) mView.findViewById(R.id.edit_profile_image);
        photoSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    /**
     * Abre uma nova tela para o usuário selecionar uma foto de qualquer aplicativo
     * do dispositivo que possa receber esse tipo de intenção
     */
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        Bundle extras = new Bundle();
        extras.putBoolean("EXTRA_ALLOW_MULTIPLE", false);
        extras.putBoolean("EXTRA_LOCAL_ONLY", true);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                getActivity().startActivityForResult(intent, REQUEST_IMAGE_GET, extras);
            }
        }
    }

    /**
     * Uma vez selecionada a foto, este método deve lidar com o arquivo retornado por selectImage
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == getActivity().RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            // Do work with photo saved at fullPhotoUri
            userImage = fullPhotoUri;
            photoSelector.setImageURI(userImage);
        }
    }

    /**
     * Inicia as definições da ActionBar para esse fragment
     */
    private void modifyActioonBar() {
        android.support.v7.app.ActionBar mActionbar = ((LostFound) this.getActivity()).getSupportActionBar();
        mActionbar.setTitle(R.string.editProfileTitle);
        ((LostFound) getActivity()).setDrawerState(false);
        mActionbar.setDisplayHomeAsUpEnabled(true);
    }

    private void startFields() {
        username_field = (EditText) mView.findViewById(R.id.username_field);
        bairro_field = (EditText) mView.findViewById(R.id.bairro_field);
        street_field = (EditText) mView.findViewById(R.id.street_field);
        phone1_field = (EditText) mView.findViewById(R.id.phone1_field);
        email_field = (EditText) mView.findViewById(R.id.email_field);
    }

    private void saveInformations() {
        username = String.valueOf(username_field.getText());
        bairro = String.valueOf(bairro_field.getText());
        street = String.valueOf(street_field.getText());
        phone1 = String.valueOf(phone1_field.getText());
        email = ((LostFound) getActivity()).getUserEmail();

        if (email == null) {
            Toast.makeText(getContext(), "É necessário entrar com sua conta Google!", Toast.LENGTH_LONG);
            return;
        }

        if (!isAllFieldsFilledOut()) {
            Toast.makeText(getContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        } else {
            Usuario user = new Usuario(String.valueOf(userImage),username,bairro,street,phone1,email);
            DBUtils.addUserInformation(getContext(), user);
            getActivity().onBackPressed();
            Toast.makeText(getContext(), "Informações publicadas", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isAllFieldsFilledOut() {
        if (username == null || userImage == null || bairro == null || street == null ||
                phone1 == null || email == null)
            return false;
        else
            return true;
    }
}

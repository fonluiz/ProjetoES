package com.example.projetoes.projetoes.Fragments;


import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
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
import com.example.projetoes.projetoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

    public final static String TAG = "EDIT_PROFILE_FRAGMENT";
    static final int REQUEST_IMAGE_GET = 1;

    private View mView;
    private ImageView photoSelector;
    private Uri objImage;
    private EditText username_field;
    private EditText bairro_field;
    private EditText street_field;
    private EditText phone1_field;
    private EditText phone2_field;
    private EditText email_field;

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
            persistInformations();
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
            startActivityForResult(intent, REQUEST_IMAGE_GET, extras);
        }
    }

    /**
     * Uma vez selecionada a foto, este método deve lidar com o arquivo retornado por selectImage
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == getActivity().RESULT_OK) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            Uri fullPhotoUri = data.getData();
            // Do work with photo saved at fullPhotoUri
            objImage = fullPhotoUri;
            photoSelector.setImageBitmap(thumbnail);
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
        phone2_field = (EditText) mView.findViewById(R.id.phone2_field);
        email_field = (EditText) mView.findViewById(R.id.email_field);
    }

    private void persistInformations() {
        //TODO: salva as informações no banco de dados
    }
}

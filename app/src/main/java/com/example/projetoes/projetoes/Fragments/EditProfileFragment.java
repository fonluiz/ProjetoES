package com.example.projetoes.projetoes.Fragments;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        modifyActioonBar();
        startPhotoSelector();

        return mView;
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
            Log.d(TAG, "onActivityResult:########### " + objImage);
            photoSelector.setImageBitmap(thumbnail);
        }
    }

    /**
     * Inicia as definições da ActionBar para esse fragment
     */
    private void modifyActioonBar() {
        android.support.v7.app.ActionBar mActionbar = ((LostFound) this.getActivity()).getSupportActionBar();
        mActionbar.setTitle(R.string.editProfileTitle);
    }

}

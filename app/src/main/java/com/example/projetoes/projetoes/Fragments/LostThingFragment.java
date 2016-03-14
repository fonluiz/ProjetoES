package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.projetoes.projetoes.R;

import java.text.NumberFormat;

/**
* Fragment da tela para divulgar um objeto perdido.
*/
public class LostThingFragment extends Fragment implements OnFragmentInteractionListener {

    public final static String TAG = "LOST_THING_FRAGMENT";
    static final int REQUEST_IMAGE_GET = 1;

    private OnFragmentInteractionListener mListener;
    private Spinner categorySpinner;
    private View mView;
    private DatePicker datePicker;
    private ImageButton photoSelector;
    private EditText rewardField;

    public LostThingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LostThingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LostThingFragment newInstance(String param1, String param2) {
        LostThingFragment fragment = new LostThingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_lost_thing, container, false);
        startCategorySpinner();
        startPhotoSelector();
        startRewardField();

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Insere as categorias no spinner de categorias
    */
    private void startCategorySpinner() {
        categorySpinner = (Spinner) mView.findViewById(R.id.category_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        categorySpinner.setAdapter(adapter);
    }

    /**
     * Inicia o botão de selecionar imagem e define o listener para ele
     */
    private void startPhotoSelector() {
        int ImagePosition = categorySpinner.getSelectedItemPosition();
        Drawable itemImage;
        switch (ImagePosition) {
            // continuar aqui
        }
        photoSelector = (ImageButton) mView.findViewById(R.id.photo_selector);
        photoSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void startRewardField() {
        rewardField = (EditText) mView.findViewById(R.id.reward_field);
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
        }
    }
}

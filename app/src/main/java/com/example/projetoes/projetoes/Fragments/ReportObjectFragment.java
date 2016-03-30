package com.example.projetoes.projetoes.Fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;

import java.util.GregorianCalendar;

/**
* Fragment da tela para divulgar um objeto perdido ou encontrado.
*/
public class ReportObjectFragment extends Fragment {

    public final static String TAG = "LOST_THING_FRAGMENT";
    static final int REQUEST_IMAGE_GET = 1;

    private View mView;
    private Status mStatus;

    private Spinner categorySpinner;
    private EditText locationField;
    private EditText dateField;
    private DialogFragment mDatePickerFragment;
    private EditText descriptionField;
    private ImageView photoSelector;
    private EditText rewardField;
    private FloatingActionButton imageFAB;

    private String category;
    private String location;
    private GregorianCalendar lossDate;
    private String description;
    private Uri objImage;

    public ReportObjectFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LostThingFragment.
     */
    public static ReportObjectFragment newInstance(Status status) {
        if (status == Status.DEVOLVIDO) {
            throw new IllegalArgumentException("Argument " + status.name() + " is not allowed.");
        } else {
            ReportObjectFragment fragment = new ReportObjectFragment();
            Bundle args = new Bundle();
            args.putString("status", status.name());
            fragment.setArguments(args);
            return fragment;
        }
    }

    public Status getStatus() {
        return this.mStatus;
    }

    /**
     * Este método define o status do fragment (encontrado ou perdido).
     * Algumas funcionalidades mudam no fragmente de acordo com o seu status.
     */
    public void setStatus() {
        if (this.getArguments().get("status") == Status.ENCONTRADO.name()) {
            mStatus = Status.ENCONTRADO;
        } else {
            mStatus = Status.PERDIDO;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        this.setStatus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.report_object_fragment, container, false);
        setActionbarTitle();

        startCategorySpinner();
        startLocationField();
        startDatePicker();
        startDescriptionField();
        startPhotoSelector();
        startRewardField();

        return mView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Se for ter um menu na action bar, inflar o layout aqui.
        // Se não tiver, pode excluir esse método.
        //getActivity().getMenuInflater().inflate(R.menu.lost_thing_menu, menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Define o título que aparecerá na actionbar
     */
    private void setActionbarTitle() {
        if (mStatus.equals(Status.PERDIDO)) {
            ((LostFound) this.getActivity()).getSupportActionBar().setTitle("Perdi Algo");
        } else {
            // Define o título na actionbar
            ((LostFound) this.getActivity()).getSupportActionBar().setTitle("Achei Algo");
        }
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
     * Inicia as definições para o campo de localização
     */
    private void startLocationField() {
        locationField = (EditText) mView.findViewById(R.id.lost_location_field);
    }

    /**
     * Inicia as definições para o seletor de data
     */
    private void startDatePicker() {
        dateField = (EditText) mView.findViewById(R.id.date_field);
        mDatePickerFragment = new DatePickerFragment();
        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerFragment.show(getChildFragmentManager(), DatePickerFragment.TAG);
            }
        });
    }

    /**
     * Inicia as definições para o campo de descrição
     */
    private void startDescriptionField() {
        descriptionField = (EditText) mView.findViewById(R.id.lost_description_field);
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
        photoSelector = (ImageView) mView.findViewById(R.id.image_field);
        imageFAB = (FloatingActionButton) mView.findViewById(R.id.image_selector_fab);
        imageFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    /**
     * Inicia as definições para o campo de recompensa
     */
    private void startRewardField() {
        rewardField = (EditText) mView.findViewById(R.id.reward_field);
        LinearLayout rewardContainer = (LinearLayout) mView.findViewById(R.id.reward_field_container);
        if (this.mStatus.equals(Status.ENCONTRADO)) {
            rewardContainer.setVisibility(View.GONE);
        }
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
            photoSelector.setImageURI(objImage);
        }
    }

    /**
     * Define os dados a serem inseridos na base de dados
     */
    private void onFinishedFillingOut() {
        category = categorySpinner.getSelectedItem().toString();
        location = locationField.getText().toString();
        description = descriptionField.getText().toString();

    }

}

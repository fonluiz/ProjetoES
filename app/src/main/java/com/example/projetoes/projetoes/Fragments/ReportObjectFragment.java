package com.example.projetoes.projetoes.Fragments;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.BD.DBUtils;
import com.example.projetoes.projetoes.Models.Objeto;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;
import com.example.projetoes.projetoes.Utils.ProfileImageLoader;

import java.util.GregorianCalendar;

/**
 * Fragment da tela para divulgar um objeto perdido ou encontrado.
 */
public class ReportObjectFragment extends Fragment {

    public final static String TAG = "LOST_THING_FRAGMENT";
    public static final int REQUEST_IMAGE_GET = 1;

    private View mView;
    private Status mStatus;

    private ImageView photoSelector;
    private Spinner categorySpinner;
    private EditText tipoField;
    private EditText locationField;
    private EditText dateField;
    private DialogFragment mDatePickerFragment;
    private EditText descriptionField;
    private EditText rewardField;
    private FloatingActionButton imageFAB;
    private Button publishButton;

    private int idObjeto;
    private String usuario;
    private Uri foto;
    private String categoria;
    private String tipo;
    private String descricao;
    private String local;
    private String data;
    private double recompensa;
    private String status;

    public SharedPreferences userData;

    public ReportObjectFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
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
    private void setStatus() {
        if (this.getArguments().get("status") == Status.ENCONTRADO.name()) {
            mStatus = Status.ENCONTRADO;
        } else {
            mStatus = Status.PERDIDO;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ;
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
        startPublishButton();

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
        tipoField = (EditText) mView.findViewById(R.id.tipo_field);
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
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        Bundle extras = new Bundle();
        extras.putBoolean("EXTRA_ALLOW_MULTIPLE", false);
        extras.putBoolean("EXTRA_LOCAL_ONLY", true);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivityForResult(intent, REQUEST_IMAGE_GET, extras);
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
            foto = fullPhotoUri;
            photoSelector.setImageURI(foto);
        }
    }

    private void startPublishButton() {
        publishButton = (Button) mView.findViewById(R.id.publish_btn);
        publishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinishedFillingOut();
            }
        });
    }

    /**
     * Define os dados a serem inseridos na base de dados
     */
    private void onFinishedFillingOut() {
        userData = getActivity().getPreferences(EditProfileFragment.USER_PREFERENCES);

        usuario = userData.getString("username", "Nome do usuário");
        categoria = categorySpinner.getSelectedItem().toString();
        tipo = String.valueOf(tipoField.getText());
        descricao = String.valueOf(descriptionField.getText());
        local = String.valueOf(locationField.getText());
        data = String.valueOf(dateField.getText());
        if (!String.valueOf(rewardField.getText()).equals(""))
            recompensa = Double.parseDouble(String.valueOf(rewardField.getText()));
        else
            recompensa = 0.0;
        status = mStatus.name();
        idObjeto = descricao.hashCode() + tipo.hashCode() - data.hashCode() + usuario.hashCode();

        if(isTodosCamposPreenchidos()) {
            Objeto obj = new Objeto(idObjeto, usuario, foto, categoria, tipo, descricao, local, data, recompensa, status);
            DBUtils.addItemToLostFound(getContext(), obj);
            ((LostFound) getActivity()).addListaObjachadosPerdidos();

        } else {
            Toast.makeText(getContext(), "Por favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isTodosCamposPreenchidos() {
        if (usuario == null || tipo.equals("") || descricao.equals("") || local.equals("") ||
                data.equals("") || rewardField.getText().equals("") || foto == null)
            return false;
        else
            return true;
    }

}

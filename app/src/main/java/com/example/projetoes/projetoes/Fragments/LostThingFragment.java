package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.projetoes.projetoes.R;

/**
* Fragment da tela para divulgar um objeto perdido.
*/
public class LostThingFragment extends Fragment implements OnFragmentInteractionListener {

    public final static String TAG = "LOST_THING_FRAGMENT";

    private OnFragmentInteractionListener mListener;
    private Spinner categorySpinner;
    private View mView;
    private DateSetterFragment dateSetterFragment;

    public LostThingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
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

        //comentado pois está dando erro
        //showDatePickerDialog(mView);

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
     * Povoa o spinner de categorias
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

    public void showDatePickerDialog(View v) {
        dateSetterFragment = new DateSetterFragment();
        dateSetterFragment.show(getChildFragmentManager(), DateSetterFragment.TAG);
    }

}

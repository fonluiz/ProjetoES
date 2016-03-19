package com.example.projetoes.projetoes.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.projetoes.projetoes.R;

import java.util.Calendar;

/**
 * Fragment que representa um DatePickerDialog, que aparece ao clicar no campo
 * "data" ao preencher os formulários.
 */
public class DatePickerFragment extends DialogFragment
        implements android.app.DatePickerDialog.OnDateSetListener{

    public final static String TAG = "DATE_PICKER_FRAGMENT";
    private TextView dateField;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getParentFragment().getContext(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        dateField = (TextView) getParentFragment().getView().findViewById(R.id.date_field);
        dateField.setText(day+"/"+(month+1)+"/"+year);
    }

}

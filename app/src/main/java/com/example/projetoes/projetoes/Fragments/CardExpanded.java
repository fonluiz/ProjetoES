package com.example.projetoes.projetoes.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoes.projetoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardExpanded extends Fragment {

    public final static String TAG = "CARD_EXPANDED";

    public CardExpanded() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_expanded, container, false);
    }

}

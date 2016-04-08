package com.example.projetoes.projetoes.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardExpanded extends Fragment {

    public final static String TAG = "CARD_EXPANDED";

    private Status mStatus;
    private View mView;
    private Button reportBtn;
    private LinearLayout rewardContainer;

    public CardExpanded() {
        // Required empty public constructor
    }

    public static CardExpanded newInstance(Status status) {
        if (status == Status.DEVOLVIDO) {
            throw new IllegalArgumentException("Argument " + status.name() + " is not allowed.");
        } else {
            CardExpanded fragment = new CardExpanded();
            Bundle args = new Bundle();
            args.putString("status", status.name());
            fragment.setArguments(args);
            return fragment;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setStatus();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_card_expanded, container, false);

        setActionbarTitle();
        startReportButton();
        startRewardField();

        return mView;
    }

    private void setActionbarTitle() {
        if (mStatus.equals(Status.PERDIDO)) {
            ((LostFound) this.getActivity()).getSupportActionBar().setTitle("Item Perdido");
        } else {
            // Define o título na actionbar
            ((LostFound) this.getActivity()).getSupportActionBar().setTitle("Item Achado");
        }
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

    private void startReportButton() {
        reportBtn = (Button) mView.findViewById(R.id.report_btn);
        if (mStatus.equals(Status.ENCONTRADO))
            reportBtn.setText("Perdi este objeto");
        else
            reportBtn.setText("Achei seu objeto");
    }

    private void startRewardField() {
        rewardContainer = (LinearLayout) mView.findViewById(R.id.reward_field_container);
        if (this.mStatus.equals(Status.ENCONTRADO)) {
            rewardContainer.setVisibility(View.GONE);
        }
    }
}

package com.example.projetoes.projetoes.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.Models.Objeto;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardExpanded extends Fragment {

    public final static String TAG = "CARD_EXPANDED";

    private Status mStatus;
    private View mView;
    private ImageView mImgView;
    private TextView title;
    private TextView category;
    private TextView location;
    private TextView tittle;
    private TextView date;
    private TextView description;
    private TextView reward;
    private List<Objeto> mList;
    private Button reportBtn;
    private LinearLayout rewardContainer;
    private int position;

    public CardExpanded() {
        // Required empty public constructor
    }

    public static CardExpanded newInstance(Status status, int position) {
        if (status == Status.DEVOLVIDO) {
            throw new IllegalArgumentException("Argument " + status.name() + " is not allowed.");
        } else {
            CardExpanded fragment = new CardExpanded();
            Bundle args = new Bundle();
            args.putInt("position", position);
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

        this.position = getArguments().getInt("position");
        setActionbarTitle();
        startReportButton();
        startRewardField();
        statrObjectInformation();

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

    private void statrObjectInformation(){

        mImgView = (ImageView) mView.findViewById(R.id.obj_image);
        title = (TextView) mView.findViewById(R.id.title);
        category = (TextView) mView.findViewById(R.id.category);
        location = (TextView) mView.findViewById(R.id.location);
        date = (TextView) mView.findViewById(R.id.date);
        description = (TextView) mView.findViewById(R.id.description_field);
        reward = (TextView) mView.findViewById(R.id.reward_field);
        if (mStatus.equals(Status.PERDIDO)) {
            mList = ((LostFound) getActivity()).getObjPerdidos();
            Objeto obj = mList.get(position);
            reward.setText(obj.getRecompensa()+"");
        }
        else {
            mList = ((LostFound) getActivity()).getObjAchados();
        }
        Objeto obj = mList.get(position);
        mImgView.setImageURI(obj.getFoto());
        title.setText(obj.getTipo());
        category.setText(obj.getCategoria());
        location.setText(obj.getLocal());
        date.setText(obj.getData());
        description.setText(obj.getDescricao());





    }

    private void startRewardField() {
        rewardContainer = (LinearLayout) mView.findViewById(R.id.reward_field_container);
        if (this.mStatus.equals(Status.ENCONTRADO)) {
            rewardContainer.setVisibility(View.GONE);
        }
    }
}

package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.Adapters.LostFeedCardAdapter;
import com.example.projetoes.projetoes.Interfaces.OnCardClickedListener;
import com.example.projetoes.projetoes.Interfaces.RecycleViewOnClickListener;
import com.example.projetoes.projetoes.Models.Card;
import com.example.projetoes.projetoes.Models.Status;
import com.example.projetoes.projetoes.R;

import java.util.List;

/**
 * Um Fragment para mostrar uma lista com todos os objetos perdidos.
 */
public class LostItemFeed extends Fragment implements RecycleViewOnClickListener, OnCardClickedListener {

    public final static String TAG = "LOST_ITEM_FEED";
    public final static Status status = Status.PERDIDO;

    private RecyclerView mRecycleView;
    private List<Card> mList;
    private View mview;

    private OnCardClickedListener expandCardCallBack;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LostItemFeed() {
    }


    public static LostItemFeed newInstance() {
        LostItemFeed fragment = new LostItemFeed();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_lostitem, container, false);
        startAdapter();

        return mview;
    }

    @Override
    public void onResume() {
        startAdapter();
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            expandCardCallBack = (OnCardClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onCardClickedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void startAdapter() {
        mRecycleView = (RecyclerView) mview.findViewById(R.id.card_list_lost);
        mRecycleView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(llm);

        mList = ((LostFound) getActivity()).getCardLostList();
        LostFeedCardAdapter adapter = new LostFeedCardAdapter(getActivity(),mList);
        adapter.setRecycleViewOnClickListener(this);
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void onClickListener(View view, int position) {
        onCardClicked(status);
    }

    @Override
    public void onCardClicked(Status status) {
        expandCardCallBack.onCardClicked(status);
    }
}

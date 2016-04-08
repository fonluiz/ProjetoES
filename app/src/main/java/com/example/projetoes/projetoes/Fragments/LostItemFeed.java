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
import com.example.projetoes.projetoes.Interfaces.RecycleViewOnClickListener;
import com.example.projetoes.projetoes.Models.Card;
import com.example.projetoes.projetoes.R;

import java.util.List;

/**
 * Um Fragment para mostrar uma lista com todos os objetos perdidos.
 */
public class LostItemFeed extends Fragment implements RecycleViewOnClickListener {

    public final static String TAG = "LOST_ITEM_FEED";
    private RecyclerView mRecycleView;
    private List<Card> mList;
    private View mview;

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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void startAdapter() {
        mRecycleView = (RecyclerView) mview.findViewById(R.id.card_list_lost);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) mRecycleView.getLayoutManager();
                LostFeedCardAdapter adapter = (LostFeedCardAdapter) mRecycleView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Card> listAux = ((LostFound) getActivity()).getCardLostList(6);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(llm);

        mList = ((LostFound) getActivity()).getCardLostList(6);
        LostFeedCardAdapter adapter = new LostFeedCardAdapter(getActivity(),mList);
        adapter.setRecycleViewOnClickListener(this);
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void onClickListener(View view, int position) {

    }
}

package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetoes.projetoes.Activities.LostFound;
import com.example.projetoes.projetoes.Adapters.CardAdapter;
import com.example.projetoes.projetoes.Interfaces.RecycleViewOnClickListener;
import com.example.projetoes.projetoes.Models.Card;
import com.example.projetoes.projetoes.R;

import java.util.List;

/**
 * Um Fragment para mostrar uma lista com todos os objetos encontrados.
 */
public class FoundItemFeed extends Fragment implements RecycleViewOnClickListener{


    public final static String TAG = "FOUND_ITEM_FEED";
    private RecyclerView mRecycleView;
    private List<Card> mList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FoundItemFeed() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FoundItemFeed newInstance() {
        FoundItemFeed fragment = new FoundItemFeed();
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
        View view = inflater.inflate(R.layout.fragment_founditem, container, false);

        mRecycleView = (RecyclerView) view.findViewById(R.id.card_list);
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
                CardAdapter adapter = (CardAdapter) mRecycleView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Card> listAux = ((LostFound) getActivity()).getCardList(6);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(llm);

        mList = ((LostFound) getActivity()).getCardList(6);
        CardAdapter adapter = new CardAdapter(getActivity(),mList);
        adapter.setRecycleViewOnClickListener(this);
        mRecycleView.setAdapter(adapter);

        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position "+position, Toast.LENGTH_SHORT).show();

    }

}

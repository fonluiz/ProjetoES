package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoes.projetoes.R;
import com.example.projetoes.projetoes.Fragments.dummy.DummyContent;

/**
 * Um Fragment para mostrar uma lista com todos os objetos encontrados.
 */
public class FoundItemFeed extends Fragment  implements OnListFragmentInteractionListener{


    public final static String TAG = "FOUND_ITEM_FEED";

    private OnListFragmentInteractionListener mListener;

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

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setAdapter(new FeedRecyclerViewAdapter(DummyContent.ITEMS, null)); //Aqui deveria ser o listener ao invés de null
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //TODO: Este metodo precisa ser implementado. Coloquei aqui so pra nao dar erro.
    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

}

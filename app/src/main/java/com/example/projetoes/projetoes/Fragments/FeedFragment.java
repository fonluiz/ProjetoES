package com.example.projetoes.projetoes.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetoes.projetoes.R;

/**
 * Fragment para o feed inicial do Perdidos Achados. Esta classe gerencia
 * mais dois fragmentos: FoundItemFeed e LostItemFeed
 */
public class FeedFragment extends Fragment {

    public final static String TAG = "FEED_FRAGMENT";

    private OnFragmentInteractionListener mListener;

    private FoundItemFeed foundItemFeed;
    private LostItemFeed lostItemFeed;

    private FeedSwipeAdapter mAdapter;
    private ViewPager mPager;

    public FeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FeedFragment newInstance() {
        FeedFragment fragment = new FeedFragment();
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

        View feed_view = inflater.inflate(R.layout.fragment_feed, container, false);

        foundItemFeed = new FoundItemFeed();
        lostItemFeed = new LostItemFeed();

        mAdapter = new FeedSwipeAdapter(getChildFragmentManager());
        mPager = (ViewPager) feed_view.findViewById(R.id.feed_pager);
        mPager.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return feed_view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

}

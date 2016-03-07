package com.example.projetoes.projetoes.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by luiz on 06/03/16.
 */
public class FeedSwipeAdapter extends FragmentPagerAdapter {

    static final int NUM_ITEMS = 2;

    public FeedSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */
    @Override
    public Fragment getItem(int position)
    {
        switch (position){
            case 0 : return new FoundItemFeed();
            case 1 : return new LostItemFeed();
        }
        return null;
    }

    @Override
    public int getCount() {

        return NUM_ITEMS;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Achados";
            case 1 :
                return "Perdidos";
        }
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);

    }

}


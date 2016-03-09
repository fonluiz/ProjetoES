package com.example.projetoes.projetoes.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * Classe que serve como Adapter da ViewPager do FeedFragment.
 * Este Adapter é o reponsável por fazera troca entre lista de objetos encontrados
 * e lista de objetos perdidos no feed inicial.
 */
public class FeedSwipeAdapter extends FragmentPagerAdapter {

    static final int NUM_ITEMS = 2;

    public FeedSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Retorna o Fragment da respectiva posição
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

    /**
     * Retorna o número de Fragments que serão mostrados na ViewPager
     */
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    /**
     * Este método retorna o título da tab de acordo com a posição.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Itens achados";
            case 1 :
                return "Itens perdidos";
        }
        return null;
    }

}


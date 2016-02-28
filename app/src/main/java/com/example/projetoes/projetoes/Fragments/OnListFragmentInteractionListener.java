package com.example.projetoes.projetoes.Fragments;

import com.example.projetoes.projetoes.Fragments.dummy.DummyContent;

/**
 * Created by Samir Silva e Luiz Alberto on 27/02/16.
 * Interface que gerencia as açoes de clique nos itens do feed principal.
 *
 */
public interface OnListFragmentInteractionListener {

    /**
     * Lida com a iteração de cliques nos itens do List Fragment
     * @param item O item que recebeu uma ação, um toque simples por exemplo.
     */
    public void onListFragmentInteraction(DummyContent.DummyItem item);
}

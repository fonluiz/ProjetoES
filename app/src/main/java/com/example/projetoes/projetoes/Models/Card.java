package com.example.projetoes.projetoes.Models;

/**
 * Created by samirsmedeiros on 05/04/16.
 */
public class Card {

    private String titulo;
    private String bairro;
    private int foto;

    public Card(String titulo, String bairro, int foto){
        this.titulo = titulo;
        this.bairro = bairro;
        this.foto = foto;
    }

    public String getTitulo(){
        return titulo;
    }
    public String getBairro(){
        return bairro;
    }

    public int getFoto(){
        return foto;
    }
}

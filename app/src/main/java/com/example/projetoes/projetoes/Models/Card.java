package com.example.projetoes.projetoes.Models;

/**
 * Created by samirsmedeiros on 05/04/16.
 */
public class Card {

    private String titulo;
    private String bairro;
    private int foto;
    private String recompensa;

    public Card(String titulo, String bairro, int foto){
        this.titulo = titulo;
        this.bairro = bairro;
        this.foto = foto;
    }

    public Card(String titulo, String bairro, int foto, String recompensa){
        this.titulo = titulo;
        this.bairro = bairro;
        this.foto = foto;
        this.recompensa = recompensa;
    }

    public String getTitulo(){
        return titulo;
    }
    public String getBairro(){
        return bairro;
    }

    public String getRecompensa() {
        return recompensa;
    }

    public int getFoto(){
        return foto;
    }
}

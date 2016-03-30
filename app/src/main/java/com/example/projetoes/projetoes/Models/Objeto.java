package com.example.projetoes.projetoes.Models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Tuca on 07/03/2016.
 * Classe de persistência, objeto que pode ser perdido ou achado pelo usuário
 */
public class Objeto {
    private UUID id;
    private String titulo;
    private String descricao;
    private Categoria categoria;
    private Date dataPublicacao;
    private float recompensa;
    private String lugar;
    private String foto;
    private Status status;

    /**
     * getters e setters
     */
    public void setId(UUID id){
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setRecompensa(float recompensa) {
        this.recompensa = recompensa;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UUID getId(){
        return id;
    }
    public String getTitulo() {

        return titulo;
    }
    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public float getRecompensa() {
        return recompensa;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFoto() {
        return foto;
    }

    public Status getStatus() {
        return status;
    }
    /*
    Construtor vazio do Objeto
     */
    public Objeto(){

    }
    /*
    Construtor do objeto com os parâmetros que não podem ser nulos
     */
    public Objeto( String titulo, String descricao,
            Categoria categoria,String lugar, String foto,Status status){
        setTitulo(titulo);
        setDescricao(descricao);
        setCategoria(categoria);
        setLugar(lugar);
        setFoto(foto);
        setStatus(status);
        this.id = (UUID) generateId();
        this.dataPublicacao = new Date();
    }

    /*
    Função que gera um id qualquer
     */
    UUID generateId(){ //gerar o Id único
        return UUID.randomUUID();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Objeto))
            return false;

        Objeto outro = (Objeto) obj;

        return this.getId() == outro.getId();
    }

}

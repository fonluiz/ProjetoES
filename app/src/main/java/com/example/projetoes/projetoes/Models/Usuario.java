package com.example.projetoes.projetoes.Models;

import java.util.Set;
import java.util.UUID;
/**
 * Created by Tuca on 07/03/2016.
 */
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String contato;
    private String foto;
    private int flags;
    private Set<Objeto> achados;
    private Set<Objeto> perdidos;



    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setContato(String contato) {   //não seria melhor uma classe ou estrutura de dados como map para guardar contato? email não já é um contato?
        this.contato = contato;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getNome() {

        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getContato() {
        return contato;
    }

    public String getFoto() {
        return foto;
    }

    public int getFlags() {
        return flags;
    }

    public Set<Objeto> getAchados() {
        return achados;
    }

    public Set<Objeto> getPerdidos() {
        return perdidos;
    }

    public void criarObjeto(String titulo, String descricao,
                            Categoria categoria,String lugar, String foto,Status status){
        Objeto obj = new Objeto(titulo,descricao,categoria,lugar,foto,status);
        if (status == Status.ENCONTRADO){
            addAchados(obj);
        }elseif (status == Status.PERDIDO){
            addPerdidos(obj);
        }
    }
    void addAchados(Objeto objeto){
        this.achados.add(objeto);
    }
    void addPerdidos(Objeto objeto){
        this.perdidos.add(objeto);
    }

    UUID generateId(){ //gerar o Id único
        return UUID.randomUUID();
    }

    public Usuario(String nome, String email, String senha, String contato) {
        setNome(nome);
        setEmail(email);
        setSenha(senha);
        setContato(contato);
        this.id = generateId();
    }
}

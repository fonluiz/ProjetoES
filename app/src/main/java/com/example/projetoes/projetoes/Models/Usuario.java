package com.example.projetoes.projetoes.Models;

import android.net.Uri;

import java.net.URI;
import java.util.UUID;

public class Usuario {

    private final String CIDADE = "Campina Grande";
    private String photo;
    private String username;
    private String bairro;
    private String rua;
    private String telefone;
    private String email;


    public Usuario(String photo, String username, String bairro, String rua, String telefone, String email) {
        this.photo = photo;
        this.username = username;
        this.bairro = bairro;
        this.rua = rua;
        this.telefone = telefone;
        this.email = email;
    }


    public String getCIDADE() {
        return CIDADE;
    }

    public int getId() {
        return hashCode();
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getTelefone1() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Usuario))
            return false;
        else
            return this.getId() == ((Usuario) obj).getId();
    }

    @Override
    public int hashCode() {
        int result = getCIDADE().hashCode();
        result = 31 * result + getPhoto().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getBairro().hashCode();
        result = 31 * result + getRua().hashCode();
        result = 31 * result + telefone.hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}

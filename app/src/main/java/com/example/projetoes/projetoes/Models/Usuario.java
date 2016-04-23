package com.example.projetoes.projetoes.Models;

import java.net.URI;
import java.util.UUID;

public class Usuario {

    private final String CIDADE = "Campina Grande";
    private UUID id;
    private URI photo;
    private String username;
    private String bairro;
    private String rua;
    private String telefone1;
    private String telefone2;
    private String email;


    public Usuario(URI photo, String username, String bairro, String rua, String telefone1, String telefone2, String email) {
        this.photo = photo;
        this.username = username;
        this.bairro = bairro;
        this.rua = rua;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.email = email;
    }

    public String getCIDADE() {
        return CIDADE;
    }

    public int getId() {
        return hashCode();
    }


    public URI getPhoto() {
        return photo;
    }

    public void setPhoto(URI photo) {
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
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
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
        return id.hashCode();
    }
}

package com.example.projetoes.projetoes.Models;

import android.net.Uri;

public class Objeto {

    private int id;
    private String idUsuario;
    private Uri foto;
    private String categoria;
    private String tipo;
    private String descricao;
    private String local;
    private String data;
    private double recompensa;
    private String status;

    public Objeto(int id, String idUsuario, Uri foto, String categoria, String tipo, String descricao,
                  String local, String data, double recompensa, String status) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.foto = foto;
        this.categoria = categoria;
        this.tipo = tipo;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.recompensa = recompensa;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrição) {
        this.descricao = descrição;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(double recompensa) {
        this.recompensa = recompensa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Objeto objeto = (Objeto) o;

        if (getId() != objeto.getId()) return false;
        if (getIdUsuario() != objeto.getIdUsuario()) return false;
        if (getCategoria() != null ? !getCategoria().equals(objeto.getCategoria()) : objeto.getCategoria() != null)
            return false;
        return getStatus() != null ? getStatus().equals(objeto.getStatus()) : objeto.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (getIdUsuario() != null ? getIdUsuario().hashCode() : 0);
        result = 31 * result + (getCategoria() != null ? getCategoria().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}

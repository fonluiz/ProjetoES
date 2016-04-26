package com.example.projetoes.projetoes.Models;

import com.parse.ParseObject;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public class Objeto {

    private URI foto;
    private Categoria categoria;
    private String descricao;
    private String local;
    private Date data;
    private double recompensa;
    private Status status;

    public Objeto(URI foto, Categoria categoria, String descricao, String local, Date data, double recompensa, Status status) {
        this.foto = foto;
        this.categoria = categoria;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.recompensa = recompensa;
        this.status = status;
    }

    public Objeto(ParseObject){
        /* TODO */
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return hashCode();
    }

    public URI getFoto() {
        return foto;
    }

    public void setFoto(URI foto) {
        this.foto = foto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(double recompensa) {
        this.recompensa = recompensa;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Objeto))
            return false;
        else
            return this.getId() == ((Objeto) obj).getId();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getFoto().hashCode();
        result = 31 * result + getCategoria().hashCode();
        result = 31 * result + getDescricao().hashCode();
        result = 31 * result + getLocal().hashCode();
        result = 31 * result + getData().hashCode();
        temp = Double.doubleToLongBits(getRecompensa());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getStatus().hashCode();
        return result;
    }
}

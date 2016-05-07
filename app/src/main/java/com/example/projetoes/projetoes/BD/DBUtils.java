package com.example.projetoes.projetoes.BD;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.example.projetoes.projetoes.Fragments.LostItemFeed;
import com.example.projetoes.projetoes.Models.Objeto;
import com.example.projetoes.projetoes.Models.Usuario;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by luiz on 09/04/16.
 */
public class DBUtils {

    public static String ID_OBJETO = "id_objeto";
    public static String USUARIO = "usuario";
    public static String FOTO = "foto";
    public static String CATEGORIA = "categoria";
    public static String TIPO = "tipo";
    public static String DESCRICAO = "descricao";
    public static String LOCAL = "local";
    public static String DATA = "data";
    public static String RECOMPENSA = "recompensa";
    public static String STATUS = "status";

    public static SQLiteDatabase getReadableDatabase(Context context) {
        LostFoundDbHelper openHelper = new LostFoundDbHelper(context);
        return openHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        LostFoundDbHelper openHelper = new LostFoundDbHelper(context);
        return openHelper.getWritableDatabase();
    }

    public static void addItemToLostFound(Context context, Objeto obj) {
        SQLiteDatabase db = getWritableDatabase(context);
        Table itemTable = LostFoundDbHelper.getItemTable();

        db.beginTransaction();

            ContentValues dadosItemLf = new ContentValues();
            dadosItemLf.put(ID_OBJETO, obj.getId());
            dadosItemLf.put(USUARIO,obj.getIdUsuario());
            dadosItemLf.put(FOTO, String.valueOf(obj.getFoto()));
            dadosItemLf.put(CATEGORIA, obj.getCategoria());
            dadosItemLf.put(TIPO, obj.getTipo());
            dadosItemLf.put(DESCRICAO, obj.getDescricao());
            dadosItemLf.put(LOCAL, obj.getLocal());
            dadosItemLf.put(DATA, obj.getData());
            dadosItemLf.put(RECOMPENSA, obj.getRecompensa());
            dadosItemLf.put(STATUS, obj.getStatus());

            db.insert(itemTable.getName(), null, dadosItemLf);

            db.setTransactionSuccessful();

            db.endTransaction();

        db.close();
    }

    public static ArrayList<Objeto> getLostFoundObjects(Context context) {
        SQLiteDatabase db = getReadableDatabase(context);
        Table itemsTable = LostFoundDbHelper.getItemTable();

        ArrayList<Objeto> objetos = new ArrayList<>();

        String[] columns = {ID_OBJETO, USUARIO, FOTO, CATEGORIA, TIPO, DESCRICAO, LOCAL, DATA, RECOMPENSA, STATUS};
        Cursor c = db.query(true, itemsTable.getName(), columns, null, null, null, null, null, null);

        int idObjetoIndex = c.getColumnIndex(ID_OBJETO);
        int idUsuarioIndex = c.getColumnIndex(USUARIO);
        int fotoIndex = c.getColumnIndex(FOTO);
        int categoriaIndex = c.getColumnIndex(CATEGORIA);
        int tipoIndex = c.getColumnIndex(TIPO);
        int descricaoIndex = c.getColumnIndex(DESCRICAO);
        int localIndex = c.getColumnIndex(LOCAL);
        int dataIndex = c.getColumnIndex(DATA);
        int recompensaIndex = c.getColumnIndex(RECOMPENSA);
        int statusIndex = c.getColumnIndex(STATUS);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            objetos.add(new Objeto(c.getInt(idObjetoIndex), c.getString(idUsuarioIndex), Uri.parse(c.getString(fotoIndex)),
                    c.getString(categoriaIndex), c.getString(tipoIndex), c.getString(descricaoIndex), c.getString(localIndex),
                    c.getString(dataIndex), c.getDouble(recompensaIndex), c.getString(statusIndex)));

            c.moveToNext();
        }
        c.close();
        db.close();

        return objetos;
    }
}

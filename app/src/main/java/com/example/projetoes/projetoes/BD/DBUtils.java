package com.example.projetoes.projetoes.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetoes.projetoes.Models.Objeto;
import com.example.projetoes.projetoes.Models.Usuario;

import java.util.ArrayList;

/**
 * Created by luiz on 09/04/16.
 */
public class DBUtils {

    public static SQLiteDatabase getReadableDatabase(Context context) {
        LostFoundDbHelper openHelper = new LostFoundDbHelper(context);
        return openHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        LostFoundDbHelper openHelper = new LostFoundDbHelper(context);
        return openHelper.getWritableDatabase();
    }

    public static void addUserInformation(Context context, Usuario user) {
        SQLiteDatabase db = getWritableDatabase(context);
        Table locationsTable = LostFoundDbHelper.getUserTable();

        db.beginTransaction();

        try {
            ContentValues dadosUsuario = new ContentValues();
            dadosUsuario.put("id_usuario", user.getId());
            dadosUsuario.put("foto", String.valueOf(user.getPhoto()));
            dadosUsuario.put("nome", user.getUsername());
            dadosUsuario.put("cidade", user.getCIDADE());
            dadosUsuario.put("bairro", user.getBairro());
            dadosUsuario.put("rua", user.getRua());
            dadosUsuario.put("telefone", user.getTelefone1());
            dadosUsuario.put("email", user.getEmail());

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        db.close();
    }
//    public static void addItemToLostFound(Context context, Objeto obj) {
//        SQLiteDatabase db = getWritableDatabase(context);
//        Table itemTable = LostFoundDbHelper.getItemTable();
//
//        db.beginTransaction();
//
//        try {
//            ContentValues dadosItemLf = new ContentValues();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//            dadosItemLf.put();
//        }
//    }
}

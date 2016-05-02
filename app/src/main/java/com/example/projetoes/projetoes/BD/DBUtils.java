package com.example.projetoes.projetoes.BD;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
//            dadosItemLf.put("id_objeto", obj.getId());
//            dadosItemLf.put("usuario", );
//            dadosItemLf.put("foto", String.valueOf(obj.getFoto()));
//            dadosItemLf.put("categoria", obj.getCategoria().toString());
//            dadosItemLf.put("tipo", obj.get);
//            dadosItemLf.put("descricao");
//            dadosItemLf.put("local");
//            dadosItemLf.put("data");
//            dadosItemLf.put("recompensa");
//            dadosItemLf.put("status");
//        }
//    }

    public static Usuario getUserInformation(Context context, String email) {
        SQLiteDatabase db = getReadableDatabase(context);

        String query = "SELECT * FROM usuario usr WHERE usr.email = '" + email + "'";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        String foto = c.getString(c.getColumnIndex("foto"));
        String nome = c.getString(c.getColumnIndex("nome"));
        String cidade = c.getString(c.getColumnIndex("cidade"));
        String bairro = c.getString(c.getColumnIndex("bairro"));
        String telefone = c.getString(c.getColumnIndex("telefone"));

        Usuario user = new Usuario(foto, nome, cidade, bairro, telefone, email);

        c.close();
        db.close();

        return user;
    }
}

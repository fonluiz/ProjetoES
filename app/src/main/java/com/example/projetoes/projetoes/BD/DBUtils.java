package com.example.projetoes.projetoes.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetoes.projetoes.Models.Objeto;
import com.example.projetoes.projetoes.Models.Usuario;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.LogInCallback;

import java.util.ArrayList;

/**
 * Created by luiz on 09/04/16.
 */
public class DBUtils {
    @Deprecated
    public static SQLiteDatabase getReadableDatabase(Context context) {
        LostFoundDbHelper openHelper = new LostFoundDbHelper(context);
        return openHelper.getReadableDatabase();
    }
    @Deprecated
    public static SQLiteDatabase getWritableDatabase(Context context) {
        LostFoundDbHelper openHelper = new LostFoundDbHelper(context);
        return openHelper.getWritableDatabase();
    }

    @Deprecated
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

    @Deprecated
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

    public static void signUpUser(Usuario user, String senha){

        // Campos obrigatórios
        ParseUser userAserSalvo = new ParseUser();
        try {
            userAserSalvo.setUsername(user.getUsername());
            userAserSalvo.setPassword(senha);
            userAserSalvo.setEmail(user.getEmail());
            // other fields can be set just like with ParseObject
            userAserSalvo.put("id_usuario", user.getId());
            userAserSalvo.put("foto", String.valueOf(user.getPhoto()));
            userAserSalvo.put("cidade", user.getCIDADE());
            userAserSalvo.put("bairro", user.getBairro());
            userAserSalvo.put("rua", user.getRua());
            userAserSalvo.put("telefone", user.getTelefone1());
        } finally {
            userAserSalvo.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        /*TODO*/
                        // Hooray! Let them use the app now.
                    } else {
                        /*TODO*/
                        // Sign up didn't succeed. Look at the ParseException
                        // to figure out what went wrong
                    }
                }
            });
        }
    }

    public static void logIn (String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    /*TODO*/
                    // Hooray! The user is logged in.
                } else {
                    /*TODO*/
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }

    public static void logOut(){
        if (isLoged()){
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
        }
    }

    public static Boolean isLoged(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void addItemToLostFound(Context context, Objeto obj) {
        ParseObject objetoAsersalvo = new ParseObject("Objeto");
        try {
            objetoAsersalvo.put("descricao", obj.getDescricao());
            objetoAsersalvo.put("local", obj.getLocal());
            objetoAsersalvo.put("data", obj.getData());
            objetoAsersalvo.put("categoria", obj.getCategoria().name());
            objetoAsersalvo.put("status", obj.getStatus().name());
            objetoAsersalvo.put("recompensa", obj.getRecompensa());
        } finally {
            objetoAsersalvo.saveInBackground();
        }

    }
}

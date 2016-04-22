package com.example.projetoes.projetoes.BD;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by luiz on 09/04/16.
 */
public class TableCreation extends SQLiteOpenHelper {

    private static final String DB_NAME = "Lost_Found_DB";
    private static final int DB_VERSION = 1;

    private static Table userTable = null;
    private static Table itemTable = null;


    public TableCreation(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public Table getUserTable() {
        if (userTable == null) {
            userTable = new Table("Usuario")
                    .addColumn(new Column("id_usuario", "LONG", true))
                    .addColumn(new Column("email", "STRING", true))
                    .addColumn(new Column("foto", "XML"))
                    .addColumn(new Column("nome", "STRING"))
                    .addColumn(new Column("endereco", "STRING"))
                    .addColumn(new Column("contato", "STRING"));
        }
        return userTable;
    }

    public Table getItemTable() {
        if (itemTable == null) {
            itemTable = new Table("Objeto")
                    .addColumn(new Column("id_objeto", "LONG", true))
                    .addColumn(new Column("foto", "XML"))
                    .addColumn(new Column("categoria", "STRING"))
                    .addColumn(new Column("tipo", "STRING"))
                    .addColumn(new Column("descricao", "STRING"))
                    .addColumn(new Column("local", "STRING"))
                    .addColumn(new Column("data", "DATE"))
                    .addColumn(new Column("recompensa", "FLOAT"))
                    .addColumn(new Column("status", "INT"))
                    .addForeignKey(new ForeignKey(getUserTable().getName())
                            .addReference("usuario", "id_usuario"));
        }
        return itemTable;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getUserTable().toSQL());
        db.execSQL(getItemTable().toSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        onCreate(db);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}

package com.example.projetoes.projetoes.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by luiz on 09/04/16.
 */
public class DBUtils {

    public static SQLiteDatabase getReadableDatabase(Context context) {
        TableCreation openHelper = new TableCreation(context);
        return openHelper.getReadableDatabase();
    }

    public static SQLiteDatabase getWritableDatabase(Context context) {
        TableCreation openHelper = new TableCreation(context);
        return openHelper.getWritableDatabase();
    }
}

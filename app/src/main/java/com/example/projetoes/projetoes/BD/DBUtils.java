package com.example.projetoes.projetoes.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

//    public static boolean registerUser(Context context, ArrayList<LocationData> locations, long timestamp) {
//        SQLiteDatabase db = getWritableDatabase(context);
//        Table locationsTable = MeliorDBOpenHelper.getLocationsTable();
//        boolean result = false;
//
//        // inicia a transação no banco
//        db.beginTransaction();
//        try {
//            for (LocationData location : locations) {
//
//                ContentValues valoresAvaliacao = new ContentValues();
//                valoresAvaliacao.put("lat", location.getLatitude());
//                valoresAvaliacao.put("lon", location.getLongitude());
//                valoresAvaliacao.put("acc", location.getAccuracy());
//                valoresAvaliacao.put("speed", location.getSpeed());
//                valoresAvaliacao.put("bear", location.getBearing());
//                valoresAvaliacao.put("et", location.getTime());
//                valoresAvaliacao.put("timestamp", timestamp);
//
//                db.insert(locationsTable.getName(), null, valoresAvaliacao);
//            }
//
//            db.setTransactionSuccessful();
//            result = true;
//        } finally {
//            db.endTransaction();
//        }
//
//        db.close();
//
//        return result;
//    }
}

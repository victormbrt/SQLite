package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;



public class AdminBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static  final String DATABASE_NOMBRE = "BaseDatos";
    public static final String TABLE_USUARIO = "usuario";

    public AdminBD(@Nullable Context context) {
        super((Context) context,DATABASE_NOMBRE  , null, DATABASE_VERSION);
    }

    //En este metodo se crear la base de datos con sus datos correspondientes
    @Override
    public void onCreate(SQLiteDatabase BaseDatos) {

        BaseDatos.execSQL("CREATE TABLE " + TABLE_USUARIO +" (cedula int primary key, nombre text, telefono int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

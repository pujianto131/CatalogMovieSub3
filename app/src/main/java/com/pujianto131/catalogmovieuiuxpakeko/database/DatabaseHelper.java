package com.pujianto131.catalogmovieuiuxpakeko.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pujianto131.catalogmovieuiuxpakeko.provider.FavoritColums;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "catalog_movie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_MOVIE = "create table " + FavoritColums.TABLE_NAME + " (" +
                FavoritColums.COLUMN_ID + " integer primary key autoincrement, " +
                FavoritColums.COLUMN_TITLE + " text not null, " +
                FavoritColums.COLUMN_BACKDROP + " text not null, " +
                FavoritColums.COLUMN_POSTER + " text not null, " +
                FavoritColums.COLUMN_RELEASE_DATE + " text not null, " +
                FavoritColums.COLUMN_VOTE + " text not null, " +
                FavoritColums.COLUMN_OVERVIEW + " text not null " +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE MOVIE IF EXISTS " + FavoritColums.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

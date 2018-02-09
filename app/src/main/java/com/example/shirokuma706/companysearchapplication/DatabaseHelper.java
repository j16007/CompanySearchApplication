package com.example.shirokuma706.companysearchapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shirokuma706 on 2018/01/15.
 */
/*ここから平井作成*/
public class DatabaseHelper extends SQLiteOpenHelper {
    /* データベース名 */
    private final static String DB_NAME = "company";
    /* データベースのバージョン */
    private final static int DB_VER = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "";
        sql += "create table MyTable (";
        sql += "name text not null primary key";
        sql += ",addres text not null";
        sql += ",tell integer";
        sql += ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int n) {
    }
}
/*ここまで平井作成*/

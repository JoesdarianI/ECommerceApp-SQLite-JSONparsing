package com.example.projectmcs.DatabaseSQLlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBhelper extends SQLiteOpenHelper {

   public DBhelper(@Nullable Context context){
      super(context,"Insorma",null,1);
   }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE users ( " +
                "userId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "userEmail TEXT," + "userUsername TEXT," +
                "userPhonenumber TEXT,"+ "userPassword TEXT)";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE furnitures ( " +
                "product_Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "product_name TEXT," + "price INTEGER," +
                "rating TEXT,"+ "description TEXT,"+
                "image TEXT" + ")";
        sqLiteDatabase.execSQL(query);

        query = "CREATE TABLE history (" +
                "transaction_Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "userId INTEGER," + "product_Id INTEGER, " + "tQuantity INTEGER,"+ "tPrice INTEGER,"+ "tName String,"+"transaction_Date String)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS furnitures");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS history");
    }

}

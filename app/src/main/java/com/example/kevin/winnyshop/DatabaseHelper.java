package com.example.kevin.winnyshop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String Database_Name = "kingshop.db";

    public static final String Table_User = "user";
    public static final String UsrCol_1 = "User";
    public static final String UsrCol_2 = "Pass";

    public static final String Table_Barang = "barang";
    public static final String BrgCol_1 = "ID";
    public static final String BrgCol_2 = "Nama";
    public static final String BrgCol_3 = "Console";
    public static final String BrgCol_4 = "Harga";

    public static final String Table_Cart = "cart";
    public static final String CrtCol_1 = "ID";
    public static final String CrtCol_2 = "Nama";
    public static final String CrtCol_3 = "Console";
    public static final String CrtCol_4 = "Harga";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + Table_User + "(User Text Primary Key, Pass Text Not Null)");
        db.execSQL("Create Table " + Table_Barang + "(ID Text Primary Key, Nama Text Not Null, Console Text Not Null, Harga Text Not Null)");
        db.execSQL("Create Table " + Table_Cart + "(ID Text Not Null, Nama Text Not Null, Console Text Not Null, Harga Text Not Null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_User);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Barang);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Cart);
        onCreate(db);
    }

    public List<Temp> findAll(){
        try {
            List<Temp> tempList = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("Select * From "+Table_Cart,null);
            if (cursor.moveToFirst()){
                do {
                    Temp temp = new Temp();
                    temp.setId(cursor.getString(0));
                    temp.setNama(cursor.getString(1));
                    temp.setConsole(cursor.getString(2));
                    temp.setHarga(cursor.getString(3));
                    tempList.add(temp);
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return tempList;
        } catch (Exception e) {
            return null;
        }
    }
}

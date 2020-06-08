package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper {

    private static final String DATABASE_NAME = "InnerDatabase(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE0);
            db.execSQL(DataBases.CreateDB._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME1);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    // Insert Drug DB
    public long insertColumn0(String name, long stock, String type){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.STOCK, stock);
        values.put(DataBases.CreateDB.TYPE, type);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    // Insert Log DB
    public long insertColumn1(String plaintext, long yearmonth){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.PlainText, plaintext);
        values.put(DataBases.CreateDB.YearMonth, yearmonth);
        return mDB.insert(DataBases.CreateDB._TABLENAME1, null, values);
    }

    // Update Drug DB
    public boolean updateColumn0(long id, String name, long stock, String type){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.NAME, name);
        values.put(DataBases.CreateDB.STOCK, stock);
        values.put(DataBases.CreateDB.TYPE, type);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // Delete All
    public void deleteAllColumns0() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete All
    public void deleteAllColumns1() {
        mDB.delete(DataBases.CreateDB._TABLENAME1, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }

    // Drug Table Sort By ID
    public Cursor selectDrugs(){
        Cursor c = mDB.rawQuery( "SELECT * FROM drugtable ORDER BY _ID;", null);
        return c;
    }

    public Cursor selectYMLogs(long YYMM){
        Cursor c = mDB.rawQuery( "SELECT * FROM logtable WHERE yearmonth = "+YYMM+" ORDER BY _ID;", null);
        return c;
    }
}
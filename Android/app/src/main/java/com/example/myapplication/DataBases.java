package com.example.myapplication;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns{
        public static final String NAME = "name";
        public static final String STOCK = "stock";
        public static final String TYPE = "type";
        public static final String _TABLENAME0 = "drugtable";

        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +NAME+" text not null , "
                +STOCK+" integer not null , "
                +TYPE+" text not null );";


        public static final String PlainText = "plaintext";
        public static final String YearMonth = "yearmonth";
        public static final String _TABLENAME1 = "logtable";
        public static final String _CREATE1 = "create table if not exists "+_TABLENAME1+"("
                +_ID+" integer primary key autoincrement, "
                +YearMonth+" integer not null , "
                +PlainText+" text not null );";
    }
}
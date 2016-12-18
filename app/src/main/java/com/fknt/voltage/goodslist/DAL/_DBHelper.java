package com.fknt.voltage.goodslist.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by voltage on 14.12.2016.
 */

@Deprecated
class _DBHelper extends SQLiteOpenHelper {

    private String logTag;
    public _DBHelper(Context context)
    {
        super(context,"goodslistDB",null,1);
    }

    _DBHelper(Context context, String logTag){
        this(context);
        this.logTag =logTag;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateGroups="" +
                "create table tblProductGroups" +
                "(Id integer primary key autoincrement" +
                ",GroupName text" +
                ")";

        String sqlCreateProducts="" +
                "create table tblProducts" +
                "(id integer primary key autoincrement" +
                ",groupId integer" +
                ",ProductName text" +
                ")";
        String sqlCreateGoodsListsHeaders="" +
                "create table tblGoodsListHeaders" +
                "(id integer primary key autoincrement" +
                ",date_create text" +
                ",date_close text" +
                ",name text" +
                ",geolocation text" +
                ",total_sum real"+
                ")";

        String sqlCreateGoodsListPositions="" +
                "create table tblGoodsListPosition" +
                "(id integer primary key autoincrement" +
                ",header_id integer" +
                ",ProductId integer" +
                ",price real" +
                ",quantity real" +
                ",total_sum real" +
                ")";

        db.execSQL(sqlCreateGroups);
        Log.d(logTag,"sqlCreateGroups");
        db.execSQL(sqlCreateProducts);
        Log.d(logTag,"sqlCreateProducts");
        db.execSQL(sqlCreateGoodsListsHeaders);
        Log.d(logTag,"sqlCreateGoodsListsHeaders");
        db.execSQL(sqlCreateGoodsListPositions);
        Log.d(logTag,"sqlCreateGoodsListPositions");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

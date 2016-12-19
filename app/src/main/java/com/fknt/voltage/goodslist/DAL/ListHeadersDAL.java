package com.fknt.voltage.goodslist.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fknt.voltage.goodslist.Classes.ListHeaderItem;
import com.fknt.voltage.goodslist.DAL.Contracts.GoodsListDBContract;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by voltage on 17.12.2016.
 */

public class ListHeadersDAL {

    private Context context;
    private DBHelper mDbHelper;

    public ListHeadersDAL(Context context) {
        this.context = context;
        this.mDbHelper= new DBHelper(this.context);
    }

    public long Insert(ListHeaderItem headerItem)
    {
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_NAME
                ,headerItem.Name!=null?headerItem.Name:null);
        contentValues.put(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CREATED
                ,headerItem.DateCreate!=null?headerItem.DateCreate.toString():null);
        contentValues.put(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CLOSE
                ,headerItem.DateFinished!=null?headerItem.DateFinished.toString():null);
//        contentValues.put(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_LONGITUDE
//                ,headerItem.Geolocation.Longtitude!=Double.NaN?headerItem.Geolocation.Longtitude:0.0
//        );
//        contentValues.put(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_LATITUDE
//                ,headerItem.Geolocation.Latitude!=Double.NaN?headerItem.Geolocation.Latitude:0.0
//        );
        contentValues.put(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_TOTALSUM
                ,headerItem.TotalSum
        );

        return db.insert(GoodsListDBContract.ListItemHeaderTable.TABLE_NAME,
                null,
                contentValues
                );
    }

    public void Delete(ListHeaderItem headerItem){
        this.Delete(headerItem.Id);
    }

    public void Delete(int headerItemId) {
        // TODO: 17.12.2016 Add logic to take into account nested elements
        String selection= GoodsListDBContract.ListItemHeaderTable._ID+"=?";
        String[] selectionParams={String.valueOf(headerItemId)};
        SQLiteDatabase db=mDbHelper.getWritableDatabase();
        db.delete(GoodsListDBContract.ListItemHeaderTable.TABLE_NAME,selection,selectionParams);
        db.close();
    }

    public void DeleteAll() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(GoodsListDBContract.ListItemHeaderTable.TABLE_NAME, null, null);
        db.close();
    }


    public List<ListHeaderItem> Select(HashMap<String,String> parameters)
    {
        List<ListHeaderItem> result = new ArrayList<>();

        String[] columns={
                GoodsListDBContract.ListItemHeaderTable._ID,
                GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_NAME,
                GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CREATED,
                GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CLOSE,
                GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_LONGITUDE,
                GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_LATITUDE,
                GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_TOTALSUM
        };


        String selection="";
        ArrayList<String> values= new ArrayList<>();
        String[] selectionArgs=new String[parameters.size()];

        //Parameters binding
        for (Map.Entry<String, String> parameter :
                parameters.entrySet()) {
            selection=selection+parameter.getKey()+"=? AND ";
            values.add(parameter.getValue());
        }

        selection=selection+"1=1";
        values.toArray(selectionArgs);

        String sortOrder= GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CREATED+" DESC";


        SQLiteDatabase db=mDbHelper.getReadableDatabase();
        Cursor cursor=db.query(
                GoodsListDBContract.ListItemHeaderTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        if(cursor.moveToFirst()){
            do {
                result.add(this.getObjectFromDataRow(cursor));
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return  result;
    }

    public List<ListHeaderItem> SelectAll()
    {
        return this.Select(new HashMap<String, String>());
    }

    private ListHeaderItem getObjectFromDataRow(Cursor c){
        DateFormat format= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

        ListHeaderItem item= new ListHeaderItem();
        item.Id=c.getInt(c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable._ID));
        item.Name=c.getString(c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_NAME));
        String strDateCreate=c.getString(
                c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CREATED));

        try {
            item.DateCreate= format.parse(strDateCreate); //Date.valueOf(strDateCreate);
        } catch (ParseException e) {
            // FIXME: 18.12.2016 Handle Parse Exception
        }
//        item.DateFinished= Date.valueOf(c.getString(
//                c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_DATE_CLOSE)
//        ));

//        item.Geolocation.Longtitude= Double.valueOf(c.getString(
//                c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_LONGITUDE)
//        ));
//        item.Geolocation.Latitude= Double.valueOf(c.getString(
//                c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_LATITUDE)
//        ));
        item.TotalSum= Double.valueOf(c.getString(
                c.getColumnIndex(GoodsListDBContract.ListItemHeaderTable.COLUMN_NAME_TOTALSUM)
        ));


        return  item;
    }
}

package com.fknt.voltage.goodslist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;

import com.fknt.voltage.goodslist.Adapters.GenericListAdapter;
import com.fknt.voltage.goodslist.Classes.GoodsItemStatus;
import com.fknt.voltage.goodslist.Classes.GoodsListItem;
import com.fknt.voltage.goodslist.DAL.GoodsListItemsDAL;

import java.util.List;

public class GoodsListScreen extends AppCompatActivity {

    final String LOG_TAG="GOODS_LIST_LOG";
    List<GoodsListItem> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemList=GetItemsList();



        ListView listView=(ListView) findViewById(R.id.list_view_items);
        //listView.setAdapter(new GoodsListAdapter(this,itemList));
        listView.setAdapter(new GenericListAdapter(this,itemList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cbStatus=(CheckBox) view.findViewById(R.id.cb_Status);
                GoodsItemStatus status=(cbStatus.isChecked()?GoodsItemStatus.Bought:GoodsItemStatus.New);
                itemList.get(position).setStatus(status);

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    private List<GoodsListItem> GetItemsList() {
        return GoodsListItemsDAL.getListItems();
        //SQLiteDatabase db=dbHelper.getReadableDatabase();
        //Cursor c=db.query()
    }



}

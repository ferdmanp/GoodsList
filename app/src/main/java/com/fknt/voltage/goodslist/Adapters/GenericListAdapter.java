package com.fknt.voltage.goodslist.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fknt.voltage.goodslist.Classes.ListItemBase;

import java.util.List;

/**
 * Created by voltage on 14.12.2016. Project GoodsList
 */

public class GenericListAdapter<T extends ListItemBase> extends BaseAdapter {

    private List<T> items;
    private Activity context;

    public GenericListAdapter(Activity context,List<T> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).GetId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemBase item=(ListItemBase) this.getItem(position);
        return item.getView(context,convertView,parent);
    }

    public void updateList(List<T> newData) {
        items.clear();
        items.addAll(newData);
        this.notifyDataSetChanged();
    }
}

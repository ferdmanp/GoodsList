package com.fknt.voltage.goodslist.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fknt.voltage.goodslist.Classes.GoodsItemStatus;
import com.fknt.voltage.goodslist.Classes.GoodsListItem;
import com.fknt.voltage.goodslist.R;

import java.util.List;

/**
 * Created by voltage on 26.11.2016.
 */

public class GoodsListAdapter extends BaseAdapter {

    List<GoodsListItem> items;
    Activity context;

    public  GoodsListAdapter(Activity context,List<GoodsListItem> items)
    {
        this.context=context;
        this.items=items;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GoodsListItem current=items.get(position);
        View view=convertView;
        if(view==null){
            view=context.getLayoutInflater().inflate(R.layout.layout_goods_list_row,null);
            TextView tvProductName=(TextView) view.findViewById(R.id.tv_product_name);
            TextView tvProductQty=(TextView)view.findViewById(R.id.tv_product_qty);
            TextView tvProductPrice=(TextView)view.findViewById(R.id.tv_product_price);
            TextView tvTotalSum=(TextView)view.findViewById(R.id.tv_product_total_sum);
            CheckBox cbStatus=(CheckBox)view.findViewById(R.id.cb_Status);

            tvProductName.setText(current.Product.productName);
            tvProductQty.setText(String.valueOf(current.Quantity));
            tvProductPrice.setText(String.valueOf(current.Price));
            tvTotalSum.setText(String.valueOf(current.Quantity*current.Price));

            cbStatus.setChecked(current.getStatus()== GoodsItemStatus.Bought);
        }

        return  view;
    }
}

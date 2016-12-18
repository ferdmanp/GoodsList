package com.fknt.voltage.goodslist.Classes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fknt.voltage.goodslist.R;
import com.fknt.voltage.goodslist.objects.Product;
import com.fknt.voltage.goodslist.objects.ProductGroup;

public class GoodsListItem extends ListItemBase {

    public  int Id;

    public Product Product;

    public double Price;

    public double Quantity;

    GoodsItemStatus status;

    public GoodsListItem(Product product, double price, double quantity)
    {
        this.Product =product;
        this.Price=price;
        this.Quantity=quantity;
        this.setStatus(GoodsItemStatus.New);
    }

    public GoodsListItem(String productName, String measureUnit, String productGroupName, double price, double quantity)
    {
        this.Product = new Product(productName,measureUnit,new ProductGroup(productGroupName,-1));
        this.Price=price;
        this.Quantity=quantity;
        this.setStatus(GoodsItemStatus.New);
    }

    public void setStatus(GoodsItemStatus status)
    {
        this.status=status;
    }

    public GoodsItemStatus  getStatus()
    {
        return this.status;
    }


    @Override
    public int GetId() {
        return this.Id;
    }

    @Override
    public View getView(Activity context, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            view=context.getLayoutInflater().inflate(R.layout.layout_goods_list_row,null);
            TextView tvProductName=(TextView) view.findViewById(R.id.tv_product_name);
            TextView tvProductQty=(TextView)view.findViewById(R.id.tv_product_qty);
            TextView tvProductPrice=(TextView)view.findViewById(R.id.tv_product_price);
            TextView tvTotalSum=(TextView)view.findViewById(R.id.tv_product_total_sum);
            CheckBox cbStatus=(CheckBox)view.findViewById(R.id.cb_Status);

            tvProductName.setText(this.Product.productName);
            tvProductQty.setText(String.valueOf(this.Quantity));
            tvProductPrice.setText(String.valueOf(this.Price));
            tvTotalSum.setText(String.valueOf(this.Quantity*this.Price));

            cbStatus.setChecked(this.getStatus()== GoodsItemStatus.Bought);
        }

        return  view;
    }
}

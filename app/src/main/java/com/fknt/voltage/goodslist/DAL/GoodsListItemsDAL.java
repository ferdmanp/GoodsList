package com.fknt.voltage.goodslist.DAL;

import com.fknt.voltage.goodslist.Classes.GoodsListItem;
import com.fknt.voltage.goodslist.Classes.ListHeaderItem;
import com.fknt.voltage.goodslist.objects.Product;
import com.fknt.voltage.goodslist.objects.ProductGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by voltage on 26.11.2016.
 */

public class GoodsListItemsDAL
{

    public static List<GoodsListItem> getListItems()
    {
        List<GoodsListItem> items= new ArrayList<>();

        ProductGroup testGroup1=new ProductGroup("Фрукты",1);
        ProductGroup testGroup2=new ProductGroup("Овощи",2);

        items.add(
                new GoodsListItem(
                        new Product("Яблоки","кг",testGroup1),
                        1,
                        1
                )
        );

        items.add(
                new GoodsListItem(
                        new Product("Груши","кг",testGroup1),
                        0.5,
                        20
                )
        );

        items.add(
                new GoodsListItem(
                        new Product("Огурцы","кг",testGroup2),
                        2,
                        30
                )
        );

        return items;
    }


}

package com.fknt.voltage.goodslist.objects;

import android.support.annotation.Nullable;

/**
 * Created by voltage on 26.11.2016.
 */



public class Product {

    public ProductGroup productGroup;

    public String productName;

    public String measureUnit;

    public Product(String productName, String measureUnit, @Nullable ProductGroup productGroup)
    {
        this.productGroup=productGroup;
        this.productName=productName;
        this.measureUnit=measureUnit;
    }


}

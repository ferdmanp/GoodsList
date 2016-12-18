package com.fknt.voltage.goodslist.Classes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by voltage on 14.12.2016.
 */

public abstract class ListItemBase {

    public abstract int GetId();

    public abstract View getView(Activity context, View convertView, ViewGroup parent);


}

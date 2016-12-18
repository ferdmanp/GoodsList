package com.fknt.voltage.goodslist.Classes;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fknt.voltage.goodslist.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by voltage on 14.12.2016.
 */

public class ListHeaderItem extends ListItemBase {


    @Override
    public int GetId() {
        return Id;
    }

    @Override
    public View getView(Activity context, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            view=context.getLayoutInflater().inflate(R.layout.row_headers_list,null);
            TextView tvName=(TextView) view.findViewById(R.id.tv_group_name);
            TextView tvDate=(TextView)view.findViewById(R.id.tv_group_date);
            TextView tvPrice=(TextView)view.findViewById(R.id.tv_group_price);


            //DateFormat dateFormat= SimpleDateFormat()
            tvName.setText(this.Name);
            //tvDate.setText(this.DateCreate.toString());
            //TODO: Replace with date from DB
            tvDate.setText(DateFormat.getDateInstance().format(new Date()));
            tvPrice.setText(String.valueOf(this.TotalSum));
        }
        return  view;
    }


    public int Id;
    public String Name;
    public Date DateCreate;
    public Date DateFinished;
    public GeoCoord Geolocation;
    public Double TotalSum;
    public int CountPositions;

    public class GeoCoord{
        public double Latitude;
        public double Longtitude;

        public GeoCoord(int lat, int lng)
        {
            this.Latitude=lat;
            this.Longtitude=lng;
        }
    }

}

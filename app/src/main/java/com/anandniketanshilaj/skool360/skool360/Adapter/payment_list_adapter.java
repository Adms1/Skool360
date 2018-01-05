package com.anandniketanshilaj.skool360.skool360.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anandniketanshilaj.skool360.R;
import com.anandniketanshilaj.skool360.skool360.Models.CanteenModel;

import java.util.ArrayList;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class payment_list_adapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public payment_list_adapter(Context c, ArrayList<CanteenModel> canteenModels) {
        mContext = c;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtDate = null, txtDay = null, txtBreakfast = null, txtLunch = null, txtMilk = null;
        ImageView imgBulletCanteen = null;
        LinearLayout llChildRowFlvMilk = null;

        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_row_canteen, null);
        }



        return convertView;
    }

}

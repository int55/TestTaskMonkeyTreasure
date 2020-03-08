package com.example.testtaskmonkeytreasure;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] imageCell = new Integer[81];

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imageCell.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // создать новый ImageView для каждого элемента, на который ссылается адаптер
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        imageCell = new Integer[81];
        for (int i = 0; i < imageCell.length; i++) {
            imageCell[i] = R.drawable.cell;
        }

        // Если не используется повторно, инициализируем атрибуты
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(110, 110));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(imageCell[position]);
        return imageView;
    } // getView


    //    private Integer[] imageCell = {
//            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,
//            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,
//            R.drawable.cell, R.drawable.cell, R.drawable.cell, R.drawable.cell,R.drawable.cell, R.drawable.cell,
//
//    };

}// class ImageAdapter
package com.lxy.bulletinview.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lxy.bulletinview.R;

import java.util.List;

/**
 * Created by LXY on 2018/8/6.
 */

public class HomeAdapter extends BulletinView.BulletinViewadapter<String> {

    public HomeAdapter(List<String> data) {
        super(data);
    }

    @Override
    int getItemCount() {
        return getData().size();
    }

    @Override
    View onBindViewHolder(View itemView, int position, String itemData) {
        TextView textView = itemView.findViewById(R.id.tv_line);
        textView.setText(itemData);
        return itemView;
    }

    @Override
    View onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        View view;
        if (viewType%2!=0){
            view =inflater.inflate(R.layout.lines, parent);
        }else {
            view =inflater.inflate(R.layout.lines_two, parent);
        }
        return view;
    }
}

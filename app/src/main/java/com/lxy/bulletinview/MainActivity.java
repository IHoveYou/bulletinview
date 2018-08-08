package com.lxy.bulletinview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.lxy.bulletinview.widget.BulletinView;
import com.lxy.bulletinview.widget.HomeAdapter;
//import com.lxy.bulletinview.widget.HometwoAdapter;
//import com.lxy.bulletinview.widget.HorizontalView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BulletinView bulletinView;
    HomeAdapter adapter;
//    HorizontalView horizontalView;
//    HorizontalView.HorizontaViewAdapter horizontaViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulletinView=findViewById(R.id.buttonPanel);
        //horizontalView=findViewById(R.id.horizontalView);
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add("别人家孩子作业做到转钟");
            list1.add("别人家孩子周末都在家学习");
            list1.add("就知道玩");
            list1.add("别人家孩子作业做到转钟");
            list1.add("就知道玩");
            list1.add("别人家孩子周末都在家学习");
        }

        adapter=new HomeAdapter(list1);
//        horizontaViewAdapter =new HometwoAdapter(list1);
//        horizontalView.setAdapter(horizontaViewAdapter);
        bulletinView.setAdapter(adapter);
        bulletinView.setOnItemClickListener(new BulletinView.OnItemClickListener<String>() {
            @Override
            public void onItemClickListener(String itemData, int pointer, View view) {
                Toast.makeText(MainActivity.this,itemData,Toast.LENGTH_LONG).show();
            }
        });

       ViewFlipper viewFlipper=new ViewFlipper(this);
    }
}

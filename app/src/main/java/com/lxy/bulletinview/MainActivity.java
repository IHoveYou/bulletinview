package com.lxy.bulletinview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxy.bulletinview.widget.BulletinView;
import com.lxy.bulletinview.widget.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BulletinView bulletinView;
    HomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulletinView=findViewById(R.id.buttonPanel);
        List<String> list1 = new ArrayList<>();
        list1.add("别人家孩子作业做到转钟");
        list1.add("别人家孩子周末都在家学习");
        list1.add("就知道玩");
        list1.add("别人家孩子作业做到转钟");
        list1.add("就知道玩");
        list1.add("别人家孩子周末都在家学习");
        adapter=new HomeAdapter(list1);
        bulletinView.setAdapter(adapter);
        bulletinView.setOnItemClickListener(new BulletinView.OnItemClickListener<String>() {
            @Override
            public void onItemClickListener(String itemData, int pointer, View view) {
                Toast.makeText(MainActivity.this,itemData,Toast.LENGTH_LONG).show();
            }
        });
    }
}

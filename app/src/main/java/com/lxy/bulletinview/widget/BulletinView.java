package com.lxy.bulletinview.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import java.util.List;


/**
 * Created by LXY on 2018/8/6.
 * 公告栏
 */

public class BulletinView extends NestedScrollView {
   private boolean isStop = true;
    private View view1, view2;
    private LayoutInflater inflater;
    private BulletinViewadapter adapter;
    private  int viewType = 0;
    private LinearLayout linearLayout;
    private   OnItemClickListener onItemClickListener;

    public BulletinView(Context context) {
        super(context);
    }

    public BulletinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(getContext());
        linearLayout = new LinearLayout(getContext());
    }

    public BulletinView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        linearLayout = new LinearLayout(getContext());
        inflater = LayoutInflater.from(getContext());
    }


    public void refresh() {
        scrollTo(0, 0);
        linearLayout.removeAllViews();
        linearLayout.invalidate();
        if (adapter != null) {
            if (viewType >= adapter.getItemCount()) {
                viewType = 0;
            }
            if (view1 == null) {
                addView(linearLayout);
                view1 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, null, viewType), viewType, adapter.getData().get(viewType));
            }
            if (viewType + 1 == adapter.getItemCount()) {
                view2 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, null, 0), 0, adapter.getData().get(0));
                view2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener!=null)
                        onItemClickListener.onItemClickListener(adapter.getData().get(0),viewType,v);
                    }
                });
            } else {
                view2 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, null, viewType + 1), viewType + 1, adapter.getData().get(viewType + 1));
                if (onItemClickListener!=null)
                view2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickListener(adapter.getData().get(viewType + 1),viewType + 1,v);
                    }
                });
            }
            if (view1 != null) {
                view1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener!=null)
                        onItemClickListener.onItemClickListener(adapter.getData().get(viewType),viewType,v);
                    }
                });

                view1.setLayoutParams(getLayoutParams());
                view2.setLayoutParams(getLayoutParams());
                setAnimate(view1);
                setAnimate(view2);
                linearLayout.addView(view2);
                linearLayout.addView(view1);
                view1 = view2;
            }
        }
    }

    /**
     * 属性动画
     * 平移
     */
    private void setAnimate(View view) {
//      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
//      创建属性动画对象，并设置移动的方向和偏移量
//      translationX是imageview的平移属性
        TranslateAnimation objectAnimator = new TranslateAnimation(0, 0, -(float) getHeight(), 0);
//      设置移动时间
        objectAnimator.setDuration(500);
//      开始动画
        objectAnimator.setFillAfter(true);
        view.setAnimation(objectAnimator);
        objectAnimator.start();
    }


    private Handler mHandler = new Handler();

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            if (!isStop) {
                viewType++;
                mHandler.postDelayed(this, 2000);
                invalidate();
                refresh();
            } else {
                mHandler.removeCallbacks(r);
            }
        }
    };

    public void setAdapter(BulletinViewadapter adapter) {
        if (adapter != null) {


            linearLayout.setOrientation(LinearLayout.VERTICAL);
            this.adapter = adapter;
            isStop = false;
            mHandler.postDelayed(r, 2000);
        }
    }

    public void onStop() {
        isStop = true;
        mHandler.removeCallbacks(r);
    }

    public void onStart() {
        isStop = false;
        if (adapter != null)
            mHandler.postDelayed(r, 2000);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener != null) {
            this.onItemClickListener = onItemClickListener;
        }
    }

    public static abstract class BulletinViewadapter<T> {
        private List<T> data;

        public BulletinViewadapter(List<T> data) {
            this.data = data;
        }

        public List<T> getData() {
            return data;
        }

        abstract int getItemCount();

        abstract View onBindViewHolder(View itemView, int position, T itemData);

        abstract View onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);
    }

    public interface OnItemClickListener<T> {
        void onItemClickListener(T itemData, int pointer, View view);
    }
}

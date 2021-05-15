package com.lxy.bulletinview.widget;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import java.util.List;


public class BulletinView extends LinearLayout {
    private boolean isStop = true;//是否停止
    private View view1, view2;  //上下切换的两个view,view2在上面,view1在下面
    private LayoutInflater inflater;
    private BulletinViewadapter adapter;//适配器
    private int viewType = 0;//当前显示的条目下标
    private LinearLayout linearLayout;// 条目容器
    private OnItemClickListener onItemClickListener;//条目点击事件
    private TranslateAnimation mTranslateAnimationStart, mTranslateAnimationOut;//条目切换动画
    private boolean initData = false;//是否初始化

    public boolean isInitData() {
        return initData;
    }

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


//    private void refresh() {
////        scrollTo(0, 0);//为了效果美观每次切换前先定位到顶部
//        linearLayout.removeAllViews();//移除之前添加的条目,节省空间
//        linearLayout.invalidate();
//        if (adapter != null) {
//            if (viewType >= adapter.getItemCount()) {
//                viewType = 0;
//            }
//            if (view1 == null) {//首次加载时直接显示条目一
//                addView(linearLayout);
//                view1 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, null, viewType), viewType, adapter.getData().get(viewType));
//                view1.setLayoutParams(getLayoutParams());
//                linearLayout.addView(view1);
//            } else {
//                if (viewType + 1 >= adapter.getItemCount()) {
//                    //获取即将展示的条目
//                    view2 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, null, 0), 0, adapter.getData().get(0));
//                    view2.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (onItemClickListener != null)
//                                onItemClickListener.onItemClickListener(adapter.getData().get(viewType), viewType, v);
//                        }
//                    });
//                } else {
//                    //获取即将展示的条目
//                    view2 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, null, viewType + 1), viewType + 1, adapter.getData().get(viewType + 1));
//                    if (onItemClickListener != null)
//                        view2.setOnClickListener(new OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                onItemClickListener.onItemClickListener(adapter.getData().get(viewType + 1), viewType + 1, v);
//                            }
//                        });
//                }
//
//                if (view2 != null) {
//                    view2.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (onItemClickListener != null)
//                                onItemClickListener.onItemClickListener(adapter.getData().get(viewType), viewType, v);
//                        }
//                    });
//                    //设置条目布局属性免除条目高度不一导致的问题
//                    view1.setLayoutParams(getLayoutParams());
//                    view2.setLayoutParams(getLayoutParams());
//                    //判断是否设置了自定义动画 ,如果没设置则使用上下滚动默认效果
//                    if (mTranslateAnimationStart != null) {
//                        view2.setAnimation(mTranslateAnimationStart);
//                    } else {
//                        setAnimate(view1);
//                    }
//                    if (mTranslateAnimationOut != null) {
//                        view1.setAnimation(mTranslateAnimationOut);
//                        mTranslateAnimationOut.setAnimationListener(animationListener);//监测动画执行,执行完成后将不可见布局移除
//                    } else {
//                        setAnimate(view2).setAnimationListener(animationListener);
//                    }
//                    //将自布局添加到界面上进行显示
//
//                    linearLayout.addView(view1);
//                    linearLayout.addView(view2);
//                }
//
//            }
//        }
//    }


    public void init() {
        if (view1==null){
            removeAllViews();
            addView(linearLayout);
        }
        if (viewType >= adapter.getItemCount()) {
            viewType = 0;
        }
        int viewType2 = getPson(viewType + 1);
        linearLayout.removeAllViews();
        view1 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, linearLayout, viewType), viewType, adapter.getData().get(viewType));
        view2 = adapter.onBindViewHolder(adapter.onCreateViewHolder(inflater, linearLayout, viewType2), viewType2, adapter.getData().get(viewType2));
        view1.setLayoutParams(getLayoutParams());
        view2.setLayoutParams(getLayoutParams());
        linearLayout.addView(view1);
        linearLayout.addView(view2);
        setAnimate(view1);
        setAnimate(view2);

        view1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClickListener(adapter.getData().get(viewType), viewType, v);
            }
        });
        view2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClickListener(adapter.getData().get(viewType), viewType, v);
            }
        });
    }


    public int getPson(int type) {
        if (type == adapter.getItemCount()) {
            return 0;
        }
        return type;
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
//            linearLayout.removeView(view2);
            view1 = view2;//将当前可见布局赋值给view1;表示下次移除的布局
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    /**
     * 属性动画
     * 平移
     * 从上往下
     */
//    private TranslateAnimation setAnimate(View view) {
////      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
////      创建属性动画对象，并设置移动的方向和偏移量
////      translationX是imageview的平移属性
//        TranslateAnimation objectAnimator = new TranslateAnimation(0, 0, -(float) getHeight(), 0);
////      设置移动时间
//        objectAnimator.setDuration(500);
////      开始动画
//        objectAnimator.setFillAfter(true);
//        view.setAnimation(objectAnimator);
//        objectAnimator.start();
//
//        return objectAnimator;
//    }

    /**
     * 属性动画
     * 平移
     * 从下往上
     */
    private TranslateAnimation setAnimate(View view) {
//      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
//      创建属性动画对象，并设置移动的方向和偏移量
//      translationX是imageview的平移属性
        TranslateAnimation objectAnimator = new TranslateAnimation(0, 0, 0, -(float) getHeight());
//      设置移动时间
        objectAnimator.setDuration(500);
//      开始动画
        objectAnimator.setFillAfter(true);
        view.setAnimation(objectAnimator);
        objectAnimator.start();

        return objectAnimator;
    }

    private Handler mHandler = new Handler();

    private Runnable r = new Runnable() {//循环滚动
        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            if (!isStop) {
                init();
                viewType++;
                mHandler.postDelayed(this, 2000);
            } else {
                mHandler.removeCallbacks(r);
            }
        }
    };

    public void setAdapter(BulletinViewadapter adapter) {//设置适配器
        if (adapter != null) {
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            this.adapter = adapter;
            isStop = false;
            init();
            if (!initData) {
                mHandler.postDelayed(r, 2000);
            }
            initData = true;
        }
    }

    public void onStop() {//停止滚动
        isStop = true;
        mHandler.removeCallbacks(r);
    }

    public void onStart() {//开始滚动
        isStop = false;
        if (adapter != null)
            mHandler.postDelayed(r, 500);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {//条目点击事件
        if (onItemClickListener != null) {
            this.onItemClickListener = onItemClickListener;
        }
    }

    public static abstract class BulletinViewadapter<T> {//适配器抽象类
        private List<T> data;

        public BulletinViewadapter(List<T> data) {
            this.data = data;
        }

        public List<T> getData() {
            return data;
        }

        public abstract int getItemCount();

        public abstract View onBindViewHolder(View itemView, int position, T itemData);

        protected abstract View onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);
    }

    public interface OnItemClickListener<T> {
        void onItemClickListener(T itemData, int pointer, View view);
    }

//    //设置开始动画,可不设置
//    public void setmTranslateAnimation(TranslateAnimation mTranslateAnimationStart
//            , TranslateAnimation mTranslateAnimationOut) {
//        this.mTranslateAnimationStart = mTranslateAnimationStart;
//        this.mTranslateAnimationOut = mTranslateAnimationOut;
//    }
}

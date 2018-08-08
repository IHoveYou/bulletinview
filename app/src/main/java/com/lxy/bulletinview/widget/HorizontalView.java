//package com.lxy.bulletinview.widget;
//
//import android.content.Context;
//import android.os.Handler;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.TranslateAnimation;
//import android.widget.HorizontalScrollView;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//
//import java.util.List;
//
///**
// * Created by LXY on 2018/8/7.
// */
//
//public class HorizontalView extends HorizontalScrollView {
//    private boolean isStop = false;//是否停止
//    private int viewType = 0;//当前显示的条目下标
//    HorizontaViewAdapter adapter;
//    LinearLayout linearLayout;
//    private View centerView, hidView;
//    private LayoutInflater inflater;
//
//    public HorizontalView(Context context) {
//        super(context);
//        linearLayout = new LinearLayout(context);
//        inflater = LayoutInflater.from(context);
//    }
//
//    public HorizontalView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        linearLayout = new LinearLayout(context);
//        inflater = LayoutInflater.from(context);
//    }
//
//    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        linearLayout = new LinearLayout(context);
//        inflater = LayoutInflater.from(context);
//    }
//
//    private Handler mHandler = new Handler();
//
//    private Runnable r = new Runnable() {//循环滚动
//        @Override
//        public void run() {
//            //do something
//            //每隔1s循环执行run方法
//            if (!isStop) {
//                viewType++;
//                mHandler.postDelayed(this, 2000);
//                invalidate();
//                refresh();
//            } else {
//                mHandler.removeCallbacks(r);
//            }
//        }
//    };
//
//
//    private void refresh() {
//        if (adapter != null) {
//            viewType++;
//            linearLayout.removeAllViews();
//            linearLayout.invalidate();
//            if (centerView == null) {
//                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//                centerView = adapter.onBindViewHolder(
//                        adapter.onCreateViewHolder(inflater, null, viewType)
//                        , viewType, adapter.getData().get(viewType)
//                );
//                centerView.setLayoutParams(getLayoutParams());
//                linearLayout.addView(centerView);
//            } else {
//                if (viewType >= adapter.getItemCount()) {
//                    viewType = 0;
//                }
//                setAnimate(centerView).setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        linearLayout.removeView(centerView);
//                        centerView = hidView;
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
//                setAnimate2(hidView);
//                hidView = adapter.onBindViewHolder(
//                        adapter.onCreateViewHolder(inflater, null, viewType )
//                        , viewType, adapter.getData().get(viewType )
//                );
//
//                centerView.setLayoutParams(getLayoutParams());
//                hidView.setLayoutParams(getLayoutParams());
//                linearLayout.addView(centerView);
//                linearLayout.addView(hidView);
////                centerView = hidView;
//
//            }
//        }
//
//
//    }
//
//
//
//    /**
//     * 属性动画
//     * 平移
//     */
//    private TranslateAnimation setAnimate(View view) {
////      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
////      创建属性动画对象，并设置移动的方向和偏移量
////      translationX是imageview的平移属性
//        TranslateAnimation objectAnimator =
//                new TranslateAnimation(0, -(float) getWidth(),
//                        0, 0);
////      设置移动时间
//        objectAnimator.setDuration(500);
////      开始动画
//        objectAnimator.setFillAfter(true);
//        view.setAnimation(objectAnimator);
//        objectAnimator.start();
//
//        return objectAnimator;
//    }
//    /**
//     * 属性动画
//     * 平移
//     */
//    private TranslateAnimation setAnimate2(View view) {
////      imageView中凡是有get，set方法的属性，都可以通过属性动画操作
////      创建属性动画对象，并设置移动的方向和偏移量
////      translationX是imageview的平移属性
//        TranslateAnimation objectAnimator =
//                new TranslateAnimation((float) getWidth(), 0,
//                        0, 0);
////      设置移动时间
//        objectAnimator.setDuration(500);
////      开始动画
//        objectAnimator.setFillAfter(true);
//        view.setAnimation(objectAnimator);
//        objectAnimator.start();
//
//        return objectAnimator;
//    }
//
//    public void setAdapter(HorizontaViewAdapter adapter) {
//        this.adapter = adapter;
//        mHandler.postDelayed(r, 1000);
//    }
//
//    public static abstract class HorizontaViewAdapter<T> {//适配器抽象类
//        private List<T> data;
//
//        public HorizontaViewAdapter(List<T> data) {
//            this.data = data;
//        }
//
//        public List<T> getData() {
//            return data;
//        }
//
//        public abstract int getItemCount();
//
//        public abstract View onBindViewHolder(View itemView, int position, T itemData);
//
//        protected abstract View onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);
//    }
//
//}

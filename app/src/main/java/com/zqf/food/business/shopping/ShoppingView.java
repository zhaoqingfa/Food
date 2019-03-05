package com.zqf.food.business.shopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zqf.food.R;

import java.util.List;

/**
 * Created by v_zhaoqingfa on 2019/2/21.
 */

public class ShoppingView extends LinearLayout {
    private LinearLayout shopClearView;
    private RecyclerView shopListView;
    private ShoppingAdapter adapter;
    private GestureDetector mGestureDetector;

    private float oldY = 0;
    private boolean isListTop = true;

    private float downY = 0;

    public ShoppingView(Context context) {
        super(context);
        init(context);
    }

    public ShoppingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShoppingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_shopping, this);
        shopClearView = findViewById(R.id.mll_clear);
        shopListView = findViewById(R.id.mrl_shop_list);

        mGestureDetector = new GestureDetector(context, getGestureListener());

        shopListView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ShoppingAdapter(context);
        shopListView.setAdapter(adapter);
        adapter.setCallBack(getCallBack());
        shopListView.addOnScrollListener(getScrollListener());
    }

    private RecyclerView.OnScrollListener getScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (shopListView.canScrollVertically(-1)) {
                    isListTop = false;
                } else {
                    isListTop = true;
                }
            }
        };
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float newY = ev.getRawY();
                if (oldY - newY < 0) {  // 往下滑（往上滑不做处理）
                    if (isListTop) {  // 列表第一行在顶部, 拦截事件，自己处理
                        return true;
                    }
                }
                oldY = newY;
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private GestureDetector.OnGestureListener getGestureListener() {
        return new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getRawY() - downY;
                if (y > 0) {  // 往下滑（往上滑不做处理）
                    if (isListTop) {  // 列表第一行在顶部
                        setMarginBottom(true, y);
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                float y1 = event.getRawY() - downY;
                if (y1 > 0) {  // 往下滑（往上滑不做处理）
                    if (isListTop) {  // 列表第一行在顶部
                        setMarginBottom(true, getMeasuredHeight());
                        return true;
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setMarginBottom(boolean isDown, float y) {
        LinearLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (isDown) {
            y = -y;
        }
        layoutParams.bottomMargin = (int) y;
        setLayoutParams(layoutParams);
    }

    private ShoppingAdapter.ShoppingAdapterCallBack getCallBack() {
        return new ShoppingAdapter.ShoppingAdapterCallBack() {
            @Override
            public void add(int index) {

            }

            @Override
            public void down(int index) {

            }
        };
    }

    public void updateDate(List<ShopBean> shopBeans) {
        if (shopBeans == null && shopBeans.size() <= 0) {
            return;
        }
        adapter.updateDate(shopBeans);
    }

}

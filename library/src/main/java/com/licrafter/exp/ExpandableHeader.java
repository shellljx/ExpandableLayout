package com.licrafter.exp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * author: shell
 * date 2017/1/11 下午9:50
 **/
public class ExpandableHeader extends FrameLayout {

    private HeaderCollapseListener mListener;
    private float mLastedY;
    private float mLastX;
    private int mThreshold;


    public ExpandableHeader(Context context) {
        this(context, null);
    }

    public ExpandableHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float y = ev.getRawY();
        float x = ev.getRawX();
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastedY = y;
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastedY - y;
                float dx = mLastX - x;
                if (Math.abs(dy) > Math.abs(dx)) {
                    mLastedY = y;
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getRawY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastedY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastedY - y;
                if (Math.abs(dy) > mThreshold && dy > 0 && mListener != null) {
                    mListener.collapse();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void setThreshold(int threshold) {
        mThreshold = threshold;
    }

    public void setTopMargin(int topMargin) {
        getMarginLayoutParams().topMargin = topMargin;
        requestLayout();
    }

    public MarginLayoutParams getMarginLayoutParams() {
        return (MarginLayoutParams) getLayoutParams();
    }


    public void setCollapseListener(HeaderCollapseListener listener) {
        mListener = listener;
    }

    public interface HeaderCollapseListener {
        void collapse();
    }

}

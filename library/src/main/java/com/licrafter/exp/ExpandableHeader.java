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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getRawY();
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mLastedY = y;
            case MotionEvent.ACTION_MOVE:
                float dy = mLastedY - y;
                if (Math.abs(dy) > mThreshold && dy > 0) {
                    if (mListener != null) {
                        mListener.collapse();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
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

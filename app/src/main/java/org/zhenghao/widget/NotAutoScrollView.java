package org.zhenghao.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by www on 2018/1/12.
 */

public class NotAutoScrollView extends ScrollView {
    public NotAutoScrollView(Context context) {
        super(context);
    }

    public NotAutoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotAutoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
}

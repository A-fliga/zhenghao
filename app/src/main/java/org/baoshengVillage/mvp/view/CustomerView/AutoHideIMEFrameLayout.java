package org.baoshengVillage.mvp.view.CustomerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * Created by www on 2017/6/12.
 */
public class AutoHideIMEFrameLayout extends FrameLayout {

    private Boolean isLongPress = false;
    private Boolean isFirstPress = true;

    public AutoHideIMEFrameLayout(Context context) {
        super(context);
    }

    public AutoHideIMEFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHideIMEFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Context context = getContext();
        if (context == null || !(context instanceof Activity)) {
            return super.dispatchTouchEvent(ev);
        }
        Activity activity = (Activity) context;
        View focusView = activity.getCurrentFocus();
        //给focusView设置点击监听和长按监听
        initFocusView(focusView);
        //点下去时的操作
        whenActionDown(ev);
        //点击抬起的时候操作
        whenActionUp(ev, focusView);
        return super.dispatchTouchEvent(ev);
    }

    private void whenActionDown(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            isFirstPress = false;
        }
    }

    private void whenActionUp(MotionEvent ev, View focusView) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (focusView != null && shouldHideInputMethod(focusView, ev) && !isLongPress) {
                hideInputMethod(focusView);
            }
            isLongPress = false;
        }
    }

    private void initFocusView(View focusView) {
        if (focusView != null) {
            focusView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isLongPress = true;
                    return false;
                }
            });
            if (focusView instanceof EditText) {
                final EditText et = (EditText) focusView;
                et.setOnFocusChangeListener(new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            et.setCursorVisible(true);
                        }
                    }
                });
                et.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et.setCursorVisible(true);
                    }
                });
            }
        }
    }

    private boolean shouldHideInputMethod(View focusView, MotionEvent event) {
        Rect rect = new Rect();
        focusView.getHitRect(rect);
        if (rect.contains((int) event.getX(), (int) event.getY())) {
            return false;
        }
        return true;
    }

    private synchronized void hideInputMethod(View currentFocus) {
        if (currentFocus == null) {
            return;
        }
        if (!isFirstPress && !isLongPress) {
            InputMethodManager imm = (InputMethodManager) currentFocus.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if (currentFocus instanceof EditText) {
                EditText et = (EditText) currentFocus;
                et.setCursorVisible(false);
            }
        }
    }
}
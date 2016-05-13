package com.onecodelabs.reminderexample.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseFLView extends FrameLayout {

    private boolean initialized;
    protected View root;

    public BaseFLView(Context context) {
        super(context);
    }

    public BaseFLView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFLView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void initialize() {
        if (initialized) return;
        initialized = true;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(layout(), this);
        this.root = root;
        setUi(root);
        init();
        populate();
        setListeners();
    }

    protected abstract int layout();

    protected abstract void setUi(View root);

    protected abstract void init();

    protected abstract void populate();

    protected abstract void setListeners();
}

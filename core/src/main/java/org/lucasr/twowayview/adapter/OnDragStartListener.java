package org.lucasr.twowayview.adapter;

import android.view.View;

import org.lucasr.twowayview.util.AnimationsUtil;

/**
 * Created by samuil.yanovski on 26.1.2015 г..
 */
public class OnDragStartListener implements View.OnLongClickListener {
    private EnhancedAdapter<?> mAdapter;

    public OnDragStartListener(EnhancedAdapter<?> adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean onLongClick(View v) {
        if (mAdapter.isAllowDrag()) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            highlightDragStart(v);
            return true;
        }
        return false;
    }

    protected void highlightDragStart(View v) {
        AnimationsUtil.fadeOut(v);
    }
}

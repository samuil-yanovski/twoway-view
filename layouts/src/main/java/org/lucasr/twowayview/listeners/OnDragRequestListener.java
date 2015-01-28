package org.lucasr.twowayview.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.View;

import org.lucasr.twowayview.ClickItemTouchListener;
import org.lucasr.twowayview.util.AnimationsUtil;
import org.lucasr.twowayview.widget.TwoWayView;

/**
 * Created by samuil.yanovski on 28.1.2015 г..
 */
public class OnDragRequestListener extends ClickItemTouchListener {

    public OnDragRequestListener(TwoWayView recyclerView) {
        super(recyclerView);
    }

    protected boolean performItemClick(RecyclerView parent, View view, int position, long id) {
        return false;
    }

    protected boolean performItemLongClick(RecyclerView parent, View view, int position, long id) {
        if (parent instanceof TwoWayView) {
            TwoWayView hostView = (TwoWayView) parent;
            if (hostView.isAllowDrag()) {
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(null, shadowBuilder, view, 0);
                highlightDragStart(view);
                return true;
            }
        }
        return false;
    }

    protected void highlightDragStart(View v) {
        AnimationsUtil.fadeOut(v);
    }
}

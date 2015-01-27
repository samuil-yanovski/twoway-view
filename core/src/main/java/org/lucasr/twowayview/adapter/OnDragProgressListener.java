package org.lucasr.twowayview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

import org.lucasr.twowayview.util.AnimationsUtil;

/**
 * Created by samuil.yanovski on 26.1.2015 г..
 */
public class OnDragProgressListener implements View.OnDragListener {
    private EnhancedAdapter<?> mAdapter;

    public OnDragProgressListener(EnhancedAdapter<?> adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (mAdapter.isAllowDrag()) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DROP: {
                    View view = (View) event.getLocalState();
                    highlightDragEnd(view);

                    int fromPosition = getPosition(view);
                    int toPosition = getPosition(v);

                    mAdapter.notifyItemDragged(fromPosition, toPosition);
                    break;
                }
                case DragEvent.ACTION_DRAG_ENDED: {
                    View view = (View) event.getLocalState();
                    highlightDragEnd(view);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    protected void highlightDragEnd(View view) {
        AnimationsUtil.fadeIn(view);
    }

    protected int getPosition(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        return layoutParams.getViewPosition();
    }
}

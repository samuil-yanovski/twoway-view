package org.lucasr.twowayview.adapter;

import android.view.DragEvent;
import android.view.View;

import org.lucasr.twowayview.R;

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
            if (event.getAction() == DragEvent.ACTION_DROP) {
                View view = (View) event.getLocalState();

                int fromPosition = (int) view.getTag(R.id.twowayview_position);
                int toPosition = (int) v.getTag(R.id.twowayview_position);

                view.setVisibility(View.VISIBLE);
                mAdapter.notifyItemDragged(fromPosition, toPosition);
            }
            return true;
        }
        return false;
    }
}

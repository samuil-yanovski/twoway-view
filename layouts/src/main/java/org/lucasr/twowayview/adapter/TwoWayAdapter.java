package org.lucasr.twowayview.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by samuil.yanovski on 26.1.2015 г..
 */
public abstract class TwoWayAdapter<VH extends RecyclerView.ViewHolder> extends
    RecyclerView.Adapter<VH> {

    protected abstract void moveItem(int fromPosition, int toPosition);

    public void notifyItemDragged(int fromPosition, int toPosition) {
        moveItem(fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
}

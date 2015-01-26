package org.lucasr.twowayview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.lucasr.twowayview.R;

/**
 * Created by samuil.yanovski on 26.1.2015 г..
 */
public abstract class EnhancedAdapter<VH extends RecyclerView.ViewHolder> extends
    RecyclerView.Adapter<VH> {

    private boolean allowDrag;
    private OnDragStartListener mOnDragStartListener;
    private OnDragProgressListener mOnDragProgressListener;

    protected abstract void moveItem(int fromPosition, int toPosition);

    public EnhancedAdapter() {
        init();
    }

    protected void init() {
        setAllowDrag(true);
        mOnDragProgressListener = new OnDragProgressListener(this);
        mOnDragStartListener = new OnDragStartListener(this);
    }

    public boolean isAllowDrag() {
        return allowDrag;
    }

    public void setAllowDrag(boolean allowDrag) {
        this.allowDrag = allowDrag;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.e("###", "onBindViewHolder from: " + position);
        holder.itemView.setTag(R.id.twowayview_position, position);
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
        holder.itemView.setOnLongClickListener(mOnDragStartListener);
        holder.itemView.setOnDragListener(mOnDragProgressListener);
    }

    @Override
    public void onViewDetachedFromWindow(VH holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.setOnLongClickListener(null);
        holder.itemView.setOnDragListener(null);
    }

    public void notifyItemDragged(int fromPosition, int toPosition) {
        moveItem(fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemChanged(fromPosition);
        notifyItemChanged(toPosition);
    }
}

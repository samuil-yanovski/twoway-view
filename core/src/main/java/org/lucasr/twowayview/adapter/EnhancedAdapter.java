package org.lucasr.twowayview.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;

/**
 * Created by samuil.yanovski on 26.1.2015 г..
 */
public abstract class EnhancedAdapter<VH extends RecyclerView.ViewHolder> extends
    RecyclerView.Adapter<VH> {

    private boolean allowDrag;
    private OnDragStartListener mOnDragStartListener;
    private OnDragProgressListener mOnDragProgressListener;

    private RecyclerView rv;

    protected abstract void moveItem(int fromPosition, int toPosition);

    public EnhancedAdapter(RecyclerView rv) {
        init();
        this.rv = rv;
    }

    protected void init() {
        setAllowDrag(false);
        setOnDragProgressListener(new OnDragProgressListener(this));
        setOnDragStartListener(new OnDragStartListener(this));
    }

    protected void setOnDragStartListener(OnDragStartListener onDragStartListener) {
        mOnDragStartListener = onDragStartListener;
    }

    protected void setOnDragProgressListener(OnDragProgressListener onDragProgressListener) {
        mOnDragProgressListener = onDragProgressListener;
    }

    public boolean isAllowDrag() {
        return allowDrag;
    }

    public void setAllowDrag(boolean allowDrag) {
        this.allowDrag = allowDrag;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rv.invalidateItemDecorations();
            }
        }, 500);


        //        int minPosition = Math.min(fromPosition, toPosition);
        //        int maxPosition = Math.max(fromPosition, toPosition);
        //
        //        for (int position = minPosition; position <= maxPosition; ++position) {
        //            notifyItemChanged(position);
        //        }
        //
        //        notifyItemMoved(fromPosition, toPosition);
    }
}

package com.onecodelabs.reminderexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class UtilAdapter<T, VH> extends BaseAdapter {

    private List<T> mDataset;
    protected Context mContext;

    public UtilAdapter(Context context) {
        this.mDataset = new ArrayList<>();
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public T getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)  {
        VH vh;
        if (view == null) {
            view = View.inflate(mContext, layout(), null);
            vh = viewHolder(view);
            view.setTag(vh);
        } else {
            vh = (VH) view.getTag();
        }
        populate(getItem(position), vh);
        return view;
    }

    protected abstract int layout();

    protected abstract VH viewHolder(View root);

    protected abstract void populate(T data, VH viewHolder);

    //    region Util methods

    public final void insert(T elem, int position) {
        mDataset.add(position, elem);
        notifyDataSetChanged();
    }

    public final void appendTop(T elem) {
        insert(elem, 0);
    }

    public final void appendTopAll(Collection<T> elems) {
        mDataset.addAll(0, elems);
        notifyDataSetChanged();
    }

    public final void appendBottom(T elem) {
        insert(elem, mDataset.size());
    }

    public final void appendBottomAll(Collection<T> elems) {
        int startIndex = mDataset.size();
        mDataset.addAll(startIndex, elems);
        notifyDataSetChanged();
    }

    public final void remove(int position) {
        mDataset.remove(position);
        notifyDataSetChanged();
    }

    public final void clear() {
        int size = mDataset.size();
        mDataset.clear();
        notifyDataSetChanged();
    }

    public final List<T> getDataSet() {
        return mDataset;
    }
//    endregion
}

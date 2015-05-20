package com.demo.mycommonadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/5/20.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mLayoutId;

    public CommonAdapter(Context context,List<T> datas,int layoutId){
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder = ViewHolder.get(mContext,view,viewGroup,mLayoutId,i);
        convert(viewHolder, getItem(i));
        return viewHolder.getConvertView();
    }

    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(mDatas.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return mDatas.contains(elem);
    }

    /** Clear data list */
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public abstract void convert(ViewHolder helper, T item);
}

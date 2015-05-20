package com.demo.mycommonadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/5/20.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private static View mConvertView;

    public ViewHolder(Context context,ViewGroup parent,int layoutId,int postion){
        mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int postion) {
        if (convertView == null){
            return new ViewHolder(context,parent,layoutId,postion);
        }
        return (ViewHolder)convertView.getTag();
    }

    public <T extends View> T getViewById(int viewId)
    {

        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    public ViewHolder setText(int resId,String text){
        TextView tv = getViewById(resId);
        tv.setText(text);
        return this;
    }
}

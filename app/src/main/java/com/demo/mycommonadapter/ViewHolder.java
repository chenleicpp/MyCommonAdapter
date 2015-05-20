package com.demo.mycommonadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/5/20.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private static View mConvertView;
    private Context mContext;

    public ViewHolder(Context context,ViewGroup parent,int layoutId,int postion){
        mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mContext = context;
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

    /**
     * Will set the text of a TextView.
     * @param viewId The view id.
     * @param value  The text to put in the text view.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setText(int viewId, String value) {
        TextView view = getViewById(viewId);
        view.setText(value);
        return this;
    }

    /**
     * Will set the image of an ImageView from a resource id.
     * @param viewId     The view id.
     * @param imageResId The image resource id.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = getViewById(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * Will set background color of a view.
     * @param viewId The view id.
     * @param color  A color, not a resource id.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getViewById(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * Will set background of a view.
     * @param viewId        The view id.
     * @param backgroundRes A resource to use as a background.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getViewById(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getViewById(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Will set text color of a TextView.
     * @param viewId       The view id.
     * @param textColorRes The text color resource id.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getViewById(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     * @param viewId   The view id.
     * @param drawable The image drawable.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getViewById(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Will download an image from a URL and put it in an ImageView.<br/>
     * It uses Square's Picasso library to download the image asynchronously and put the result into the ImageView.<br/>
     * Picasso manages recycling of views in a ListView.<br/>
     * If you need more control over the Picasso settings, use {ViewHolder#setImageBuilder}.
     * @param viewId   The view id.
     * @param imageUrl The image URL.
     * @return The ViewHolder for chaining.
     */
//    public ViewHolder setImageUrl(int viewId, String imageUrl) {
//        ImageView view = getViewById(viewId);
//        Picasso.with(mContext).load(imageUrl).into(view);
//        return this;
//    }

    /**
     * Will download an image from a URL and put it in an ImageView.<br/>
     * @param viewId         The view id.
     * @param requestBuilder The Picasso request builder. (e.g. Picasso.with(context).load(imageUrl))
     * @return The ViewHolder for chaining.
     */
//    public ViewHolder setImageBuilder(int viewId, RequestCreator requestBuilder) {
//        ImageView view = getViewById(viewId);
//        requestBuilder.into(view);
//        return this;
//    }

    /** Add an action to set the image of an image view. Can be called multiple times. */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getViewById(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * Add an action to set the alpha of a view. Can be called multiple times.
     * Alpha between 0-1.
     */
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getViewById(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getViewById(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or GONE (false).
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for GONE.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getViewById(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * Add links into a TextView.
     * @param viewId The id of the TextView to linkify.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder linkify(int viewId) {
        TextView view = getViewById(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /** Apply the typeface to the given viewId, and enable subpixel rendering. */
    public ViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = getViewById(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /** Apply the typeface to all the given viewIds, and enable subpixel rendering. */
    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getViewById(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getViewById(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getViewById(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the range of a ProgressBar to 0...max.
     * @param viewId The view id.
     * @param max    The max value of a ProgressBar.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setMax(int viewId, int max) {
        ProgressBar view = getViewById(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     * @param viewId The view id.
     * @param rating The rating.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getViewById(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getViewById(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the on click listener of the view.
     * @param viewId   The view id.
     * @param listener The on click listener;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * Sets the on touch listener of the view.
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getViewById(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * Sets the on long click listener of the view.
     * @param viewId   The view id.
     * @param listener The on long click listener;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getViewById(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * Sets the tag of the view.
     * @param viewId The view id.
     * @param tag    The tag;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setTag(int viewId, Object tag) {
        View view = getViewById(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the tag of the view.
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getViewById(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * Sets the checked status of a checkable.
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getViewById(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * Sets the adapter of a adapter view.
     * @param viewId  The view id.
     * @param adapter The adapter;
     * @return The ViewHolder for chaining.
     */
    public ViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = getViewById(viewId);
        view.setAdapter(adapter);
        return this;
    }
}

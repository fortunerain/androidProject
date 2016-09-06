package com.example.westinfacemobile.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.westinfacemobile.R;

public class ActivityDetailImageAdapter extends BaseAdapter {
	private Context mContext;
    private int iBackGround;
    private Integer[] images;
    
	public ActivityDetailImageAdapter(Context mContext, Integer[] images) {
		this.mContext = mContext;
		this.images = images;
		
		TypedArray typeArray = mContext.obtainStyledAttributes(R.styleable.Gallery);
        iBackGround = typeArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground,
                       0);
        typeArray.recycle();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
        imageView = new ImageView(mContext);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundResource(iBackGround);
        return imageView;
	}

}

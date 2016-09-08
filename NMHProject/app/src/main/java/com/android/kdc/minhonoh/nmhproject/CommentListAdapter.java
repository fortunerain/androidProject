package com.android.kdc.minhonoh.nmhproject;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import model.ColorObject;

/**
 * Created by min-ho.noh on 2014-11-11.
 */
public class CommentListAdapter extends ArrayAdapter<ColorObject> {

    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private List<ColorObject> listObj;


    public CommentListAdapter(Context context, int resource, List<ColorObject> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.layoutInflater = layoutInflater;
        this.listObj = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_viewholder, parent, false);
            //holder 정의
            TextView tv = (TextView) convertView.findViewById(R.id.textView1);
            ImageView iv = (ImageView) convertView.findViewById(R.id.imageView);
            holder = new ViewHolder(tv, iv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String colorName = listObj.get(position).getColor();
        int colorValue = Color.parseColor(listObj.get(position).getValue());
        holder.textView.setText(colorName);
        holder.textView.setTextColor(colorValue);
        holder.imageView.setBackgroundColor(colorValue);
        return convertView;
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;

        ViewHolder(TextView textView, ImageView imageView) {
            this.textView = textView;
            this.imageView = imageView;
        }
    }

}

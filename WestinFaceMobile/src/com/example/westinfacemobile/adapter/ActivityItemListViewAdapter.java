package com.example.westinfacemobile.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.westinfacemobile.R;
import com.example.westinfacemobile.model.ActivityModel;

/**
 * Created by min-ho.noh on 2015-04-06.
 */
public class ActivityItemListViewAdapter extends ArrayAdapter<ActivityModel> {

    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private List<ActivityModel> listObj;
    

    public ActivityItemListViewAdapter(Context context, int resource, List<ActivityModel> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.layoutInflater = layoutInflater;
        this.listObj = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_listview, parent, false);
            //holder
            ImageView img_acti_icon = (ImageView) convertView.findViewById(R.id.img_acti_icon);
            TextView text_acti_header = (TextView) convertView.findViewById(R.id.text_acti_header);
            TextView text_acti_body = (TextView) convertView.findViewById(R.id.text_acti_body);

            //글자가 사이즈를 넘어가는 경우 ... 효과 주기
//            text_acti_header.setSingleLine(true);
//            text_acti_header.setEllipsize(TruncateAt.END);
//            text_acti_body.setSingleLine(false);
//            text_acti_body.setMaxLines(4);
//            text_acti_body.setEllipsize(TruncateAt.END);
            
            //holder 셋팅
            holder = new ViewHolder(img_acti_icon, text_acti_header, text_acti_body);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        
        ActivityModel currentItemModel = listObj.get(position);
        String text_acti_header = currentItemModel.getText_acti_header();
        String text_acti_body = currentItemModel.getText_acti_body();
        Bitmap img_acti_icon = currentItemModel.getImg_acti_icon();
        
        
//        holder.img_acti_icon.setBackgroundResource(R.drawable.ic_launcher);
        holder.text_acti_header.setText(text_acti_header);
        holder.text_acti_body.setText(text_acti_body);
        holder.img_acti_icon.setScaleType(ScaleType.FIT_CENTER);
        holder.img_acti_icon.setImageBitmap(img_acti_icon);
        
        return convertView;
    }
    
    class ViewHolder{
    	ImageView img_acti_icon;
    	TextView text_acti_header;
        TextView text_acti_body;
		
        public ViewHolder(ImageView img_acti_icon, TextView text_acti_header, TextView text_acti_body) {
			super();
			this.img_acti_icon = img_acti_icon;
			this.text_acti_header = text_acti_header;
			this.text_acti_body = text_acti_body;
		}
    }

}

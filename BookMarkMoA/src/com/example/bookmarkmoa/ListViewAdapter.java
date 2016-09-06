package com.example.bookmarkmoa;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.bookmarkmoa.model.BookMarkModel;

/**
 * Created by min-ho.noh on 2014-11-11.
 */
public class ListViewAdapter extends ArrayAdapter<BookMarkModel> {

    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private List<BookMarkModel> listObj;


    public ListViewAdapter(Context context, int resource, List<BookMarkModel> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.layoutInflater = layoutInflater;
        this.listObj = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_listview, parent, false);
            //holder
            TextView txt_url = (TextView) convertView.findViewById(R.id.txt_url);
            TextView txt_title = (TextView) convertView.findViewById(R.id.txt_title);
            TextView txt_visits = (TextView) convertView.findViewById(R.id.txt_visits);
            ImageView img_favicon = (ImageView) convertView.findViewById(R.id.img_favicon);
            
            //글자가 사이즈를 넘어가는 경우 ... 효과 주기
            txt_url.setSingleLine(true);
            txt_url.setEllipsize(TruncateAt.END);
            txt_title.setSingleLine(true);
            txt_title.setEllipsize(TruncateAt.END);
            
            //holder 셋팅
            holder = new ViewHolder(txt_url, txt_title, txt_visits, img_favicon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String url = listObj.get(position).getUrl();
        String title = listObj.get(position).getTitle();
        String visits = listObj.get(position).getVisits();
        Bitmap favicon = listObj.get(position).getFavicon();
        holder.txt_url.setText(url);
        holder.txt_title.setText(title);
        holder.txt_visits.setText(visits);
        //
        holder.img_favicon.setScaleType(ScaleType.FIT_CENTER);
        holder.img_favicon.setImageBitmap(favicon);
        
        return convertView;
    }

    class ViewHolder{
        TextView txt_url;
        TextView txt_title;
        TextView txt_visits;
        ImageView img_favicon;
		
        public ViewHolder(TextView txt_url, TextView txt_title,
				TextView txt_visits, ImageView img_favicon) {
			super();
			this.txt_url = txt_url;
			this.txt_title = txt_title;
			this.txt_visits = txt_visits;
			this.img_favicon = img_favicon;
		}
        
    }

}

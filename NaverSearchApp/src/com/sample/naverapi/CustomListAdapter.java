package com.sample.naverapi;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sample.naverapi.medel.XmlData;

public class CustomListAdapter extends ArrayAdapter<Object> {
	public static final String TAG = "CustomListAdapter";
	private ArrayList<XmlData> items;
	private LayoutInflater layoutInflater;
    private ViewHolder holder;
    
	public CustomListAdapter(Context context, int textViewResourceId, ArrayList items, LayoutInflater layoutInflater) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.layoutInflater = layoutInflater;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		 if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listitem, parent, false);
            //holder
            TextView title = (TextView) convertView.findViewById(R.id.dataItem01);
            TextView description = (TextView) convertView.findViewById(R.id.dataItem02);
            TextView link = (TextView) convertView.findViewById(R.id.link);
            TextView more = (TextView) convertView.findViewById(R.id.dataItem03);
            ImageView linkImage = (ImageView) convertView.findViewById(R.id.iconItem);
            
            holder = new ViewHolder(title, description, link, more, linkImage);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
		
	 	XmlData xmlData = (XmlData) items.get(position);
        String title = xmlData.getTitle();
        String description = xmlData.getDescription();
        String link = xmlData.getOriginallink();
        
        int Last = this.getCount();

		if ((Last - 1) == position) {
	        holder.title.setText("");
	        holder.description.setText("");	
	        holder.link.setText("");	
			holder.more.setText("10개 더보기");
		}else {
	        holder.title.setText(title);
	        holder.description.setText(description);	
	        holder.link.setText(link);	
    		
	        holder.linkImage.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					LinearLayout listViewLayout = (LinearLayout)v.getParent();
					TextView link = (TextView) listViewLayout.findViewById(R.id.link);
					String linkUrl = link.getText().toString();
					Intent intent = new Intent();
			        intent.setAction(Intent.ACTION_VIEW);
			        intent.addCategory(Intent.CATEGORY_DEFAULT);
			        intent.setData(Uri.parse(linkUrl));
			        v.getContext().startActivity(intent);
					Log.d(TAG, "link  :  "+link.getText().toString());
				}
			});
		}
        
        return convertView;
	}
	
	class ViewHolder{
    	TextView title;
        TextView description;
        TextView link;
        TextView more;
		ImageView linkImage;
		
        public ViewHolder(TextView title, TextView description, TextView link, TextView more, ImageView linkImage) {
			super();
			this.title = title;
			this.description = description;
			this.link = link;
			this.more = more;
			this.linkImage = linkImage;
        }
    }
	
}
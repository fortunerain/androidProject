package com.android.kdc.minhonoh.nmhproject;

import java.util.List;

import model.ArtworkModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by min-ho.noh on 2014-11-11.
 */
public class ListViewAdapter extends ArrayAdapter<ArtworkModel> {

    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private List<ArtworkModel> listObj;


    public ListViewAdapter(Context context, int resource, List<ArtworkModel> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.layoutInflater = layoutInflater;
        this.listObj = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_listview, parent, false);
            //holder
            TextView txt_tile = (TextView) convertView.findViewById(R.id.txt_tile);
            TextView txt_artistName = (TextView) convertView.findViewById(R.id.txt_artistName);
            TextView txt_likesCnt = (TextView) convertView.findViewById(R.id.txt_likesCnt);
            holder = new ViewHolder(txt_tile, txt_artistName, txt_likesCnt);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        String title = listObj.get(position).getTitle();
        String artistName = listObj.get(position).getArtistName();
        String likesCnt = listObj.get(position).getLikesCnt();
        holder.txt_tile.setText(title);
        holder.txt_artistName.setText(artistName);
        holder.txt_likesCnt.setText(likesCnt);
        
        return convertView;
    }

    class ViewHolder{
        TextView txt_tile;
        TextView txt_artistName;
        TextView txt_likesCnt;
		
        public ViewHolder(TextView txt_tile, TextView txt_artistName,
				TextView txt_likesCnt) {
			super();
			this.txt_tile = txt_tile;
			this.txt_artistName = txt_artistName;
			this.txt_likesCnt = txt_likesCnt;
		}
        
    }

}

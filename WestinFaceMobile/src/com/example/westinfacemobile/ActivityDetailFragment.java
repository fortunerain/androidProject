package com.example.westinfacemobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.TextView;

import com.example.westinfacemobile.adapter.ActivityDetailImageAdapter;
import com.example.westinfacemobile.comm.CommApplication;
import com.example.westinfacemobile.comm.activities.BaseFragment;

public class ActivityDetailFragment extends BaseFragment {
	private int position;
	private CommApplication app;
	
	public ActivityDetailFragment(int position) {
		this.position = position;
	}
	
	public ActivityDetailFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_list_detail,
				container, false);
		app = (CommApplication) view.getContext().getApplicationContext();
		TextView title = (TextView)view.findViewById(R.id.text_acti_detail_header);
		TextView desc = (TextView)view.findViewById(R.id.text_acti_detail_body);
		title.setText(app.getActivity_list_title()[position]);
		desc.setText(app.getActivity_list_desc_detail()[position] + app.getActivity_list_desc()[position]);
		
		
		// Gallery view 생성
		Gallery galleryImage = (Gallery)view.findViewById(R.id.gallery1);
       
		Integer[][] images = app.getActivity_list_detail_img();
        /// adapter에 적용을 한다.
        galleryImage.setAdapter(new ActivityDetailImageAdapter(this.getActivity(), images[position]));
		
		return view;
	}

}

package com.example.westinfacemobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.westinfacemobile.comm.activities.BaseFragment;

public class ActivityScheduleFragment extends BaseFragment {
	public static final String TAG = "ActivityScheduleFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.activity_schedule, container, false);
    	ImageView iv = (ImageView)v.findViewById(R.id.img_schedule);
    	iv.setBackgroundResource(R.drawable.schedule);
    	
        return v;
    }

}

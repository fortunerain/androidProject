package com.example.westinfacemobile.comm.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.westinfacemobile.MainTabActivity;

public class BaseFragment extends Fragment {
	protected MainTabActivity mainTabActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainTabActivity =  (MainTabActivity) this.getActivity();
	}
	
//	public boolean onBackPressed() {
//		return false;
//	}

//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//	}

}
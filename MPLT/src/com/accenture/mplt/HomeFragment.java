package com.accenture.mplt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.constant.CommonConstants;
import com.accenture.mplt.comm.constant.StatusCodeEnum;

public class HomeFragment extends Fragment implements android.view.View.OnClickListener {
	private ImageView mainImageStart, mainImageAdmin;
	private PasswordCustomDialog passwordCustomDialog;
	private Fragment homeFragment;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        
		mainImageStart = (ImageView) v.findViewById(R.id.main_image_start);
		mainImageAdmin = (ImageView) v.findViewById(R.id.main_image_admin);
		
		mainImageStart.setOnClickListener(this);
		mainImageAdmin.setOnClickListener(this);
		
        return v;
    }
    
	@Override
	public void onClick(View v) {
		if (v == mainImageStart) {
			FragmentTabHost mTabHost = (FragmentTabHost)getActivity().findViewById(android.R.id.tabhost);
		    mTabHost.setCurrentTab(1);
			
			//공통변수 admin 셋팅
			CommApplication app = (CommApplication) this.getActivity().getApplication();
			app.setAdmin(false);
		} else if (v == mainImageAdmin) {
			String popupDesc = getString(R.string.popup_text_login_admin);
			passwordCustomDialog = new PasswordCustomDialog(v.getContext(), popupDesc, CommonConstants.ADMIN_USER_NAME, StatusCodeEnum.CHECK_ADMIN_PASSWORD, homeFragment);
			passwordCustomDialog.show();
		}
	}
	
}

package com.accenture.mplt;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.model.ResponseUsersModel;

/**
 * Created by min-ho.noh on 2014-12-01.
 */
public class UserListViewAdapter extends ArrayAdapter<ResponseUsersModel> {

    private LayoutInflater layoutInflater;
    private ViewHolder holder;
    private List<ResponseUsersModel> listObj;
    
    private final String REGISTER = "0";
    private final String SIX_MONTH_REGISTER = "1";
    private final String NON_REGISTER = "2";
	private OnClickListener mOnClickListener;

    public UserListViewAdapter(Context context, int resource, List<ResponseUsersModel> objects, LayoutInflater layoutInflater, OnClickListener mOnClickListener) {
        super(context, resource, objects);
        this.layoutInflater = layoutInflater;
        this.listObj = objects;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_userlistview, parent, false);
            //holder
            TextView eid = (TextView) convertView.findViewById(R.id.userlist_text_eid);
            TextView user_name = (TextView) convertView.findViewById(R.id.userlist_text_username);
            TextView header_title = (TextView) convertView.findViewById(R.id.userlist_text_header);
            Button btn_dialog_pay = (Button) convertView.findViewById(R.id.userlist_button_pay);
            Button btn_dialog = (Button) convertView.findViewById(R.id.userlist_button_dialog);

            //글자가 사이즈를 넘어가는 경우 ... 효과 주기
            user_name.setSingleLine(true);
            user_name.setEllipsize(TruncateAt.END);
            
            //holder 셋팅
            holder = new ViewHolder(eid, user_name, header_title, btn_dialog_pay, btn_dialog);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        
        ResponseUsersModel currentItemModel = listObj.get(position);
        String eid = currentItemModel.getUsername();
        String user_name = currentItemModel.getLast_name()+", "+currentItemModel.getFirst_name();
        holder.eid.setText(eid);
        holder.user_name.setText(user_name);
        
        String paid = currentItemModel.getPaid_fee();
        
        // 구분자 추가
        holder.header_title.setText(getListHeaderText(currentItemModel));
        holder.header_title.setVisibility(isFirstOf(currentItemModel) ? View.VISIBLE : View.GONE);
        
        // Button Background
        CommApplication app = (CommApplication) getContext().getApplicationContext();
        boolean isAdmin = app.isAdmin();
        if(isAdmin) {
        	holder.btn_dialog_pay.setOnClickListener(mOnClickListener);
        }
        holder.btn_dialog.setOnClickListener(mOnClickListener);
        
        if(REGISTER.equals(currentItemModel.getRegistType())) {
        	if("true".equals(paid)) {
        		holder.btn_dialog_pay.setBackgroundResource(R.drawable.paid);
        		String paidTagId = layoutInflater.getContext().getString(R.string.paid);
        		holder.btn_dialog_pay.setTag(paidTagId);
        	}else {
        		holder.btn_dialog_pay.setBackgroundResource(R.drawable.pay);
        		String payTagId = layoutInflater.getContext().getString(R.string.pay);
        		holder.btn_dialog_pay.setTag(payTagId);
        	}
        	holder.btn_dialog.setBackgroundResource(R.drawable.cancel_red);
        } else {
        	holder.btn_dialog_pay.setVisibility(View.INVISIBLE);
        	holder.btn_dialog.setBackgroundResource(R.drawable.register);
        }
        
        
        
        return convertView;
    }

    private String getListHeaderText(ResponseUsersModel currentItem) {
    	String headerTitle = "";
    	String type = currentItem.getRegistType();
    	if(type != null) {
    		
	    	if(SIX_MONTH_REGISTER.equals(type)) {
	    		headerTitle = layoutInflater.getContext().getString(R.string.regist_text_header1);
	    		holder.header_title.setBackgroundResource(R.color.recently_six_month_head_backgroud);
	    		int color = layoutInflater.getContext().getResources().getColor(R.color.recently_six_month_head);
	    		holder.header_title.setTextColor(color);
	    	} else if(NON_REGISTER.equals(type)){
	    		headerTitle = layoutInflater.getContext().getString(R.string.regist_text_header2);
	    		holder.header_title.setBackgroundResource(R.color.all_register_users_head_backgroud);
	    		int color = layoutInflater.getContext().getResources().getColor(R.color.all_register_users_head);
	    		holder.header_title.setTextColor(color);
	    	}
    	}
    	return headerTitle;
    }
    
    private boolean isFirstOf(ResponseUsersModel currentItem) {
    	
    	String type = currentItem.getRegistType();	// 0:신청, 1:6개월, 2:미신청 
    	boolean isFirst = false;
    	/*
    	 * 신청의 경우 registType별 구분이 필요없음.
    	 * 1. 6개월 or 미신청의 경우 현재의 model의 type에 해당하는 list를 새로 생성 
    	 * 2. 해당 list의 첫번째와 현재의 model과 비교.(userName으로 비교) 
    	 */
    	if(type != null && !type.equals(REGISTER)) {
	    	// listObj : 전체
	    	List<ResponseUsersModel> list = new ArrayList<ResponseUsersModel>();
	    	int allListSize = listObj.size();
	    	// 1
	    	for(int i=0; i<allListSize; i++) {
	    		if(listObj.get(i).getRegistType().equals(type)) {
	    			list.add(listObj.get(i));
	    		}
	    	}
	    	// 2
			if(currentItem.getUsername().equals(list.get(0).getUsername())) { 
				isFirst = true;
			} else {
				isFirst = false;
			}
    	}
    	return isFirst;
    }
    
    class ViewHolder{
    	TextView eid;			//hidden user enterprise name
        TextView user_name;		//user name
		TextView header_title;
		Button btn_dialog_pay;
		Button btn_dialog;
		
        public ViewHolder(TextView eid, TextView user_name, TextView header_title, Button btn_dialog_pay, Button btn_dialog) {
			super();
			this.eid = eid;
			this.user_name = user_name;
			this.header_title = header_title;
			this.btn_dialog_pay = btn_dialog_pay;
			this.btn_dialog = btn_dialog;
		}
    }

}

package com.example.westinfacemobile;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.westinfacemobile.adapter.GroupActivityListViewAdapter;
import com.example.westinfacemobile.comm.activities.BaseFragment;
import com.example.westinfacemobile.comm.contants.ProgressCodeEnum;
import com.example.westinfacemobile.comm.util.AsyncResponse;
import com.example.westinfacemobile.comm.util.ProgressDialogThread;
import com.example.westinfacemobile.model.ActivityModel;
import com.example.westinfacemobile.model.ResponseModel;

public class GroupActivityListFragment extends BaseFragment implements OnItemClickListener {
	public static final String TAG = "GroupActivityListFragment";
	private ListView listview;
	private GroupActivityListViewAdapter adapter;
	private List<ActivityModel> list;
	private LayoutInflater layoutInflater;
	private Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.group_activity_list, container, false);
        
  		listview = (ListView) v.findViewById(R.id.group_activity_listview);
		listview.setOnItemClickListener(this);
		
		layoutInflater = this.getActivity().getLayoutInflater();
		context = v.getContext();
  		
		
		AsyncResponse listener = new AsyncResponse() {
			
			@Override
			public void processDialogFinish(ResponseModel responseModel) {
				list = responseModel.getGroupActivityList();
				adapter = new GroupActivityListViewAdapter(context, R.layout.group_activity_list, list, layoutInflater);
				listview.setAdapter(adapter);
			}
		};
		
		//activity 정보만 list에 담기
		ProgressDialogThread thread = new ProgressDialogThread(context,listener);
		thread.execute(ProgressCodeEnum.GET_GROUP_ACTIVITY_LIST);
		
		
        return v;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
    
}

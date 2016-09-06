package com.example.westinfacemobile.model;

import java.util.List;

public class ResponseModel {
	private List<ActivityModel> activityList;
	private List<ActivityModel> groupActivityList;
	
	public List<ActivityModel> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<ActivityModel> activityList) {
		this.activityList = activityList;
	}
	public List<ActivityModel> getGroupActivityList() {
		return groupActivityList;
	}
	public void setGroupActivityList(List<ActivityModel> groupActivityList) {
		this.groupActivityList = groupActivityList;
	}

	
}

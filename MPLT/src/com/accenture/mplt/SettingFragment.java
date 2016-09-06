package com.accenture.mplt;

import java.text.ParseException;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.accenture.mplt.comm.CommApplication;
import com.accenture.mplt.comm.constant.StatusCodeEnum;
import com.accenture.mplt.comm.util.ApiUtil;
import com.accenture.mplt.comm.util.CommonUtil;
import com.accenture.mplt.model.RequestModel;
import com.accenture.mplt.model.ResponseModel;

public class SettingFragment extends Fragment implements OnClickListener{
	private final String MPLT_TAG = "SettingFragment";

	private String locked;
	private Button btnCalendarForStart, btnCalendarForEnd, btnTimeForStart, btnTimeForEnd;
	private ImageView btnForActivationOff, btnForActivationOn, saveImage;
	private TextView textUseStartMonth, textUseEndMonth;
	private int startYear, startMonth, startDay, startHour, startMinute;
	private int endYear, endMonth, endDay, endHour, endMinute;
	private int useStartMonth, useEndMonth;
	
	private CommApplication app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_setting, container, false);
		
		saveImage = (ImageView) getActivity().findViewById(R.id.actionBar_image_right);
		btnCalendarForStart = (Button) v.findViewById(R.id.setting_button_date_start);
		btnCalendarForEnd = (Button) v.findViewById(R.id.setting_button_date_end);
		btnTimeForStart = (Button) v.findViewById(R.id.setting_button_time_start);
		btnTimeForEnd = (Button) v.findViewById(R.id.setting_button_time_end);
		btnForActivationOff = (ImageView) v.findViewById(R.id.setting_image_off);
		btnForActivationOn = (ImageView) v.findViewById(R.id.setting_image_on);
		textUseStartMonth = (TextView) v.findViewById(R.id.setting_text_startMonth);
		textUseEndMonth = (TextView) v.findViewById(R.id.setting_text_endMonth);

		btnCalendarForStart.setOnClickListener(this);
		btnCalendarForEnd.setOnClickListener(this);
		btnTimeForStart.setOnClickListener(this);
		btnTimeForEnd.setOnClickListener(this);
		btnForActivationOff.setOnClickListener(this);
		btnForActivationOn.setOnClickListener(this);
		saveImage.setOnClickListener(this);
		
		// baseFragment 의 셋팅 정보 가져오기.
		app = (CommApplication) v.getContext().getApplicationContext();
		ResponseModel response = app.getSetting();

		/* Informations from API */
		try {
			String startRegDate = response.getStart_registration_date();
			String endRegDate = response.getEnd_registration_date();
			locked = String.valueOf(response.getLocked());

			startYear = CommonUtil.makeYYYYDate(startRegDate);
			startMonth = CommonUtil.makeMMDate(startRegDate);
			startDay = CommonUtil.makeDDDate(startRegDate);
			startHour = CommonUtil.makeHHDate(startRegDate);
			startMinute = CommonUtil.makeMIDate(startRegDate);

			endYear = CommonUtil.makeYYYYDate(endRegDate);
			endMonth = CommonUtil.makeMMDate(endRegDate);
			endDay = CommonUtil.makeDDDate(endRegDate);
			endHour = CommonUtil.makeHHDate(endRegDate);
			endMinute = CommonUtil.makeMIDate(endRegDate);
			
			String useStartDate = response.getStart_date();
			String useEndDate = response.getEnd_date();
			
			useStartMonth = CommonUtil.makeMMDate(useStartDate);
			useEndMonth = CommonUtil.makeMMDate(useEndDate);
		} catch (ParseException e) {
			Log.e(MPLT_TAG, e.getMessage());
		}

		initSetting();
		
		return v;
	}
	@Override
	public void onClick(View v) {
		if (v == btnCalendarForStart) {
			DatePickerDialog dpd = new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					startYear = year;
					startMonth = monthOfYear + 1;
					startDay = dayOfMonth;
					btnCalendarForStart.setText(makeStringDate(startYear, startMonth, startDay));
				}
			}, startYear, startMonth - 1, startDay);
			dpd.show();
		} else if (v == btnCalendarForEnd) {
			DatePickerDialog dpd = new DatePickerDialog(this.getActivity(), new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					endYear = year;
					endMonth = monthOfYear + 1;
					endDay = dayOfMonth;
					btnCalendarForEnd.setText(makeStringDate(endYear, endMonth, endDay));
				}
			}, endYear, endMonth - 1, endDay);
			dpd.show();
		} else if (v == btnTimeForStart) {
			TimePickerDialog tdp = new TimePickerDialog(this.getActivity(), new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					startHour = hourOfDay;
					startMinute = minute;
					btnTimeForStart.setText(makeStringTime(startHour, startMinute));
				}
			}, startHour, startMinute, true);
			tdp.show();
		} else if (v == btnTimeForEnd) {
			TimePickerDialog tdp = new TimePickerDialog(this.getActivity(), new TimePickerDialog.OnTimeSetListener() {
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					endHour = hourOfDay;
					endMinute = minute;
					btnTimeForEnd.setText(makeStringTime(endHour, endMinute));
				}
			}, endHour, endMinute, true);
			tdp.show();
		} else if (v == btnForActivationOn) {
			locked = "0";
			changeLockedState();
		} else if (v == btnForActivationOff) {
			locked = "1";
			changeLockedState();
		} else if (v == saveImage) {
			save();
		}
	}

	/**
	 * When 'Save' button clicked
	 */
	private void save() {
		String startRegistrationDate = makeStringDateTime(startYear, startMonth, startDay, startHour, startMinute);
		String endRegistrationDate = makeStringDateTime(endYear, endMonth, endDay, endHour, endMinute);

		RequestModel requestModel = new RequestModel();
		requestModel.setStart_registration_date(startRegistrationDate);
		requestModel.setEnd_registration_date(endRegistrationDate);
		requestModel.setLocked(locked);

		//update api 호출
		ResponseModel response = ApiUtil.callApi(StatusCodeEnum.UPDATE_SETTING, requestModel);
		String resultcode = response.getResult().getCode();
		String resultMsg = response.getResult().getMessage();
		
		
		//api 결과에 따라 얼럿창 띄우기
		CommonUtil.showAlertOK(this.getActivity(), resultMsg);
		ResponseModel getNewSettingInfo = ApiUtil.getSettingInfo(null);
		app.setSetting(getNewSettingInfo);
	}

	/**
	 * initiate the view
	 * 
	 * @param response
	 */
	private void initSetting() {
		textUseStartMonth.setText(String.valueOf(useStartMonth));
		textUseEndMonth.setText(String.valueOf(useEndMonth));
		btnCalendarForStart.setText(makeStringDate(startYear, startMonth, startDay));
		btnCalendarForEnd.setText(makeStringDate(endYear, endMonth, endDay));
		btnTimeForStart.setText(makeStringTime(startHour, startMinute));
		btnTimeForEnd.setText(makeStringTime(endHour, endMinute));
		changeLockedState();
		
	}
	
	/**
	 * change image resource following locked status
	 */
	private void changeLockedState() {
		/* unlocked, activated */
		if(locked.equals("0")) {
			btnForActivationOn.setImageResource(R.drawable.on_red);
			btnForActivationOff.setImageResource(R.drawable.off_gray);
		} else {
			btnForActivationOn.setImageResource(R.drawable.on_gray);
			btnForActivationOff.setImageResource(R.drawable.off_red);
		}
	}

	/**
	 * Transform date fields to 'yyyy-MM-ddThh:mm:00Z' format
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	private String makeStringDateTime(int year, int month, int day, int hour, int minute) {
		String strDateTime = String.format("%d-%02d-%02d %02d:%02d:00", year, month, day, hour, minute);
		return strDateTime;
	}
	
	private String makeStringDate(int year, int month, int day) {
		String strDate = String.format("%d-%02d-%02d", year, month, day);
		return strDate;
	}
	private String makeStringTime(int hour, int minute) {
		String strTime = String.format("%02d:%02d", hour, minute);
		return strTime;
	}

//	@Override
//	public void setAppTitle() {
//		appTitle = getString(R.string.setting_title);
//	}
}

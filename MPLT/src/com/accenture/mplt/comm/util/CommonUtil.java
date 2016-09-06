package com.accenture.mplt.comm.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.accenture.mplt.R;
import com.accenture.mplt.model.ResponseModel;

public class CommonUtil {
	private static final String NON_DATE = "99";
	/**
	 * String date Format : 2014-11-25T08:29:53Z
	 * String date to yyyy-MM-dd split and convert Date
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	private static Date parseDate(String date) throws ParseException {
		Date returnDate = null;
		if(date != null && !"".equals(date)) {
			String dateYmd = "";
			if(date.contains("T")) {
				dateYmd = date.substring(0, date.indexOf("T"));
			} else {
				dateYmd = date;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			returnDate = sdf.parse(dateYmd);
		}
		return returnDate;
	}
	/**
	 * String date Format : 2014-11-25T08:29:53Z
	 * String date to hh:mm:ss split and convert Date
	 * 2015.01.13 수정
	 * String date Format : 2014-11-25T08:29:53
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	private static Date parseTime(String date) throws ParseException {
		Date returnDate = null;
		if(date != null && !"".equals(date)) {
			
			String dateTime = date.substring(date.indexOf("T")+1);
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			returnDate = sdf.parse(dateTime);
		}
		return returnDate;
	}
	/**
	 * use parseDate , formatter
	 * @param formatter
	 * @param parseDate
	 * @return
	 */
	private static int formatDate(SimpleDateFormat formatter, Date parseDate) {
		String formatterString = "";
		
		if(parseDate != null) {
			formatterString = formatter.format(parseDate);
		} else {
			formatterString = NON_DATE;
		}
		
		return Integer.parseInt(formatterString);
	}
	
	private static String formatDateToString(SimpleDateFormat formatter, Date parseDate) {
		String formatterString = "";
		
		if(parseDate != null) {
			formatterString = formatter.format(parseDate);
		} else {
			formatterString = NON_DATE;
		}
		return formatterString;
	}
	
	// YYYY Date Format
	public static int makeYYYYDate(String date) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		
		return formatDate(formatter, parseDate(date));
	}
	
	// MM Date Format
	public static int makeMMDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		
		return formatDate(formatter, parseDate(date));
	}
	
	// dd Date Format
	public static int makeDDDate(String date) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		
		return formatDate(formatter, parseDate(date));
	}
	
	// hh Date Format ( 0~24 hour )
	public static int makeHHDate(String date) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("kk");
		
		return formatDate(formatter, parseTime(date));
	}
	
	// mm Date Format
	public static int makeMIDate(String date) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("mm");
		
		return formatDate(formatter, parseTime(date));
	}
	
	// ss Date Format
	public static int makeSSDate(String date) throws ParseException {
		
		SimpleDateFormat formatter = new SimpleDateFormat("ss");
		
		return formatDate(formatter, parseTime(date));
	}
	
	
	
	// Make Action Bar Title/ title + startDate( MM) ~ endDate(MM) 
	public static String makeTitle(String title , ResponseModel response) throws ParseException {
		title = title
				+ "(" + String.format("%02d", makeMMDate(response.getStart_date())) + ") ~ (" 
				+ String.format("%02d", makeMMDate(response.getEnd_date())) + ")";
		
		return title;
	}
	
	public static String makeDeadLine(ResponseModel response) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		String deadlineDate = formatDateToString(formatter, parseDate(response.getEnd_registration_date()));
		SimpleDateFormat formatterTime = new SimpleDateFormat("kk:mm");
		String datelineTime = formatDateToString(formatterTime, parseTime(response.getEnd_registration_date()));
		
		String deadline = deadlineDate + " "+datelineTime;
		return deadline;
		
	}
	
	public static void showAlertOK(Context context, String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setPositiveButton(context.getString(R.string.popup_button_ok), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();     //닫기
			}
		});
		alert.setMessage(message);
		alert.show();
	}
	
	/**
	  * 숫자에 천단위마다 콤마 넣기
	  * @param int
	  * @return String
	  * */
	 public static String toNumFormat(int num) {
	  DecimalFormat df = new DecimalFormat("#,###");
	  return df.format(num);
	 }
	
}

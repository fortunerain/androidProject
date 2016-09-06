package com.accenture.kdc.minhonoh;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class MuliTouchView extends View {
	private static final String LOG_TAG = "MuliTouch";
	
	private List<Coordinate> coordinateList = new ArrayList<Coordinate>();
	private int[] colorList = {Color.BLUE, Color.RED, Color.YELLOW, Color.BLACK, Color.GREEN, Color.GRAY, Color.CYAN};
	private Paint pnt = new Paint();
	
	public MuliTouchView(Context context) {
		super(context);
	}

    @Override
	protected void onDraw(Canvas canvas) {
    	Log.i(LOG_TAG, "onDraw");
    	pnt.setStyle(Style.FILL);
        // 채워진 동그라미 그리기
        if(!coordinateList.isEmpty()){
        	for (int i = 0; i < coordinateList.size(); i++) {
            	pnt.setColor(colorList[i%7]);
        		canvas.drawCircle(coordinateList.get(i).x, coordinateList.get(i).y, 90, pnt);
        	}
        }
       
		super.onDraw(canvas);
	}
    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int act = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = event.getActionIndex();
		int x = (int) event.getX(pointerIndex);
		int y = (int) event.getY(pointerIndex);
		
		switch (act) {
		case MotionEvent.ACTION_DOWN:
			Log.i(LOG_TAG, "ACTION_DOWN");
			Log.i(LOG_TAG, "pointerIndex : "+pointerIndex);
			
			//최초 터치 좌표 설정
		    ListIterator<Coordinate> listIterator = coordinateList.listIterator();

		    if (listIterator.hasNext()) {
		      listIterator.next();
		      listIterator.set(new Coordinate(x,y));
		    }else{
		    	coordinateList.add(new Coordinate(x, y));
		    }
			
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			//다른 터치 좌표 설정
			Log.i(LOG_TAG, "ACTION_POINTER_DOWN");
			//처음으로 다른 터치 일 경우 add 함
			if(coordinateList.size() < event.getPointerCount()){
				coordinateList.add(new Coordinate(x, y));
			}else{
				coordinateList.set(pointerIndex, new Coordinate(x, y));
			}
			break;
		case MotionEvent.ACTION_UP:
			//모든 터치를 up 했을 경우 clear함.
			coordinateList.clear();
			Log.i(LOG_TAG, "ACTION_UP");
			break;
		case MotionEvent.ACTION_MOVE:
			//이동할때의 터치 좌표 설정
			for (int i = 0; i < event.getPointerCount(); i++) {
				int x2 = (int) event.getX(i);
				int y2 = (int) event.getY(i);
				coordinateList.set(i, new Coordinate(x2, y2));
			}
			Log.i(LOG_TAG, "ACTION_MOVE");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			//다른 터치를 up 했을 경우는 해당 터치만 remove함.
			coordinateList.remove(pointerIndex);
			Log.i(LOG_TAG, "ACTION_POINTER_UP");
			break;
		}

		//다시 그린다.
		invalidate();
		
		return true;
	}
	
	
	private static class Coordinate {
	    int x;
	    int y;

	    Coordinate(int x, int y) {
	      this.x = x;
	      this.y = y;
	    }
	}

}

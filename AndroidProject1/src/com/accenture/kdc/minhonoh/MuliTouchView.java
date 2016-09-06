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
        // ä���� ���׶�� �׸���
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
			
			//���� ��ġ ��ǥ ����
		    ListIterator<Coordinate> listIterator = coordinateList.listIterator();

		    if (listIterator.hasNext()) {
		      listIterator.next();
		      listIterator.set(new Coordinate(x,y));
		    }else{
		    	coordinateList.add(new Coordinate(x, y));
		    }
			
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			//�ٸ� ��ġ ��ǥ ����
			Log.i(LOG_TAG, "ACTION_POINTER_DOWN");
			//ó������ �ٸ� ��ġ �� ��� add ��
			if(coordinateList.size() < event.getPointerCount()){
				coordinateList.add(new Coordinate(x, y));
			}else{
				coordinateList.set(pointerIndex, new Coordinate(x, y));
			}
			break;
		case MotionEvent.ACTION_UP:
			//��� ��ġ�� up ���� ��� clear��.
			coordinateList.clear();
			Log.i(LOG_TAG, "ACTION_UP");
			break;
		case MotionEvent.ACTION_MOVE:
			//�̵��Ҷ��� ��ġ ��ǥ ����
			for (int i = 0; i < event.getPointerCount(); i++) {
				int x2 = (int) event.getX(i);
				int y2 = (int) event.getY(i);
				coordinateList.set(i, new Coordinate(x2, y2));
			}
			Log.i(LOG_TAG, "ACTION_MOVE");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			//�ٸ� ��ġ�� up ���� ���� �ش� ��ġ�� remove��.
			coordinateList.remove(pointerIndex);
			Log.i(LOG_TAG, "ACTION_POINTER_UP");
			break;
		}

		//�ٽ� �׸���.
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

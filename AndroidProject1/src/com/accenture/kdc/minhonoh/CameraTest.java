package com.accenture.kdc.minhonoh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

public class CameraTest extends Activity implements SurfaceHolder.Callback,
		AutoFocusCallback, PictureCallback {
	private SurfaceHolder mSh;
	private Camera mCamera = null;
	private int preViewWidth = 0;
	private int preViewHeight = 0;
	private static final String LOG_TAG = "camera Test";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		SurfaceView sv = (SurfaceView) findViewById(R.id.svPreView);
		mSh = sv.getHolder();
		mSh.addCallback(this);
		mSh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	// 서피스 생성 이벤트 함수
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			// 카메라 연동
			mCamera = Camera.open();
			// 카메라에 스크린 지정
			mCamera.setPreviewDisplay(mSh);
			mCamera.setDisplayOrientation(0);
		} catch (Exception e) {
			if(mCamera != null) {
				mCamera.release();
				mCamera = null;
			}else {
				// 카메라 연동
				mCamera = Camera.open();
				// 카메라에 스크린 지정
				try {
					mCamera.setPreviewDisplay(mSh);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mCamera.setDisplayOrientation(0);
			}
			return;
		}
	}

	// 서피스 변경 이벤트 함수
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// 프리뷰 크기 목록을 구한다
		Camera.Parameters params = mCamera.getParameters();
		List<Size> arSize = params.getSupportedPreviewSizes();

		// 서피스 크기와 가장 유사한 프리뷰 크기를 찾는다
		Size match = null;
		for (Size sPrev : arSize) {
			if (match == null) {
				match = sPrev;
				continue;
			}

			if (Math.abs(sPrev.height - height) < Math.abs(match.height
					- height))
				match = sPrev;
		}

		// 프리뷰 크기를 지정
		preViewWidth = match.width;
		preViewHeight = match.height;
		
		params.setPreviewSize(preViewWidth, preViewHeight);
		mCamera.setParameters(params);
		// 프리뷰 시작
		mCamera.startPreview();
	}

	// 서피스 삭제 이벤트 함수
	public void surfaceDestroyed(SurfaceHolder holder) {
		// 카메라 삭제
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}

	// 버튼 이벤트 함수
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnShutter:
			// 오토 포커스 시작
			mCamera.autoFocus(this);
			break;
		}
	}

	// 오토 포커스 완료 이벤트 함수
	public void onAutoFocus(boolean success, Camera camera) {
		// 사진 촬영
		mCamera.takePicture(null, null, this);
	}

	// 사진 촬영 완료 이벤트 함수
	public void onPictureTaken(byte[] data, Camera camera) {
		// 이미지 파일 경로 생성
		String sdRootPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String filePath = sdRootPath + "/camerashot.jpg";

		// 이미지를 파일로 저장
		File file = new File(filePath);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			Log.d("tag", "File Write Error");
			return;
		}

		// 이미지를 파일을 화면에 표시
		Bitmap bmp = BitmapFactory.decodeFile(filePath);
		Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, preViewWidth, preViewHeight, true);
		ImageView imageCamera = (ImageView) findViewById(R.id.imageCamera);
		imageCamera.setImageBitmap(resizedbitmap);
		
		// 프리뷰 시작
		mCamera.startPreview();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		surfaceDestroyed(null);
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		finish();
	}
}
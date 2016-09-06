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

	// ���ǽ� ���� �̺�Ʈ �Լ�
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			// ī�޶� ����
			mCamera = Camera.open();
			// ī�޶� ��ũ�� ����
			mCamera.setPreviewDisplay(mSh);
			mCamera.setDisplayOrientation(0);
		} catch (Exception e) {
			if(mCamera != null) {
				mCamera.release();
				mCamera = null;
			}else {
				// ī�޶� ����
				mCamera = Camera.open();
				// ī�޶� ��ũ�� ����
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

	// ���ǽ� ���� �̺�Ʈ �Լ�
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// ������ ũ�� ����� ���Ѵ�
		Camera.Parameters params = mCamera.getParameters();
		List<Size> arSize = params.getSupportedPreviewSizes();

		// ���ǽ� ũ��� ���� ������ ������ ũ�⸦ ã�´�
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

		// ������ ũ�⸦ ����
		preViewWidth = match.width;
		preViewHeight = match.height;
		
		params.setPreviewSize(preViewWidth, preViewHeight);
		mCamera.setParameters(params);
		// ������ ����
		mCamera.startPreview();
	}

	// ���ǽ� ���� �̺�Ʈ �Լ�
	public void surfaceDestroyed(SurfaceHolder holder) {
		// ī�޶� ����
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}

	// ��ư �̺�Ʈ �Լ�
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnShutter:
			// ���� ��Ŀ�� ����
			mCamera.autoFocus(this);
			break;
		}
	}

	// ���� ��Ŀ�� �Ϸ� �̺�Ʈ �Լ�
	public void onAutoFocus(boolean success, Camera camera) {
		// ���� �Կ�
		mCamera.takePicture(null, null, this);
	}

	// ���� �Կ� �Ϸ� �̺�Ʈ �Լ�
	public void onPictureTaken(byte[] data, Camera camera) {
		// �̹��� ���� ��� ����
		String sdRootPath = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String filePath = sdRootPath + "/camerashot.jpg";

		// �̹����� ���Ϸ� ����
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

		// �̹����� ������ ȭ�鿡 ǥ��
		Bitmap bmp = BitmapFactory.decodeFile(filePath);
		Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, preViewWidth, preViewHeight, true);
		ImageView imageCamera = (ImageView) findViewById(R.id.imageCamera);
		imageCamera.setImageBitmap(resizedbitmap);
		
		// ������ ����
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
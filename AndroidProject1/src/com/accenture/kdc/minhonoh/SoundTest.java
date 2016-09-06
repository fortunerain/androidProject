package com.accenture.kdc.minhonoh;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.TextView;
import android.widget.Toast;

public class SoundTest extends Activity implements MediaPlayerControl {
	private SoundService soundService;
	private MediaController mediaController;
	private boolean mIsBound;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sound);
		mediaController = new MediaController(this);
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView((MediaController) findViewById(R.id.mediaController1));

		((TextView) findViewById(R.id.audio_textView1)).setText("음악파일 경로");
		doBindService();
//		soundService.getMediaPlayer().setOnPreparedListener(this);
		
		Intent i = new Intent(this, SoundService.class);
		startService(i);
	}
	
    
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
        	soundService = ((SoundService.LocalBinder)service).getService();
            
            Toast.makeText(SoundTest.this, "onServiceConnected",
                    Toast.LENGTH_SHORT).show();
        }

        public void onServiceDisconnected(ComponentName className) {
        	soundService = null;
            Toast.makeText(SoundTest.this, "onServiceDisconnected",
                    Toast.LENGTH_SHORT).show();
        }
    };
    
    void doBindService() {
        bindService(new Intent(SoundTest.this, 
        		SoundService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
    
    void doUnbindService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }
    

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mediaController.show(0);
		return super.onTouchEvent(event);
	}

	@Override
	public void start() {
		// 음악 재생시 service로 백그라운드에서 플레이시킨다.
//		Intent i = new Intent(this, SoundService.class);
//		startService(i);
		soundService.getMediaPlayer().start();
		((TextView) findViewById(R.id.audio_textView1)).setText(soundService.getAudioPath());
	}

	@Override
	public void pause() {
		soundService.getMediaPlayer().pause();
	}

	@Override
	public int getDuration() {
		return soundService.getMediaPlayer().getDuration();
		//return 0;
	}

	@Override
	public int getCurrentPosition() {
		return soundService.getMediaPlayer().getCurrentPosition();
		//return 0;
	}

	@Override
	public void seekTo(int pos) {
		soundService.getMediaPlayer().seekTo(pos);
	}

	@Override
	public boolean isPlaying() {
		return soundService.getMediaPlayer().isPlaying();
		//return false;
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

//	@Override
//	public void onPrepared(MediaPlayer mp) {
//		mediaController.show(0);
//		// mediaController = new MediaController(this);
//		// mediaController.setMediaPlayer(this);
//		// mediaController.setAnchorView((MediaController)
//		// findViewById(R.id.mediaController1));
//
//	}
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(getApplicationContext(), SubActivity.class);
		intent.putExtra("menuNum", 2);
		startActivity(intent);
		super.onBackPressed();
	}
}

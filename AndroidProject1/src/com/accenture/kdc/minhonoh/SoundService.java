package com.accenture.kdc.minhonoh;

import java.io.File;
import java.io.FilenameFilter;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class SoundService extends Service {
	private MediaPlayer mediaPlayer;
	private String audioFile;
	private String dirName;
	private String audioPath;
	private String fileList[] = null;
	private final IBinder mBinder = new LocalBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public class LocalBinder extends Binder {
    	SoundService getService() {
            return SoundService.this;
        }
    }

	@Override
	public void onCreate() {
		super.onCreate();
		// 미디어플레이어를 초기화
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		// mediaPlayer.setOnPreparedListener(this);
		// 곡 재생이 완료하면 서비스를 종료시킨다
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer arg0) {
				stopSelf(); // 서비스 종료
			}
		});

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		String[] fileList = getMediaFilePath();

		// audioFile = this.getIntent().getStringExtra("audioPath");
		audioFile = fileList[0].toString();
		audioPath = dirName + audioFile;
		// ((TextView)findViewById(R.id.audio_textView1)).setText(audioPath);
		File mp3file = new File(audioPath);
		if (mp3file.exists()) {
			try {
				// 미디어플레이어 재생
				mediaPlayer.setDataSource(audioPath);
				mediaPlayer.prepare();
				//mediaPlayer.start();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), e.toString(),
						Toast.LENGTH_LONG).show();
			}
		}

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		super.onDestroy();
	}

	// mp3
	private String[] getMediaFilePath() {

		String sdcardState = android.os.Environment.getExternalStorageState();
		if (sdcardState.contentEquals(android.os.Environment.MEDIA_MOUNTED)) {
			dirName = android.os.Environment.getExternalStorageDirectory()
					+ "/mp3/";// + File.separator;
			File dir = new File(dirName);
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File file, String name) {
					return (name.endsWith(".mp3") || name.endsWith(".jpg"));
				}
			};
			fileList = dir.list(filter);
			return fileList;
		}
		return null;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
	
	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

}

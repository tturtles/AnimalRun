package com.example.animalrun.framework.impl;

import java.io.IOException;

import com.example.animalrun.framework.Audio;
import com.example.animalrun.framework.Music;
import com.example.animalrun.framework.Sound;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;


public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();									//・ｽQ・ｽ[・ｽ・ｽ・ｽA・ｽN・ｽe・ｽB・ｽr・ｽe・ｽB・ｽ・ｽn・ｽ・ｽ
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}
	
	public Music newMusic(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			return new AndroidMusic(assetDescriptor);
		}catch (IOException e) {
			throw new RuntimeException("Couldn't load music '" + filename + "'");
		}
	}
	public Sound newSound(String filename) {
		try {
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		}catch (IOException e) {
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		}
	}
}

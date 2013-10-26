package com.vogabe.randomMovie.controller;

import java.io.IOException;
import java.util.prefs.Preferences;

public class MediaPlayerController {
		
	private String vlcPath;
	private SettingsController settingsController;
	
	public MediaPlayerController(SettingsController settingsController){
		this.settingsController = settingsController;
		vlcPath = settingsController.getStoredMediaPlayerPath();			
	}
	
	private String getVlcPath() {
		return vlcPath;
	}

	public void setVlcPath(String vlcPath) {
		this.vlcPath = vlcPath;
		settingsController.storeMediaPlayerPath(vlcPath);
	}
	
	public void play(String file){
		ProcessBuilder pb = new ProcessBuilder(getVlcPath(), file);
		try {
			Process start = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

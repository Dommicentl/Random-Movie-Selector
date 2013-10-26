package com.vogabe.randomMovie.controller;

import java.util.prefs.Preferences;

public class SettingsController {
	
	private Preferences prefs = Preferences.userNodeForPackage(com.vogabe.randomMovie.controller.SettingsController.class);
	private final String vlcPref = "VLC";
	private final String lastOpened = "LAST_OPENED";
	
	public SettingsController(){
		
	}
	
	public String getStoredMediaPlayerPath(){
		return prefs.get(vlcPref, null);
	}
	
	public void storeMediaPlayerPath(String mediaPlayerPath){
		prefs.put(vlcPref, mediaPlayerPath);
	}

	public void storeLastOpened(String lastOpened){
		prefs.put(this.lastOpened, lastOpened);
	}
	
	public String getLastOpened(){
		return prefs.get(lastOpened, null);
	}
}

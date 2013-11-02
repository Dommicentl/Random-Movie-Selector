package com.vogabe.randomMovie.controller;

import java.util.prefs.Preferences;

public class SettingsController {
	
	private Preferences prefs = Preferences.userNodeForPackage(com.vogabe.randomMovie.controller.SettingsController.class);
	private final String vlcPref = "VLC";
	private String vlcPrefResult;
	private final String lastOpened = "LAST_OPENED";
	private String lastOpenedResult;
	private final String onlyOnce = "ONLY_ONCE";
	private boolean onlyOnceResult;
	
	public SettingsController(){
		vlcPrefResult = prefs.get(vlcPref, null);
		lastOpenedResult = prefs.get(lastOpened, null);
		onlyOnceResult = prefs.getBoolean(onlyOnce, false);
	}
	
	public String getStoredMediaPlayerPath(){
		return vlcPrefResult;
	}
	
	public void storeMediaPlayerPath(String mediaPlayerPath){
		prefs.put(vlcPref, mediaPlayerPath);
		vlcPrefResult = mediaPlayerPath;
	}

	public void storeLastOpened(String lastOpened){
		prefs.put(this.lastOpened, lastOpened);
		lastOpenedResult = lastOpened;
	}
	
	public String getLastOpened(){
		return lastOpenedResult;
	}
	
	public boolean getStoredOnlyOnce(){
		return onlyOnceResult;
	}
	
	public void storeOnlyOnce(boolean onlyOnce){
		prefs.putBoolean(this.onlyOnce, onlyOnce);
		onlyOnceResult = onlyOnce;
	}
}

package com.vogabe.randomMovie;

import java.util.prefs.Preferences;

public class Settings {
	private Preferences prefs = Preferences.userNodeForPackage(com.vogabe.randomMovie.Settings.class);
	private final String VlcPath = "VLC";
	private final String currentFolder = "CURRENT_FOLDER";

	public String getCurrentFolder() {
		return prefs.get(currentFolder, "");
	}

	public void setCurrentFolder(String currentFolder) {
		prefs.put(this.currentFolder, currentFolder);
	}

	public String getVlcPath() {
		return prefs.get(VlcPath, "");
	}

	public void setVlcPath(String vlcPath) {
		prefs.put(VlcPath, vlcPath);
	}
}

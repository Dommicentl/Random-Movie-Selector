package com.vogabe.randomMovie.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.prefs.Preferences;

import org.apache.log4j.Logger;

import com.vogabe.randomMovie.model.ListEntry;

public class ListController {

	private Preferences prefs = Preferences
			.userNodeForPackage(com.vogabe.randomMovie.controller.ListController.class);
	private Logger logger = Logger.getLogger("randomMovie");
	private List<ListEntry> visualSearchList;
	private List<String> actualSearchList;
	private final String[] movieTypes = { ".avi", ".mp4", ".wmv", ".mkv" };
	private SettingsController settingsController;

	public ListController(SettingsController settingsController) {
		visualSearchList = new ArrayList<ListEntry>();
		actualSearchList = new ArrayList<String>();
		this.settingsController = settingsController;
	}

	public void addEntryToList(ListEntry entry) {
		visualSearchList.add(entry);
		addToActualList(entry.getFullPath());
	}

	public void removeEntryFromList(ListEntry entry) {
		visualSearchList.remove(entry);
		removeFromActualList(entry.getFullPath());
	}

	private void addToActualList(String pathEntry) {
		File entryDir = new File(pathEntry);
		if (entryDir.isFile() && isValidFile(entryDir))
			actualSearchList.add(pathEntry);
		else if (entryDir.isDirectory()) {
			File[] children = entryDir.listFiles();
			for (File current : children) {
				addToActualList(current.getAbsolutePath());
			}
		}
	}

	private void removeFromActualList(String pathEntry) {
		File entryDir = new File(pathEntry);
		if (entryDir.isFile() && isValidFile(entryDir))
			actualSearchList.remove(pathEntry);
		else if (entryDir.isDirectory()) {
			File[] children = entryDir.listFiles();
			for (File current : children) {
				removeFromActualList(current.getAbsolutePath());
			}
		}
	}

	public void updateActualList() {
		actualSearchList = new ArrayList<String>();
		for (ListEntry current : visualSearchList) {
			addToActualList(current.getFullPath());
		}
	}

	private boolean isValidFile(File entryDir) {
		for (String suffix : movieTypes) {
			if (entryDir.getName().toLowerCase().endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

	public String getRandomFile() {
		if(actualSearchList.size() == 0)
			return null;
		Random random = new Random();
		int index = random.nextInt(actualSearchList.size());
		String result = actualSearchList.get(index);
		if(settingsController.getStoredOnlyOnce())
			actualSearchList.remove(index);
		return result;
	}
}

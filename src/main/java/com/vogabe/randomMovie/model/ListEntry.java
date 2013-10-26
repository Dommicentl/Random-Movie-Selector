package com.vogabe.randomMovie.model;

public class ListEntry {
	private String fullPath;
	
	public ListEntry(String path){
		fullPath = path;
	}
	
	public String getFullPath(){
		return fullPath;
	}
	
	@Override
	public String toString(){
		return getFullPath();
	}

}

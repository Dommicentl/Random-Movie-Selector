package com.vogabe.randomMovie;

import java.io.IOException;

public class VlcExecuter {
	Settings settings;
	
	public VlcExecuter(Settings settings){
		this.settings = settings;
	}
	
	public void play(String file){
		ProcessBuilder pb = new ProcessBuilder(settings.getVlcPath(), file);
		try {
			Process start = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.vogabe.randomMovie;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class RandomFileChooser {

	private Logger logger = Logger.getLogger("randomMovie");
	private ArrayList<File> movies;
	private File rootDir;
	private final String[] movieTypes = {".avi",".mp4",".wmv",".mkv"};
	
	public RandomFileChooser(String path){
		PropertyConfigurator.configure("log4j.conf");
		rootDir = new File(path);
		movies = getMovieFiles();
	}
	
	public ArrayList<File> getMovieFiles(){
		File[] files = rootDir.listFiles();
		ArrayList<File> filePaths = new ArrayList<File>();
		addRecursive(rootDir, filePaths);
		return filePaths;
	}
	
	public String getRandomMovieFile(){
		int length = movies.size();
		Random random = new Random();
		int chosen = random.nextInt(length);
		logger.info("Chosen movie: "+movies.get(chosen).getName());
		return movies.get(chosen).getAbsolutePath();
	}
	
	private void addRecursive(File dir, ArrayList<File> list){
		File[] files = dir.listFiles();
		if(files == null){
			logger.warn("Given path "+dir.getAbsolutePath()+" isn't a folder -> ignoring");
			return;
		}
		for(File file: files){
			if(file.isDirectory() && isValidDirectory(file)){
				addRecursive(file, list);
				logger.info("Traversing directory "+file);
			}
			else if(file.isFile() && isValidFile(file)){
				list.add(file);
				logger.info("Added file "+file.getAbsolutePath());
			}
		}
	}

	private boolean isValidDirectory(File file) {
		if(file.getAbsolutePath().contains("$")){
			return false;
		}
		return true;
	}

	private boolean isValidFile(File file) {
		for(String suffix : movieTypes){
			if(file.getName().toLowerCase().endsWith(suffix)){
				return true;
			}
		}
		return false;
	}
}

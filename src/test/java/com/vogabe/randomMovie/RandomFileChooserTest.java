package com.vogabe.randomMovie;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

public class RandomFileChooserTest {

	@Test
	public void GeneratorTest() {
		RandomFileChooser chooser = new RandomFileChooser("/media/Data/Download");
		ArrayList<File> movies = chooser.getMovieFiles();
	}
	
	@Test
	public void RandomTest(){
		RandomFileChooser chooser = new RandomFileChooser("/media/Data/Download");
		String movies = chooser.getRandomMovieFile();
	}

}

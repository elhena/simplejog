/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.util.logger.Log;

public class CompetitionWriter {

	// Attributes
	private FileOutputStream output;
	private ObjectOutputStream stream;
	
	
	// Constructor
	public CompetitionWriter(String path) {
		try {
			output = new FileOutputStream(new File(path));
			stream = new ObjectOutputStream(output);
		} 
		
		catch (FileNotFoundException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		} 
		
		catch (IOException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
	
	// Method : Write a competition
	public void write(Competition competition) {
		try {
			stream.writeObject(competition);
		} 
		
		catch (IOException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
	
	// Method : Close streams
	public void close() {
		try {
			stream.close();
			output.close();
		} 
		
		catch (IOException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
}

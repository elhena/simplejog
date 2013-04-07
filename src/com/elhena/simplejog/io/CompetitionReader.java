/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.util.logger.Log;

public class CompetitionReader {

	// Attributes
	private FileInputStream input;
	private ObjectInputStream stream;
	
	
	// Constructor
	public CompetitionReader(String path) {
		try {
			input = new FileInputStream(new File(path));
			stream = new ObjectInputStream(input);
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
	
	// Method : Read a competition
	public Competition read() {
		try {
			return ((Competition) stream.readObject());
		} 
		
		catch (IOException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
			
			return null;
		} 
		
		catch (ClassNotFoundException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
			
			return null;
		}
	}
	
	// Method : Close stream
	public void close() {
		try {
			stream.close();
			input.close();
		} 
		
		catch (IOException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
}

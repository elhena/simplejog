/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.util.logger.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.elhena.simplejog.util.logger.Log;
import com.elhena.simplejog.util.logger.model.LogRecord;

public class LogWriter {

	// Constants
	private static final String LOG_DIRECTORY = "logs";
	
	
	// Attributes
	private FileOutputStream outputStream;
	private PrintWriter writer;
	
	
	// Constructor
	public LogWriter(String fileName) {
		try {
			outputStream = new FileOutputStream(new File(LOG_DIRECTORY + "/" + fileName), true);
			writer = new PrintWriter(outputStream);
		} 
		
		catch (FileNotFoundException e) {
			System.err.println("[Logger] Error: Log file doesn't exist");
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
	
	// Method : Write a log
	public void write(LogRecord log) {
		writer.println(log);
	}
	
	// Method : Close file
	public void close() {
		writer.flush();
		writer.close();
		
		try {
			outputStream.close();
		} 
		
		catch (IOException e) {
			System.err.println("[Logger] Error I/O: " + e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
}

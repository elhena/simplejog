/**
 * @author Fabien Vanden Bulck
 */

package com.elhena.simplejog.util.logger.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogRecord {

	// Attributes
	private Date date;
	private LogType type;
	private String message;
	
	
	// Constructor
	public LogRecord(LogType type, String message) {
		
		this.date = new Date();
		this.type = type;
		this.message = message;
	}
	
	// Methods : Encapsulation
	// Getters
	public Date getDate() {
		return date;
	}
	
	public String getDateAsString() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
	}
	
	public LogType getType() {
		return type;
	}
	
	public String getTypeAsString() {
		switch(type) {
			case SYSTEM: return "System";
			case INFO: return "Information";
			case EXCEPTION: return "Exception";
			case ERROR: return "Error";
			default: return "Unknown";
		}
	}
	
	public String getMessage() {
		return message;
	}
	
	// Method : Get default string value
	public String toString() {
		return "[" + getDateAsString() + "] " + getTypeAsString() + ": " + message;
	}
}

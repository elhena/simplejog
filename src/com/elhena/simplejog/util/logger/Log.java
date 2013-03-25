/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.util.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.util.logger.io.LogWriter;
import com.elhena.simplejog.util.logger.model.LogRecord;
import com.elhena.simplejog.util.logger.model.LogType;

public class Log {

	// Attributes
	public static boolean debug;
	
	
	// Constructor
	public Log() {}
	
	// Method : Process a log
	private static void process(LogRecord log) {
		// Save log in a file
		LogWriter writer = new LogWriter(Application.NAME + "_" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()) + ".log");
		writer.write(log);
		writer.close();
		
		// Print log (if debug is enabled)
		if (debug) {
			if (log.getType() == LogType.ERROR)
				System.err.println(log);
			else
				System.out.println(log);
		}
	}
	
	// Method : Process a system log
	public static void s(String message) {
		process(new LogRecord(LogType.SYSTEM, message));
	}
	
	// Method : Process an information log
	public static void i(String message) {
		process(new LogRecord(LogType.INFO, message));
	}
	
	// Method : Process an exception log
	public static void x(String message) {
		process(new LogRecord(LogType.EXCEPTION, message));
	}
	
	// Method : Processs an error log
	public static void e(String message) {
		process(new LogRecord(LogType.ERROR, message));
	}
}

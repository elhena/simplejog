/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app.model;

import com.elhena.simplejog.util.logger.Log;

public class ApplicationArgumentsHandler {

	// Method : Check arguments
	public static void manage(String[] args) {
		
		for (String arg : args) {
			if (arg.equals("-debug"))
				debugHandler(); break;
		}
	}
	
	private static void debugHandler()
	{
		System.out.println("*** DEBUG MODE ***\n");
		Log.debug = true;
	}
}

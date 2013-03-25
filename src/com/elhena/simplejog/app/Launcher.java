/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.app.model.ApplicationArgumentsHandler;
import com.elhena.simplejog.util.logger.Log;

public class Launcher {

	// Main app
	public static void main(String[] args) {
		
		// Arguments checker
		ApplicationArgumentsHandler.manage(args);
		
		// Application infos
		Application.showCredits();
		
		Log.s("Application is started...");
		Log.x("fuck");
		Log.e("Big error");
	}
}

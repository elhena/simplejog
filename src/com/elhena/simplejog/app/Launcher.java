/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.app.model.ApplicationArgumentsHandler;
import com.elhena.simplejog.app.splash.Splash;
import com.elhena.simplejog.util.loader.ResourcesLoader;
import com.elhena.simplejog.util.logger.Log;

public class Launcher {

	// Main app
	public static void main(String[] args) {
		
		// Arguments checker
		ApplicationArgumentsHandler.manage(args);
		
		// Application infos
		Application.showCredits();
				
		// Resources loader
		ResourcesLoader.loadResources();
		Log.s("Resources loaded");
		
		// Splash
		Splash splash = new Splash();
		
		Log.s("Application is started");
	}
}

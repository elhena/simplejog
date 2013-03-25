/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.app.model.ApplicationArgumentsHandler;

public class Launcher {

	// Main app
	public static void main(String[] args) {
		
		// Arguments checker
		ApplicationArgumentsHandler.manage(args);
		
		// Application infos
		Application.showCredits();
	}
}

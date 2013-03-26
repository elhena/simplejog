/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.app.splash.Splash;

public class FrontController {

	// Attributes
	private Splash splash;
	private HomeController home;
	
	// Constructor
	public FrontController() {
		home = new HomeController(this);
	}
	
	// Method : Open splash
	public void openSplash() {
		splash = new Splash(this);
	}
	
	// Method : Close splash
	public void closeSplash() {
		if (splash != null)
			splash.dispose();
	}
	
	// Method : Open Home frame
	public void openHomeFrame() {
		home.openFrame();
	}
	
	// Method : Close
	public void closeHomeFrame() {
		home.closeFrame();
	}
 }

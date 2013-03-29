/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.app.splash.Splash;

public class FrontController {

	// Attributes
	private Splash splash;
	private HomeController home;
	private NewCompetitionController newCompetition;
	
	// Constructor
	public FrontController() {
		home = new HomeController(this);
		newCompetition = new NewCompetitionController(this);
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
	
	// Method : Open home frame
	public void openHomeFrame() {
		home.openFrame();
	}
	
	// Method : Close home frame
	public void closeHomeFrame() {
		home.closeFrame();
	}
	
	// Method : Open new competition frame
	public void openNewCompetitionFrame() {
		newCompetition.openFrame();
	}
	
	// Method : Close new competition frame
	public void closeNewCompetitionFrame() {
		newCompetition.closeFrame();
	}
 }
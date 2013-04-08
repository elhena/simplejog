/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.app.splash.Splash;
import com.elhena.simplejog.io.CompetitionReader;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.util.logger.Log;
import com.elhena.simplejog.view.CompetitionFrame;

public class FrontController {

	// Attributes
	private Splash splash;
	private HomeController home;
	private NewCompetitionController newCompetition;
	private CompetitionController competition;
	
	// Constructor
	public FrontController() {
		home = new HomeController(this);
		newCompetition = new NewCompetitionController(this);
		competition = new CompetitionController(this);
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
	
	// Method : Open competition frame
	public void openCompetitionFrame(Competition competition) {
		this.competition.setCompetition(competition);
		this.competition.openFrame();
	}
	
	// Method : Close competition frame
	public void closeCompetitionFrame() {
		this.competition.closeFrame();
	}
	
	// Method : Open a competition
	public void openCompetition(String path) {
		CompetitionReader reader = new CompetitionReader(path);
		openCompetitionFrame(reader.read());
		reader.close();
		CompetitionFrame.savePath = path;
		Log.i("New competition opened");
	}
 }
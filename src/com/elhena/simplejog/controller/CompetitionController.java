/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.view.CompetitionFrame;

public class CompetitionController extends Controller {
	
	// Attributes
	private CompetitionFrame frame;
	private Competition competition;
	
	// Constructor
	public CompetitionController(FrontController controller) {
		super(controller);
	}
	
	// Method : Set competition
	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	// Method : Open competition frame
	public void openFrame() {
		if (frame == null)
			frame = new CompetitionFrame(this);
		
		frame.setVisible(true);
	}
	
	// Method : Close competition frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
	
	// Method : Get competition
	public Competition getCompetition() {
		return competition;
	}
}

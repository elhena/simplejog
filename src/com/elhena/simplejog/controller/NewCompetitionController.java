/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.view.NewCompetitionFrame;

public class NewCompetitionController extends Controller {

	// Attributes
	private NewCompetitionFrame frame;
	
	
	// Constructor
	public NewCompetitionController(FrontController controller) {
		super(controller);
	}

	// Method : Open new competition frame
	public void openFrame() {
		if (frame == null)
			frame = new NewCompetitionFrame(this);
		
		frame.setVisible(true);
	}
	
	// Method : Close new competition frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
}

/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.BackController;
import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.view.ViewRaceFrame;

public class ViewRaceController extends BackController {

	// Attributes
	private ViewRaceFrame frame;
	private Race race;
	
	
	// Constructor
	public ViewRaceController(Controller parentController) {
		super(parentController);
	}
	
	// Methods : Encapsulation
	// Getters
	public Race getRace() {
		return race;
	}
	
	// Method : Open frame
	public void openFrame(Race race) {
		this.race = race;
		
		frame = new ViewRaceFrame(this);
		frame.setVisible(true);
	}
	
	// Method : Close frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
}

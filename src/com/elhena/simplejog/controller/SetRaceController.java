/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.BackController;
import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.view.SetRaceFrame;

public class SetRaceController extends BackController {
	
	// Attributes
	private SetRaceFrame frame;
	private Race race;
	

	// Constructor
	public SetRaceController(Controller controller) {
		super(controller);
	}
	
	// Methods : Encapsulation
	// Getters
	public Race getRace() {
		return race;
	}
	
	// Setters
	public void setRace(Race newRace) {
		race = newRace;
	}
	
	// Method : Open frame
	public void openFrame(Race race) {
		this.race = race;
		
		frame = new SetRaceFrame(this);
		frame.setVisible(true);
	}
	
	// Method : Close frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
}
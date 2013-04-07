/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.BackController;
import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.view.AboutFrame;

public class AboutController extends BackController {
	
	// Attributes
	private AboutFrame frame;

	
	// Constructor
	public AboutController(Controller parentController) {
		super(parentController);
	}
	
	// Method : Open frame
	public void openFrame() {
		frame = new AboutFrame(this);
		frame.setVisible(true);
	}
	
	// Method : Close frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
}

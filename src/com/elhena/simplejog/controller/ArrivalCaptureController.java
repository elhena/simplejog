/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.BackController;
import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.view.ArrivalCaptureFrame;

public class ArrivalCaptureController extends BackController {

	// Attributes
	ArrivalCaptureFrame frame;
	
	
	// Constructor
	public ArrivalCaptureController(Controller controller) {
		super(controller);
	}
	
	// Method : Open frame
	public void openFrame() {
		frame = new ArrivalCaptureFrame(this);
		frame.setVisible(true);
	}
	
	// Method : Close frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
}

/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller;

import com.elhena.simplejog.controller.model.Controller;
import com.elhena.simplejog.view.HomeFrame;

public class HomeController extends Controller {

	// Attributes
	private HomeFrame frame;
	
	// Constructor
	public HomeController(FrontController controller) {
		super(controller);
	}
	
	// Method : Open home frame
	public void openFrame() {
		frame = new HomeFrame();
		frame.setVisible(true);
	}
	
	// Method : Close home frame
	public void closeFrame() {
		if (frame != null)
			frame.dispose();
	}
}

/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller.model;

abstract public class BackController {

	// Attributes
	private Controller parentController;
	
	
	// Constructor
	public BackController(Controller parentController) {
		this.parentController = parentController;
	}
	
	// Method : Get parent controller
	public Controller getParentController() {
		return parentController;
	}
}

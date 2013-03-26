/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.controller.model;

import com.elhena.simplejog.controller.FrontController;

abstract public class Controller {

	// Attributes
	private FrontController frontController;
	
	
	// Constructor
	public Controller(FrontController controller) {
		frontController = controller;
	}
	
	// Method : Get Front Controller
	public FrontController getFrontController() {
		return frontController;
	}
}

/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app.model;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.elhena.simplejog.util.logger.Log;

public class UICompatibility {

	// Method : Run compatibility 
	public static void apply() {
		// Set Look and Feel
		setLookAndFeel();
	}
	
	// Method : Set Look & Feel
	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		
		catch (ClassNotFoundException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		} 
		
		catch (InstantiationException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		} 
		
		catch (IllegalAccessException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		} 
		
		catch (UnsupportedLookAndFeelException e) {
			Log.x(e.getMessage());
			
			if (Log.debug)
				e.printStackTrace();
		}
	}
}

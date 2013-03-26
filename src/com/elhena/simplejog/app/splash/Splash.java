/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app.splash;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import com.elhena.simplejog.controller.FrontController;
import com.elhena.simplejog.util.loader.ResourcesLoader;

public class Splash extends JWindow {

	// Constants
	private static final long serialVersionUID = 1L;
	
	// Attributes
	FrontController controller;
	
	
	// Constructor
	public Splash(FrontController controller) {
		this.controller = controller;
		
		setSize(new Dimension(400, 200));
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		
		getContentPane().add(new JLabel(new ImageIcon(ResourcesLoader.getImage("logo.png"))));
		setVisible(true);
		
		// Timer
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				close();
			}
			
		}, new Date(System.currentTimeMillis() + 1000 * 5));
		
		// Mouse
		getContentPane().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				timer.cancel();
				close();
			}
		});
	}
	
	// Method : Close splash
	public void close() {
		dispose();
		controller.openHomeFrame();
	}
	
}

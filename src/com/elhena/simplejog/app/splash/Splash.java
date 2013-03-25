/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app.splash;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import com.elhena.simplejog.util.loader.ResourcesLoader;

public class Splash extends JWindow {

	// Constants
	private static final long serialVersionUID = 1L;
	
	
	// Constructor
	public Splash() {
		setSize(new Dimension(400, 200));
		setLocationRelativeTo(null);
		
		getContentPane().add(new JLabel(new ImageIcon(ResourcesLoader.getImage("logo.png"))));
		setVisible(true);
		
		getContentPane().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}
	
	
}

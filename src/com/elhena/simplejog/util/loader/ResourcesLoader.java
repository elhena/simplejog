/**
 * @author Fabien Vanden Bulck
 */

package com.elhena.simplejog.util.loader;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.util.logger.Log;

public class ResourcesLoader {

	// Constants
	private static final String RESOURCES_DIRECTORY = "resources";
	
	// Attributes
	private static HashMap<String, Image> images;
	private static ArrayList<String> imagesToLoad;
	
	
	// Constructor
	public ResourcesLoader() {}
	
	// Method : Get an image
	public static Image getImage(String name) {
		return images.get(name);
	}
	
	// Method : Check if resources are available
	public static void loadResources() {
		images = new HashMap<String, Image>();
		imagesToLoad = new ArrayList<String>();
		Image buffer;
		
		// List of resources to load
		imagesToLoad.add("logo.png");
		
		// Process
		for (String image : imagesToLoad) {
			
			if (new File(RESOURCES_DIRECTORY + "/" + image).exists()) {
				buffer = Toolkit.getDefaultToolkit().getImage(RESOURCES_DIRECTORY + "/" + image);
				images.put(image, buffer);
			}
				
			else {
				JOptionPane.showMessageDialog(null, "Une ou plusieurs ressources nécessaires au bon fonctionnement de l'application sont introuvables.", Application.NAME + " - Erreur durant le chargement des ressources", JOptionPane.INFORMATION_MESSAGE);
				Log.e("One or several resources are missing");
				System.exit(0);
			}
		}
	}
}

/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app.model;

import java.util.ArrayList;

public class Application {

	// Constants
	public static final String NAME = "SimpleJog";
	public static final String VERSION = "0.2";
	public static final String LAST_UPDATE = "23/05/2013";
	public static final String WEBPAGE = "https://github.com/elhena/simplejog";
	public static final String COPYRIGHT = "(C) 2013 Fabien Vanden Bulck";
	public static final String WEBSITE = "http://www.elhena.com";
	
	// Attributes
	public static ArrayList<Contributor> contributors;
	
	// Constructor
	public Application() {}
	
	// Method : Show application infos
	public static void showCredits() {
		
		// App infos
		System.out.println(NAME + " " + VERSION + " - " + LAST_UPDATE);
		System.out.println("Project WebPage: " + WEBPAGE);
		System.out.println(COPYRIGHT + " - " + WEBSITE);
		
		// Contributors
		contributors = new ArrayList<Contributor>();
		contributors.add(new Contributor("Fabien Vanden Bulck", "fabien@elhena.com", "Founder, developer"));
		
		System.out.println("\nContributors:\n");
		for (Contributor contributor : contributors)
			System.out.println("\t* " + contributor);
		
		System.out.println("\n");
	}
}

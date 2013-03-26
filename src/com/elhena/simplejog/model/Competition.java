/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Competition {

	// Attributes
	private String name;
	private Date date;
	private String location;
	private ArrayList<Race> races;
	
	
	// Constructor
	public Competition(String name, String location) {
		this.name = name;
		this.location = location;
		
		races = new ArrayList<Race>();
	}
	
	// Method : Encapsulation
	// Getters
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getLocation() {
		return location;
	}
	
	public ArrayList<Race> getRaces() {
		return races;
	}
	
	// Setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setDate(Date newDate) {
		date = newDate;
	}
	
	public void setLocation(String newLocation) {
		location = newLocation;
	}
	
	// Method : Get default string value
	public String toString() {
		return name + " - " + new SimpleDateFormat("dd/MM/yyyy").format(date) + " - " + location;
	}
	
	// Method : Equals two Competition
	public boolean equals(Competition competition) {
		if (this.name.equals(competition.getName()))
			return true;
		else
			return false;
	}
	
	// Method : Add a race
	public void addRace(Race race) {
		if (!races.contains(race))
			races.add(race);
	}
	
	// Method : Remove a race
	public void removeRace(Race race) {
		if (races.contains(race))
			races.remove(race);
	}
}

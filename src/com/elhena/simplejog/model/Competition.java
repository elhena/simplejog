/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Competition implements Serializable {
	
	// Constants
	private static final long serialVersionUID = 1L;

	// Attributes
	private String name;
	private Date createdAt;
	private Date startTime;
	private String location;
	private CompetitionStatus status;
	private ArrayList<Race> races;
	private static boolean updated = true;
	
	
	// Constructor
	public Competition(String name, String location) {
		this.createdAt = new Date();
		this.name = name;
		this.location = location;
		this.status = CompetitionStatus.STANDBY;
		
		races = new ArrayList<Race>();
	}
	
	// Method : Encapsulation
	// Getters
	public String getName() {
		return name;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public String getLocation() {
		return location;
	}
	
	public CompetitionStatus getStatus() {
		return status;
	}
	
	public ArrayList<Race> getRaces() {
		return races;
	}
	
	// Setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setStartTime(Date newStartTime) {
		startTime = newStartTime;
	}
	
	public void setLocation(String newLocation) {
		location = newLocation;
	}
	
	public void setStatus(CompetitionStatus state) {
		status = state;
	}
	
	// Method : Get default string value
	public String toString() {
		return name + " - " + new SimpleDateFormat("dd/MM/yyyy").format(createdAt) + " - " + location;
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
	
	// Method : Check if data changed
	public static boolean dataIsUpdated() {
		return updated;
	}
	
	// Method : Notify data changed
	public static void notifyDataChanged() {
		updated = false;
	}
	
	// Method : Notify date updated
	public static void notifyDataUpdated() {
		updated = true;
	}
}

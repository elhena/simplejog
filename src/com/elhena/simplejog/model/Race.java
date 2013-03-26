/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Race {

	// Attributes
	private Competition competition;
	private Jogger jogger;
	private Date startTime;
	private Date endTime;
	
	
	// Constructor
	public Race(Competition competition, Jogger jogger) {
		this.competition = competition;
		this.jogger = jogger;
	}
	
	// Methods : Encapsulation
	// Getters
	public Competition getCompetition() {
		return competition;
	}
	
	public Jogger getJogger() {
		return jogger;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	// Setters
	public void setJogger(Jogger newJogger) {
		jogger = newJogger;
	}
	
	public void setStartTime(Date newStartTime) {
		startTime = newStartTime;
	}
	
	public void setEndTime(Date newEndTime) {
		endTime = newEndTime;
	}
	
	// Method : Get duration
	public Date getDuration() {
		return new Date(endTime.getTime() - startTime.getTime());
	}
	
	// Method : Get default string value
	public String toString() {
		return jogger.getName() + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(startTime) + " -> " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endTime) + " - " + new SimpleDateFormat("HH:mm:ss").format(getDuration());
	}
	
	// Method : Equals two Race
	public boolean equals(Race race) {
		if (this.competition.equals(race.getCompetition()) && this.jogger.equals(race.getJogger()))
			return true;
		else
			return false;
	}
}

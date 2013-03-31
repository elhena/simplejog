/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Race implements Comparable<Race> {

	// Attributes
	private int number;
	private Competition competition;
	private Jogger jogger;
	private Date endTime;
	
	
	// Constructor
	public Race(int number, Competition competition, Jogger jogger) {
		this.number = number;
		this.competition = competition;
		this.jogger = jogger;
	}
	
	// Methods : Encapsulation
	// Getters
	public int getNumber() {
		return number;
	}
	
	public Competition getCompetition() {
		return competition;
	}
	
	public Jogger getJogger() {
		return jogger;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	// Setters
	public void setNumber(int newNumber) {
		number = newNumber;
	}
	
	public void setJogger(Jogger newJogger) {
		jogger = newJogger;
	}
	
	public void setEndTime(Date newEndTime) {
		endTime = newEndTime;
	}
	
	// Method : Get duration
	public Date getDuration() {
		return new Date(endTime.getTime() - competition.getStartTime().getTime());
	}
	
	// Method : Get default string value
	public String toString() {
		return number + ": " + jogger.getName() + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(competition.getStartTime()) + " -> " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(endTime) + " - " + new SimpleDateFormat("HH:mm:ss").format(getDuration());
	}
	
	// Method : Equals two Race
	public boolean equals(Race race) {
		if (this.competition.equals(race.getCompetition()) && this.jogger.equals(race.getJogger()))
			return true;
		else
			return false;
	}
	
	// Method : Compare two races
	public int compareTo(Race r) {
		return number - r.getNumber();
	}
}

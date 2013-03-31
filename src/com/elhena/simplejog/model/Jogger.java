/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Jogger {
	
	// Attributes
	private String name;
	private JoggerSex sex;
	private Date birthday;
	
	
	// Constructor
	public Jogger(String name, JoggerSex sex, Date birthday) {
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
	}
	
	// Methods : Encapsulation
	// Getters
	public String getName() {
		return name;
	}
	
	public JoggerSex getSex() {
		return sex;
	}
	
	public String getSexAsString() {
		switch(sex) {
			case MAN: return "Homme";
			case WOMAN: return "Femme";
			default : return "Inconnu";
		}
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	// Setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setSex(JoggerSex newSex) {
		sex = newSex;
	}
	
	public void setBirthday(Date newBirthday) {
		birthday = newBirthday;
	}
	
	// Method : Get default string value
	public String toString() {
		return name + " - " + getSexAsString() + " - " + new SimpleDateFormat("dd/MM/YYYY").format(birthday);
	}
	
	// Method : Equals two Jogger
	public boolean equals(Jogger jogger) {
		if (this.name.equals(jogger.getName()))
			return true;
		else
			return false;
	}
}

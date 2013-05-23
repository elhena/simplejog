/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Jogger implements Serializable {
	
	// Constants
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle RESOURCES = ResourceBundle.getBundle(Jogger.class.getName());
	
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
			case MAN: return RESOURCES.getString("sex.male");
			case WOMAN: return RESOURCES.getString("sex.female");
			default : return RESOURCES.getString("sex.unknown");
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

/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.app.model;

public class Contributor {

	// Attributes
	private String name;
	private String email;
	private String roles;
	
	
	// Constructor
	public Contributor(String name, String email, String roles) {
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
	// Methods : Encapsulation
	// Getters
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getRoles() {
		return roles;
	}
	
	// Setters
	public void setName(String newName) {
		name = newName;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public void setRoles(String newRoles) {
		roles = newRoles;
	}
	
	// Method : Return default string value
	public String toString() {
		return name + " <" + email + "> - " + roles;
	}
 }

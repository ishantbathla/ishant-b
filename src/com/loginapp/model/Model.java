package com.loginapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {

	/**
	 * @author Ishant
	 * creating getter and setter method
	 */
	private static final long serialVersionUID = 1L;
	private String email, firstName, lastName, age, password, gender, address,
			emailFound, feildEmpty, passMismatch, invalidEmail, newUser,
			incorrectPassword, profileUpdated;
	
	private ArrayList<String> allEmail, allFirstName, allLastName, allAge, allPassword, allGender, allAddress;

	public ArrayList<String> getAllEmail() {
		return allEmail;
	}

	public void setAllEmail(ArrayList<String> allEmail) {
		this.allEmail = allEmail;
	}

	public ArrayList<String> getAllFirstName() {
		return allFirstName;
	}

	public void setAllFirstName(ArrayList<String> allFirstName) {
		this.allFirstName = allFirstName;
	}

	public ArrayList<String> getAllLastName() {
		return allLastName;
	}

	public void setAllLastName(ArrayList<String> allLastName) {
		this.allLastName = allLastName;
	}

	public ArrayList<String> getAllAge() {
		return allAge;
	}

	public void setAllAge(ArrayList<String> allAge) {
		this.allAge = allAge;
	}

	public ArrayList<String> getAllPassword() {
		return allPassword;
	}

	public void setAllPassword(ArrayList<String> allPassword) {
		this.allPassword = allPassword;
	}

	public ArrayList<String> getAllGender() {
		return allGender;
	}

	public void setAllGender(ArrayList<String> allGender) {
		this.allGender = allGender;
	}

	public ArrayList<String> getAllAddress() {
		return allAddress;
	}

	public void setAllAddress(ArrayList<String> allAddress) {
		this.allAddress = allAddress;
	}

	public String getProfileUpdated() {
		return profileUpdated;
	}

	public void setProfileUpdated(String profileUpdated) {
		this.profileUpdated = profileUpdated;
	}

	public String getIncorrectPassword() {
		return incorrectPassword;
	}

	public void setIncorrectPassword(String incorrectPassword) {
		this.incorrectPassword = incorrectPassword;
	}

	public String getNewUser() {
		return newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	public String getPassMismatch() {
		return passMismatch;
	}

	public String getInvalidEmail() {
		return invalidEmail;
	}

	public void setInvalidEmail(String invalidEmail) {
		this.invalidEmail = invalidEmail;
	}

	public void setPassMismatch(String passMismatch) {
		this.passMismatch = passMismatch;
	}

	public String getFeildEmpty() {
		return feildEmpty;
	}

	public void setFeildEmpty(String feildEmpty) {
		this.feildEmpty = feildEmpty;
	}

	public String getEmailFound() {
		return emailFound;
	}

	public void setEmailFound(String emailFound) {
		this.emailFound = emailFound;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

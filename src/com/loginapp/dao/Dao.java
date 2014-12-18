package com.loginapp.dao;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.loginapp.constants.DBConstants;
import com.loginapp.model.Model;
/**
 * 
 * @author Ishant
 * Class for doing operations on datastore
 */
public class Dao {

	private static final Logger log = Logger.getLogger(Dao.class.getName());
	/**
	 * 
	 * @author Ishant
	 * Creating object for datastore, query and preparedquery 
	 */
	
	DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
	Query query = new Query(DBConstants.ENTITY_NAME);
	PreparedQuery preparedQuery = dataStore.prepare(query);
	
	
	
	/**
	 * 
	 * @author Ishant
	 * Checking if the email already exist in datastore
	 */
	public boolean checkEmail(Model mo) {
		log.info("IN CHECK EMAIL METHOD");
		String email = mo.getEmail();
		// Iterating the data store to check if email already exist
		for (Entity itrate : preparedQuery.asIterable()) {
			String emailCheck = itrate.getProperty(DBConstants.EMAIL).toString();
			if (email.equalsIgnoreCase(emailCheck)) {
				log.fine("EMAIL FOUND");
				return false;
			}
		}
		log.fine("EMAIL NOT FOUND");
		return true;
	}

	/**
	 * 
	 * @author Ishant
	 * Verifying the password 
	 */
	public boolean checkPassword(Model mo) {
		log.info("IN PASSWORD CHECK METHOD");
		String email = mo.getEmail();
		String password = mo.getPassword();
		
		
		
		//Iterating the data store to get the password and upon verification setting the user data
		
		for (Entity itrate : preparedQuery.asIterable()) {
			String emailCheck = itrate.getProperty(DBConstants.EMAIL).toString();
			if (email.equalsIgnoreCase(emailCheck)) {
				try {

					String passwordCheck = itrate.getProperty(DBConstants.PASSWORD).toString();
					if (password.equals(passwordCheck)) {
						log.fine("PASSWORD MATCHED");
						String fname = itrate.getProperty(DBConstants.FIRST_NAME).toString();
						String lname = itrate.getProperty(DBConstants.LAST_NAME).toString();
						String age = itrate.getProperty(DBConstants.AGE).toString();
						String gender = itrate.getProperty(DBConstants.GENDER).toString();
						String address = itrate.getProperty(DBConstants.ADDRESS).toString();
						mo.setAddress(address);
						mo.setAge(age);
						mo.setGender(gender);
						mo.setFirstName(fname);
						mo.setLastName(lname);

						return true;
					}
				} catch (NullPointerException e) {
					log.severe(e.getMessage());
				}
			}
		}
		log.fine("PASSWORD MISMATCH");
		return false;
	}
	
	public Model getProfile(Model mo)
	{
		log.info("IN GET PROFILE METHOD");
		String email = mo.getEmail();
		for (Entity itrate : preparedQuery.asIterable()) {
			String emailCheck = itrate.getProperty(DBConstants.EMAIL).toString();
			if (email.equalsIgnoreCase(emailCheck)) {
				try {
						log.fine("GETTING PROFILE DATA");
						String password = itrate.getProperty(DBConstants.PASSWORD).toString();
						String fname = itrate.getProperty(DBConstants.FIRST_NAME).toString();
						String lname = itrate.getProperty(DBConstants.LAST_NAME).toString();
						String age = itrate.getProperty(DBConstants.AGE).toString();
						String gender = itrate.getProperty(DBConstants.GENDER).toString();
						String address = itrate.getProperty(DBConstants.ADDRESS).toString();
						mo.setAddress(address);
						mo.setAge(age);
						mo.setGender(gender);
						mo.setFirstName(fname);
						mo.setLastName(lname);
						mo.setPassword(password);

					
				} catch (NullPointerException e) {
					log.fine(e.getMessage());
				}
			}
		}

		return mo;
	}
	
	/**
	 * 
	 * @author Ishant
	 * updation user profile upon request
	 */
	public void updateProfile(Model mo) {

		String email = mo.getEmail();

		Entity entity = new Entity(DBConstants.ENTITY_NAME, email);
		entity.setProperty(DBConstants.FIRST_NAME, mo.getFirstName());
		entity.setProperty(DBConstants.LAST_NAME, mo.getLastName());
		entity.setProperty(DBConstants.AGE, mo.getAge());
		entity.setProperty(DBConstants.EMAIL, mo.getEmail());
		entity.setProperty(DBConstants.GENDER, mo.getGender());
		entity.setProperty(DBConstants.ADDRESS, mo.getAddress());
		entity.setProperty(DBConstants.PASSWORD, mo.getPassword());
		dataStore.put(entity);

	}
	
	/**
	 * @author Ishant
	 * method for getting all the visitor informtion
	 */
	public Model allUser(Model mo) {
		log.info("ALL USER METHOD");
		ArrayList<String> allFirstName = new ArrayList<String>(),
				allLastName = new ArrayList<String>(),
				allAge = new ArrayList<String>(),
				allGender = new ArrayList<String>(),
				allEmail = new ArrayList<String>(),
				allPassword = new ArrayList<String>(),
				allAddress = new ArrayList<String>();

		for (Entity itrate : preparedQuery.asIterable()) {
			log.fine("GETTING ALL DATA FOR VISITORS");
			String fname = itrate.getProperty(DBConstants.FIRST_NAME).toString();
			String lname = itrate.getProperty(DBConstants.LAST_NAME).toString();
			String age = itrate.getProperty(DBConstants.AGE).toString();
			String gender = itrate.getProperty(DBConstants.GENDER).toString();
			String address = itrate.getProperty(DBConstants.ADDRESS).toString();
			String password = itrate.getProperty(DBConstants.PASSWORD).toString();
			String email = itrate.getProperty(DBConstants.EMAIL).toString();
			System.out.println(fname);
			allFirstName.add(fname);
			allLastName.add(lname);
			allAge.add(age);
			allGender.add(gender);
			allAddress.add(address);
			allEmail.add(email);
			allPassword.add(password);

		}
		mo.setAllFirstName(allFirstName);
		mo.setAllLastName(allLastName);
		mo.setAllAge(allAge);
		mo.setAllAddress(allAddress);
		mo.setAllEmail(allEmail);
		mo.setAllGender(allGender);
		mo.setAllPassword(allPassword);
		return mo;
	}
	
	/**
	 * 
	 * @author Ishant
	 * method for creating new profile using email as key 
	 */

	public void createProfile(Model mo) {
		log.fine("Creating User Profile");
		Entity entity = new Entity(DBConstants.ENTITY_NAME, mo.getEmail());
		entity.setProperty(DBConstants.FIRST_NAME, mo.getFirstName());
		entity.setProperty(DBConstants.LAST_NAME, mo.getLastName());
		entity.setProperty(DBConstants.EMAIL, mo.getEmail());
		entity.setProperty(DBConstants.AGE, mo.getAge());
		entity.setProperty(DBConstants.GENDER, mo.getGender());
		entity.setProperty(DBConstants.ADDRESS, mo.getAddress());
		entity.setProperty(DBConstants.PASSWORD, mo.getPassword());
		dataStore.put(entity);
	}

	/**
	 * 
	 * @author Ishant
	 * method for deletion of profile 
	 */
	
	public void deleteProfile(Model mo) {
		log.fine("DELETING USER PROFILE");
		Entity entity = new Entity(DBConstants.ENTITY_NAME, mo.getEmail());
		dataStore.delete(entity.getKey());

	}

}

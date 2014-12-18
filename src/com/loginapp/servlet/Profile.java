package com.loginapp.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.loginapp.dao.Dao;
import com.loginapp.model.Model;
import com.loginapp.constants.FormConstants;

@SuppressWarnings("serial")
public class Profile extends HttpServlet {
	private static final Logger log = Logger.getLogger(Profile.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doProcess(req, resp);
	}

	public void doProcess(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method="";
		try{
		method = req.getParameter("method").toUpperCase();
		log.info("Method is ::" + method);
		}
		catch(NullPointerException e){
			log.severe("Method value is ::"+e.getMessage());
			RequestDispatcher reqDis = req
					.getRequestDispatcher("login.jsp");
			reqDis.include(req, resp);
			
		}
		switch (method) {
		case "CREATEACCOUNT":
			createAccount(req, resp);
			break;

		case "HOMEPAGE":
			homePage(req, resp);
			break;
		
		case "ALREADYREGISTERED":
			alreadyRegistered(req, resp);
			break;
			
		case "NEWUSER":
			newUser(req, resp);
			break;
			
		case "LOGIN":
			login(req, resp);
			break;
			
		case "SIGNOUT":
			signout(req, resp);
			break;
			
		case "VIEWPROFILE":
			viewProfile(req, resp);
			break;
			
		case "UPDATEACCOUNT":	
			UpdateAccount(req, resp);
			break;
			
		case "DELETEPROFILE":
			deleteProfile(req, resp);
			break;
			
		case "VIEWALLPROFILE":
			viewAllProfile(req, resp);
			break;
			
		case "OTHERPROFILE":
			otherProfile(req, resp);
			break;
			
		case "UPDATEOTHERACCOUNT":
			updateOtherAccount(req, resp);
			break;
			
		case "DELETEOTHERPROFILE":
			deleteOtherProfile(req, resp);
			break;
		}
		
		
	}

	/**
	 * method for creating new account
	 */
	private void createAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering Create Account");
		HttpSession session = req.getSession();
		Model mo = new Model();
		Dao dao = new Dao();
		String email = req.getParameter(FormConstants.EMAIL);
		
		if (email != "" && email != null) {
			if (email.indexOf('@') < 1
					|| email.indexOf('@') + 2 < email.length()) {
				mo.setEmail(email);
				log.fine("Email is not empty");
				boolean emailCheck = dao.checkEmail(mo);
				if (emailCheck) {
					log.fine("Password Matched");
					String fname = req.getParameter(FormConstants.FIRST_NAME);
					String lname = req.getParameter(FormConstants.LAST_NAME);
					String age = req.getParameter(FormConstants.AGE);

					String address = req.getParameter(FormConstants.ADDRESS);
					String gender = req.getParameter(FormConstants.GENDER);
					
					String password = req.getParameter(FormConstants.PASSWORD);
					String rePassword = req.getParameter(FormConstants.RETYPE_PASSWORD);
					
					if (fname.equals("") || lname.equals("") || age.equals("")
							|| address.equals("") || gender.equals("")
							|| password.equals("") || rePassword.equals("")) {
						log.fine("Some Value is missing");
						mo.setFeildEmpty("Please enter all the feilds");
						session.setAttribute("emptyFeild", mo);
						RequestDispatcher reqDis = req
								.getRequestDispatcher("signup.jsp");
						log.fine("Redirecting to signup");
						reqDis.include(req, resp);
					} else {
						if (password.equals(rePassword)) {
							//upon validation setting user data
							
							mo.setFirstName(fname);
							mo.setLastName(lname);
							mo.setAge(age);
							mo.setAddress(address);
							mo.setGender(gender);
							mo.setPassword(password);
							//calling the dao class method to set the values
							log.fine("Calling DAO");
							dao.createProfile(mo);
							log.fine("After DAO");
							session.setAttribute("userValue", mo);
							log.fine("Session id ::"+session.getId());
							RequestDispatcher reqDis = req
									.getRequestDispatcher("welcome.jsp");
							log.fine("Exiting Create Account");
							reqDis.include(req, resp);
						} else {
							log.fine("Password Mismatch");
							mo.setPassMismatch(" Both Password doesn't match");
							session.setAttribute("passMismatch", mo);
							RequestDispatcher reqDis = req
									.getRequestDispatcher("signup.jsp");
							reqDis.include(req, resp);
						}
					}
				} else {
					log.fine("already registered");
					mo.setEmailFound("Email already registered. Please Login");
					session.setAttribute("emailRegistered", mo);
					RequestDispatcher reqDis = req
							.getRequestDispatcher("login.jsp");
					reqDis.include(req, resp);
				}
			} else {
				log.fine("Password Mismatch");
				mo.setInvalidEmail("Please Enter valid Email");
				session.setAttribute("invalidEmail", mo);
				RequestDispatcher reqDis = req
						.getRequestDispatcher("signup.jsp");
				reqDis.include(req, resp);
			}
		} else {
			log.info("Email input is empty");
			mo.setInvalidEmail("Please Enter Email");
			session.setAttribute("invalidEmail", mo);
			RequestDispatcher reqDis = req.getRequestDispatcher("signup.jsp");
			reqDis.include(req, resp);
		}

	}

	/**
	 * method telling the user already exist and dragging the user to login page
	 */
	
	private void alreadyRegistered(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		log.info("Entering Already Registered");
		RequestDispatcher reqDis = req.getRequestDispatcher("login.jsp");
		log.info("Exiting Already Registered");
		reqDis.include(req, resp);
	}

	/**
	 * method telling the user is new and dragging the user to signup page
	 */
	
	private void newUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering New User");
		RequestDispatcher reqDis = req.getRequestDispatcher("signup.jsp");
		log.info("Exiting New User");
		reqDis.include(req, resp);
	}
	
	/**
	 * method dragging the user to home page
	 */
	
	private void homePage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering HomePage");
		RequestDispatcher reqDis = req.getRequestDispatcher("welcome.jsp");
		log.info("Exiting HomePage");
		reqDis.include(req, resp);
	}

	/**
	 * method for logging out and invalidating the session
	 */
	private void signout(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering Signout");
		HttpSession session = req.getSession(false);
		String logout = req.getParameter(FormConstants.LOGOUT);
		log.fine("Signout Value is ::"+logout);
		if (logout.equalsIgnoreCase("true")) {
			session.removeAttribute("userValue");
			session.invalidate();
			RequestDispatcher reqDis = req.getRequestDispatcher("logout.jsp");
			log.info("Exiting");
			reqDis.include(req, resp);
		}
	}

	/**
	 * method for updating the user profile
	 */
	private void UpdateAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering UpdateAccont");
		HttpSession session = req.getSession(false);
		Model mo = (Model) session.getAttribute("userValue");
		
		Dao dao = new Dao();
		
		String fname = req.getParameter(FormConstants.FIRST_NAME);
		String lname = req.getParameter(FormConstants.LAST_NAME);
		String age = req.getParameter(FormConstants.AGE);
		String address = req.getParameter(FormConstants.ADDRESS);
		String gender = req.getParameter(FormConstants.GENDER);
		String password = req.getParameter(FormConstants.PASSWORD);

		if (fname.equals("") || lname.equals("") || age.equals("")
				|| address.equals("") || gender.equals("")) {
			log.fine("Values are empty");
			mo.setFeildEmpty("Please enter all the feilds");
			session.setAttribute("emptyFeild", mo);
			RequestDispatcher reqDis = req
					.getRequestDispatcher("myprofile.jsp");
			reqDis.include(req, resp);
		} else {

			
			mo.setFirstName(fname);
			mo.setLastName(lname);
			mo.setAge(age);
			mo.setAddress(address);
			mo.setGender(gender);
			mo.setPassword(password);
			log.fine("Calling DAO");
			dao.updateProfile(mo);
			log.fine("After DAO");
			mo.setProfileUpdated("Your Profile has been updated");
			session.setAttribute("userValue", mo);

			RequestDispatcher reqDis = req
					.getRequestDispatcher("myprofile.jsp");
			log.info("Exiting UpdateAccont");
			reqDis.include(req, resp);
		}

	}
	
	/**
	 * method for updating the user profile
	 */
	private void updateOtherAccount(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering Update Other account");
		HttpSession session = req.getSession(false);
		Model mo = (Model) session.getAttribute("otherUser");
		Dao dao = new Dao();
		String fname = req.getParameter(FormConstants.FIRST_NAME);
		String lname = req.getParameter(FormConstants.LAST_NAME);
		String age = req.getParameter(FormConstants.AGE);
		String address = req.getParameter(FormConstants.ADDRESS);
		String gender = req.getParameter(FormConstants.GENDER);
		String password = req.getParameter(FormConstants.PASSWORD);

		if (fname.equals("") || lname.equals("") || age.equals("")
				|| address.equals("") || gender.equals("")) {
			log.fine("Some Values are missing");
			mo.setFeildEmpty("Please enter all the feilds");
			session.setAttribute("emptyFeild", mo);
			RequestDispatcher reqDis = req
					.getRequestDispatcher("otherProfile.jsp");
			reqDis.include(req, resp);
		} else {

			
			mo.setFirstName(fname);
			mo.setLastName(lname);
			mo.setAge(age);
			mo.setAddress(address);
			mo.setGender(gender);
			mo.setPassword(password);
			log.fine("Before DAO");
			dao.updateProfile(mo);
			log.fine("After DAO");
			mo.setProfileUpdated("This Profile has been updated");
			session.setAttribute("otherUser", mo);

			RequestDispatcher reqDis = req
					.getRequestDispatcher("otherProfile.jsp");
			log.info("Exiting Update Other account");
			reqDis.include(req, resp);
		}

	}

	/**
	 * method dragging user to myprofile page
	 */
	private void viewProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering VIEW PROFILE");
		RequestDispatcher reqDis = req.getRequestDispatcher("myprofile.jsp");
		log.info("Exiting VIEW PROFILE");
		reqDis.include(req, resp);
	}
	
	/**
	 * method dragging user to otherProfile page
	 */
	private void otherProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering Other PROFILE");
		HttpSession session = req.getSession(false);
		Model mo = (Model) session.getAttribute("userValue");
		
		String email = mo.getEmail();
		String id = req.getParameter("id");
		if(email.equalsIgnoreCase(id))
		{
			log.fine("The user clicked on his own Profile");
			viewProfile(req, resp);
		}
		else
		{
			Model model = new Model();
			log.fine("Calling DAO");
			Dao dao = new Dao();
			log.fine("After DAO");
			model.setEmail(id);
			model = dao.getProfile(model);
			session.setAttribute("otherUser", model);
			RequestDispatcher reqDis = req.getRequestDispatcher("otherProfile.jsp");
			log.info("Exiting OTHER PROFILE");
			reqDis.include(req, resp);
			
		}
	}

	/**
	 * method dragging user to view all vistors profile page
	 */
	private void viewAllProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.setLevel(Level.INFO);
		log.info("IN ALL VIEW METHOD");
		HttpSession session = req.getSession(false);
		Dao dao = new Dao();
		Model mo = new Model();
		mo = dao.allUser(mo);
		log.fine("MO VALUE"+dao.toString());
		session.setAttribute("allUser", mo);
		log.fine("Session :: " + session.getId());
		RequestDispatcher reqDis = req.getRequestDispatcher("allProfile.jsp");
		log.info("EXITING METHOD");
		reqDis.include(req, resp);
		
	}
	
	/**
	 * Method for deleting the user Profile
	 */
	private void deleteProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.info("Entering DELETE PROFILE METHOD");
		HttpSession session = req.getSession(false);
		
		Model mo = (Model) session.getAttribute("userValue");
		log.fine("Calling DAO");
		Dao dao = new Dao();
		log.fine("After DAO");
		dao.deleteProfile(mo);
		session.removeAttribute("userValue");
		session.invalidate();
		
		RequestDispatcher reqDis = req.getRequestDispatcher("signup.jsp");
		log.info("Exiting DELETE PROFILE");
		reqDis.include(req, resp);

	}

	/**
	 * Method for deleting the others Profile
	 */
	private void deleteOtherProfile(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
HttpSession session = req.getSession(false);
	log.info("Entering DELETE OTHER PROFILE");
		Model mo = (Model) session.getAttribute("otherUser");
		log.fine("Calling Dao");
		Dao dao = new Dao();
		log.fine("After Dao");
		dao.deleteProfile(mo);
		log.info("Calling View all Profile");
		viewAllProfile(req, resp);
	}
	
	/**
	 * method for logging in for already existing user
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		log.fine("Entering login Method");
		HttpSession session = req.getSession();
		Model mo = new Model();
		Dao dao = new Dao();
		String email = req.getParameter(FormConstants.EMAIL);

		if (email != "" && email != null) {
			if (email.indexOf('@') < 1
					|| email.indexOf('@') + 2 < email.length()) {
				mo.setEmail(email);

				boolean emailCheck = dao.checkEmail(mo);
				if (!emailCheck) {
					String password = req.getParameter(FormConstants.PASSWORD);
					mo.setPassword(password);
					boolean passwordCheck = dao.checkPassword(mo);
					if (passwordCheck) {
						log.fine("After Password Check");
						session.setAttribute("userValue", mo);
						RequestDispatcher reqDis = req
								.getRequestDispatcher("welcome.jsp");
						log.fine("Exiting Login Method");
						reqDis.include(req, resp);
					} else {
						log.fine("The username or password is incorrect");
						mo.setIncorrectPassword("The username or password is incorrect");
						session.setAttribute("incorrectPassword", mo);
						RequestDispatcher reqDis = req
								.getRequestDispatcher("login.jsp");
						reqDis.include(req, resp);
					}
				} else {
					log.fine("Your Email is not registered with us. Please SignUp");
					mo.setNewUser("Your Email is not registered with us. Please SignUp");
					session.setAttribute("newUser", mo);
					RequestDispatcher reqDis = req
							.getRequestDispatcher("signup.jsp");
					reqDis.include(req, resp);

				}
			} else {
				log.fine("Please Enter valid Email");
				mo.setInvalidEmail("Please Enter valid Email");
				session.setAttribute("invalidEmail", mo);
				RequestDispatcher reqDis = req
						.getRequestDispatcher("login.jsp");
				reqDis.include(req, resp);
			}
		} else {
			log.fine("Please Enter Email");
			mo.setInvalidEmail("Please Enter Email");
			session.setAttribute("invalidEmail", mo);
			RequestDispatcher reqDis = req.getRequestDispatcher("login.jsp");
			reqDis.include(req, resp);
		}

	}

}

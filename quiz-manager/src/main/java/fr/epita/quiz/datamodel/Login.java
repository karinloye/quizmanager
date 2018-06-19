/**
 * 
 */
package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ADEBOWALE
 *
 */
@Entity
public class Login {

	/** The e-mail */
	@Id
	private String email;
	
	/** The password */
	private String password;
	
	public Login() {
		
	}

	public Login(String email2, String password2) {
		this.email=email2;
		this.password=password2;
	}

	/**
	 * Gets the e-mail.
	 * 
	 * @return String The e-mail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the e-mail.
	 * 
	 * @param email The e-mail address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 * 
	 * @return String The password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password The password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}

package nl.sogyo.oauth.javagooglesignin;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

/**
 * The GoogleUser class represents information of a Google user.
 * Each GooglUser object contains a user ID, a given name, a family name
 * and a email address.
 */
public class GoogleUser {

	private String userId;
	private String givenName;
	private String familyName;
	private String email;
	
	/**
	 * Initializes the GoogleUser object.
	 * The constructor needs a payload object that could obtained by the
	 * GoogleIdToken. It throws an EmailNotVerifiedException if the email
	 * address in the payload has not been verified by Google.
	 * 
	 * @param payload
	 * @throws EmailNotVerifiedException
	 */
	public GoogleUser(Payload payload) throws EmailNotVerifiedException {
		email = payload.getEmail();
		if (isEmailVerified(payload)) {
			userId = payload.getSubject();
			givenName = (String) payload.get("given_name");
			familyName = (String) payload.get("family_name");
		} else {
			throw new EmailNotVerifiedException(email);
		}
	}
	
	/**
	 * Initializes the GoogleUser object.
	 * This constructor is only for testing.
	 * The variables are directly initialized.
	 * Authentication has never happened.
	 * DON'T USE THIS IN PRODUCTION!
	 * 
	 * @param userId
	 * @param givenName
	 * @param familyName
	 * @param email
	 */
	public GoogleUser(String userId, String givenName, String familyName, String email) {
		this.userId = userId;
		this.givenName = givenName;
		this.familyName = familyName;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	private boolean isEmailVerified(Payload payload) {
		return Boolean.valueOf(payload.getEmailVerified());
	}
}

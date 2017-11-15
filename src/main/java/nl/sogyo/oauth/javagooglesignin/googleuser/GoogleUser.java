package nl.sogyo.oauth.javagooglesignin.googleuser;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import nl.sogyo.oauth.javagooglesignin.exception.EmailNotVerifiedException;

/**
 * The GoogleUser class represents information of a Google user.
 * Each child of a GooglUser object contains a user ID, a given name, a family name
 * and a email address.
 */
public abstract class GoogleUser {

	private String userId;
	private String givenName;
	private String familyName;
	private String email;
	
	/**
	 * Initializes the variables for the AuthenticGoogleUser object.
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
	 * Initializes the variables for the TestGoogleUser object.
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

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}
	
	public String getEmail() {
		return email;
	}
	
	private boolean isEmailVerified(Payload payload) {
		return Boolean.valueOf(payload.getEmailVerified());
	}
}

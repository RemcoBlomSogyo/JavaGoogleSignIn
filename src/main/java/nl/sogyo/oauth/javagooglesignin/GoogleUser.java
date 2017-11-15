package nl.sogyo.oauth.javagooglesignin;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import nl.sogyo.oauth.javagooglesignin.exception.EmailNotVerifiedException;

/**
 * The GoogleUser class represents information of a Google user.
 * The GooglUser object contains a user ID, a given name, a family name
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

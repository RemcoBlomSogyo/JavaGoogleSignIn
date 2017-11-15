package nl.sogyo.oauth.javagooglesignin.googleuser;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import nl.sogyo.oauth.javagooglesignin.exception.EmailNotVerifiedException;

public class AuthenticGoogleUser extends GoogleUser {
	
	/**
	 * Initializes the AuthenticGoogleUser object.
	 * This GoogleUser class is for production and can only be used for real Google accounts.
	 * The constructor needs a payload object that could obtained by the
	 * GoogleIdToken. It throws an EmailNotVerifiedException if the email
	 * address in the payload has not been verified by Google.
	 * 
	 * @param payload
	 * @throws EmailNotVerifiedException
	 */
	public AuthenticGoogleUser(Payload payload) throws EmailNotVerifiedException {
		super(payload);
	}
}

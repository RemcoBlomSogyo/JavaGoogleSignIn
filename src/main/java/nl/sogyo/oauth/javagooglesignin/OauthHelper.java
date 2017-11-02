package nl.sogyo.oauth.javagooglesignin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * The OauthHelper class makes it possible to retrieve Google profile information
 * by an authorization code that has been obtained by a Google sign-in that
 * has been sent by the client side of an app.
 */
public class OauthHelper {
	
	private String clientId;
	
	/**
	 * Initializes the OauthHelper class.
	 * The constructor needs a file object that leads to a client secret JSON file 
	 * that has a client id. The client id is required to validate the id token at Google.
	 * To download the JSON file, go to console.developer.google.com and create an OAuth 2.0 client id. 
	 * 
	 * @param clientSecretJson
	 * @throws IOException
	 */
	public OauthHelper(File clientSecretJson) throws IOException {
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), 
				new FileReader(clientSecretJson));
		clientId = clientSecrets.getDetails().getClientId();
	}
	
	/**
	 * Initializes the OauthHelper class.
	 * The constructor needs a client id string. The client id is required to validate the id token 
	 * at Google. To get a client id, go to console.developer.google.com and create an OAuth 2.0 client id.
	 * 
	 * @param clientId
	 * @param redirectUri
	 */
	public OauthHelper(String clientId) {
		this.clientId = clientId;
	}
	
	/**
	 * Gets a token from Google and creates a Google user object with the user ID, given name, family
	 * name and email address from it.
	 * 
	 * Takes an id token and verifies it at Google.
	 * It returns a GoogleUser object that is made of the payload of the id token.
	 * 
	 * @param idTokenString
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException
	 * @throws EmailNotVerifiedException
	 * @throws InvalidTokenException
	 */
	public GoogleUser getUserFromToken(String idTokenString) throws IOException, GeneralSecurityException, 
			EmailNotVerifiedException, InvalidTokenException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), 
				JacksonFactory.getDefaultInstance()).setAudience(Collections.singletonList(clientId)).build();

		GoogleIdToken idToken;
		try {
			idToken = verifier.verify(idTokenString);
		} catch (IllegalArgumentException e) {
			idToken = null;
		}

		if (idToken == null) {
			throw new InvalidTokenException();
		} 
		Payload payload = idToken.getPayload();
		GoogleUser user = new GoogleUser(payload);
		return user;
	}
}

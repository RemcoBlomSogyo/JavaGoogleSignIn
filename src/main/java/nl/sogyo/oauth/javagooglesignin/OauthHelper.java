package nl.sogyo.oauth.javagooglesignin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * The OauthHelper class makes it possible to retrieve Google profile information
 * by an authorization code that has been obtained by a Google sign-in that
 * has been sent by the client side of an app.
 */
public class OauthHelper {
	
	private String clientId;
	private String clientSecret;
	private String tokenServerEncodedUrl;
	private String redirectUri;
	private static final String DEFAULT_TOKEN_SERVER_ENCODED_URL = "https://www.googleapis.com/oauth2/v4/token";
	
	/**
	 * Initializes the OauthHelper class.
	 * The constructor needs a file object that leads to a JSON file with the client id
	 * and client secret to obtain user information of Google. To download the JSON file,
	 * go to console.developer.google.com and create an OAuth 2.0 client ID. The second parameter
	 * is the redirect URI that the user goes to when the login succeeds. The URI has to be configured
	 * at the OAuth client ID in the developer console.
	 * 
	 * @param clientSecretJson
	 * @param redirectUri
	 * @throws IOException
	 */
	public OauthHelper(File clientSecretJson, String redirectUri) throws IOException {
		this(clientSecretJson, DEFAULT_TOKEN_SERVER_ENCODED_URL, redirectUri);
	}
	
	/**
	 * Initializes the OauthHelper class.
	 * The constructor needs a file object that leads to a JSON file with the client id
	 * and client secret to obtain user information of Google. To download the JSON file,
	 * go to console.developer.google.com and create an OAuth 2.0 client ID. The third parameter
	 * is the redirect URI that the user goes to when the login succeeds. The URI has to be configured
	 * at the OAuth client ID in the developer console. This constructor needs also a token server
	 * encoded url. The default url is https://www.googleapis.com/oauth2/v4/token.
	 * 
	 * @param clientSecretJson
	 * @param tokenServerEncodedUrl
	 * @param redirectUri
	 * @throws IOException
	 */
	public OauthHelper(File clientSecretJson, String tokenServerEncodedUrl, String redirectUri) throws IOException {
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), 
				new FileReader(clientSecretJson));
		clientId = clientSecrets.getDetails().getClientId();
		clientSecret = clientSecrets.getDetails().getClientSecret();
		this.tokenServerEncodedUrl = tokenServerEncodedUrl;
		this.redirectUri = redirectUri;
	}
	
	/**
	 * Initializes the OauthHelper class.
	 * The constructor needs a client id and client secret to obtain user information of Google. 
	 * To get the client id and client secret, go to console.developer.google.com and create an 
	 * OAuth 2.0 client ID. The third parameter is the redirect URI that the user goes to when the 
	 * login succeeds. The URI has to be configured at the OAuth client ID in the developer console. 
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param redirectUri
	 */
	public OauthHelper(String clientId, String clientSecret, String redirectUri) {
		this(clientId, clientSecret, DEFAULT_TOKEN_SERVER_ENCODED_URL, redirectUri);
	}
	
	/**
	 * Initializes the OauthHelper class.
	 * The constructor needs a client id and client secret to obtain user information of Google. 
	 * To get the client id and client secret, go to console.developer.google.com and create an 
	 * OAuth 2.0 client ID. This constructor needs also a token server encoded url. The default url 
	 * is https://www.googleapis.com/oauth2/v4/token. The fourth parameter is the redirect URI that 
	 * the user goes to when the login succeeds. The URI has to be configured at the OAuth client ID 
	 * in the developer console. 
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param tokenServerEncodedUrl
	 * @param redirectUri
	 */
	public OauthHelper(String clientId, String clientSecret, String tokenServerEncodedUrl, String redirectUri) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.tokenServerEncodedUrl = tokenServerEncodedUrl;
		this.redirectUri = redirectUri;
	}
	
	/**
	 * Gets a token from Google and creates a Google user object with the user ID, given name, family
	 * name and email address from it.
	 * 
	 * @param authorizationCode
	 * @return
	 * @throws IOException
	 * @throws EmailNotVerifiedException
	 */
	public GoogleUser getUserFromGoogle(String authorizationCode) throws IOException, EmailNotVerifiedException {
		GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
				new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
				tokenServerEncodedUrl, clientId, clientSecret, authorizationCode,
				redirectUri).execute();
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		GoogleUser user = new GoogleUser(payload);
		return user;
	}
}


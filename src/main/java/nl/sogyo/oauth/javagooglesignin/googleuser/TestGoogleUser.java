package nl.sogyo.oauth.javagooglesignin.googleuser;

public class TestGoogleUser extends GoogleUser {
	
	/**
	 * Initializes the TestGoogleUser object.
	 * This GoogleUser class is for testing and can be used for test accounts.
	 * It initializes the variables directly.
	 * 
	 * @param userId
	 * @param givenName
	 * @param familyName
	 * @param email
	 */
	public TestGoogleUser(String userId, String givenName, String familyName, String email) {
		super(userId, givenName, familyName, email);
	}
}

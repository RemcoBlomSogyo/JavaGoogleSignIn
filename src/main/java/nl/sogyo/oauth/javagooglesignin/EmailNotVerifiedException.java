package nl.sogyo.oauth.javagooglesignin;

/**
 * The EmailNotVerifiedException class is a checked exception that 
 * can be thrown if an email address has not been verified by Google.
 */
public class EmailNotVerifiedException extends Exception {

	private static final long serialVersionUID = 6631667331385645633L;

	public EmailNotVerifiedException(String email) {
		super("Email is not verified by Google: " + email);
	}
}

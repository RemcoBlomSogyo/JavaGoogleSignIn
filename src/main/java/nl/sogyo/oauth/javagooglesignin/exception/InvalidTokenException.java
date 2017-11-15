package nl.sogyo.oauth.javagooglesignin.exception;

/**
 * The InvalidTokenException class is a checked exception that 
 * can be thrown if the Google token verifier returns null and 
 * IOException and GeneralSecurityException has not been thrown.
 */
public class InvalidTokenException extends Exception {

	private static final long serialVersionUID = 3655022714071380777L;

	public InvalidTokenException() {
		super("The token is verified invalid by Google");
	}
}

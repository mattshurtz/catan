package shared.communication.params;

/**
 * Wrapper object for a user's username and password.  This is used in the user authentication request, the registration request, etc.
 * @author Alex
 *
 */
public class Credentials {
	private String username;
	private String password;
	
	public Credentials() {
		
	}
	
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return "Credentials w/ username: " + this.username + " and password: " + this.password;
	}
}

package shared.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 *
 */
public class User implements Serializable{
    private String username;
    private String password;

    public User(String username, String password) {
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
    
    /**
     * Static method to check if the given username is valid.
     * A username is valid if it's between 3 and 7 characters and made of
     * alphanumerics, underscores, and hyphens.
     * 
     * @param uname
     * @return 
     */
    public static boolean isValidUsername( String uname ) {
        
        if ( uname == null )
            return false;
        
        if ( uname.length() > 7 || uname.length() < 3 )
            return false;
        
        if ( !uname.matches("[a-zA-Z0-9_-]+") )
            return false;
        
        // Else, it passed all the tests, so it's good!
        return true;
    }
    
    /**
     * Static method to check if a given password is valid.
     * A password is valid if it's 5 or more characters and made of
     * alphanumerics, underscores, and hyphens.
     * 
     * @param pswd
     * @return 
     */
    public static boolean isValidPassword( String pswd ) {
        if ( pswd == null )
            return false;
        
        if ( pswd.length() < 5 )
            return false;
        
        if ( !pswd.matches("[a-zA-Z0-9_-]+") )
            return false;
        
        // Else, it passed all the tests, so it's good!
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
}

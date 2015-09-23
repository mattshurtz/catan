package shared.model;

/**
 * 
 *
 */
public class User {
    private String username;
    private String password;

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
}

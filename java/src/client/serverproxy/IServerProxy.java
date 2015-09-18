package client.serverproxy;
import shared.communication.*;
import shared.exceptions.ServerException;

/**
 * Interface for the Server Proxy and Mock Server Proxy.
 * Defines all functionality listed on the TA Swagger page
 * 
 * @author Alex Morris
 *
 */
public interface IServerProxy {
	// Operations about users
	
	/**
	 * Logs a user into the game system
	 * @param userCredentials The Credentials object containing the user's username and password
	 * @return True if the user successfully authenticates, false if not
	 * @throws ServerException if the server request fails
	 */
	boolean login(Credentials userCredentials) throws ServerException;
	
	
}

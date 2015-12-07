package server.persistence.DAO;

import server.gameinfocontainer.UserInfoBank;
import server.persistence.sqlPlugin.SQLConnectionUtility;

public interface IUsersDAO {
    
    public void clearUsers() throws Exception;
    
    public UserInfoBank getUsers() throws Exception;
    
    public SQLConnectionUtility getConnectionUtility();

	public void addUser(int userID, String username, String password) throws Exception;

}

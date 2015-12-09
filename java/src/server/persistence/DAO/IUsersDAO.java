package server.persistence.DAO;

import server.gameinfocontainer.UserInfoBank;

public interface IUsersDAO {
    
    public void clearUsers() throws Exception;
    
    public UserInfoBank getUsers() throws Exception;
    
    public IConnections getConnectionUtility();

	public void addUser(int userID, String username, String password) throws Exception;

}

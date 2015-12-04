package server.persistence.DAO;

import server.gameinfocontainer.UserInfoBank;

public interface IUsersDAO {

    public void addUser() throws Exception;
    
    public void clearUsers() throws Exception;
    
    public UserInfoBank getUsers() throws Exception;
    
}

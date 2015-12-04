package server.persistence.DAO;

public interface IUsersDAO {

    public void addUser() throws Exception;
    
    public void clearUsers() throws Exception;
    
    public void getUsers() throws Exception;
    
}

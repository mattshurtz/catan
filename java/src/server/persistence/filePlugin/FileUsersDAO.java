/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.filePlugin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IConnections;
import server.persistence.DAO.IUsersDAO;
import static server.persistence.filePlugin.FileGamesDAO.getGameInfoContainer;

/**
 *
 */
public class FileUsersDAO implements IUsersDAO {
    
    private FileConnection fc = null;
    
    public FileUsersDAO( FileConnection fc ) {
        this.fc = fc;
    }

    @Override
    public IConnections getConnectionUtility() {
       return this.fc;
    }

    @Override
    public void addUser(int userID, String username, String password) throws Exception {
        GameInfoContainer gic = getGameInfoContainer( fc );
        if( userID >= gic.getUsers().getUsers().size())
        {
            gic.getUsers().addUser(username, password);
        }
        fc.writeGamesBytes( toBytes(gic) );    
    }

    @Override
    public void clearUsers() throws Exception {
        //GameInfoContainer gic = getGameInfoContainer( fc );
        GameInfoContainer gic = new GameInfoContainer(false);
    	//gic.setUser(new UserInfoBank(false));
        byte[] bytes = toBytes( gic );
        fc.writeGamesBytes(bytes);
    }

    @Override
    public UserInfoBank getUsers() throws Exception {
            GameInfoContainer gic = getGameInfoContainer( fc );
            return gic.getUsers();
    }
    
    public byte[] toBytes( Object o ) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            byte[] bytes = baos.toByteArray();
            oos.close();
            baos.close();

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    
    
}

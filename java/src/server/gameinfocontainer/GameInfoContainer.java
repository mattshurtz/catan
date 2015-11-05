/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;

/**
 *
 * @author Scott
 */
public class GameInfoContainer {
    private ModelBank models;
    private UserInfoBank users;
    
    /**
     * Constructs the GameInfoContainer Object
     */
    GameInfoContainer() {
        
    }
    
    /**
     * 
     * @return ModelBank of game models currently being handled by the server.
     */
    ModelBank getModels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    /**
     * 
     * @return UserInfoBank of registered user information.
     */
    UserInfoBank getRegisteredUserInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

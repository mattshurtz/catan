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
     * Called by the create.java command class and adds a game to the 
     * GameInfoContainer
     */
    public void createGame(){
        
    }
    
    /**
    *Checks password if it is valid adds user to 
     */
    public boolean login(){
        return false;
    }
    
    /**
     * Adds player to the list of games stored in the correct model
     */
    public boolean joinGame(){
        return false;
    }
    
    /**
     * Returns a list of the games in this model
     */
    public String Games(){
        return "list of games";
    }
    
    /**
     * adds new registered player to the userInfoBank
     */
    public void register(){
        
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

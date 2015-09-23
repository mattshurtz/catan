package client.proxy;

import java.util.List;

import shared.communication.params.*;
import shared.communication.responses.*;
import shared.exceptions.ServerException;
import shared.model.User;

public class MockProxy implements IServerProxy {

    public MockProxy(){
        
    }
    
    @Override
    public String listAi() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postCommands(PostCommandsRequest postCommandsRequest) throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCommands() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addAi() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildRoad() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void offerTrade() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptTrade() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void maritimeTrade() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buyDevCard() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playYearOfPlenty() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playRoadBuilding() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playSoldier() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playMonopoly() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buildSettlement() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buildCity() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendChat() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rollNumber() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void robPlayer() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finishTurn() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(Credentials userCredentials) throws ServerException {
        if ( userCredentials == null ) 
            return false;

        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        if ( username == null || password == null )
            return false;
        
        boolean ret = username.matches("(Sam|Brooke|Pete|Mark|Ken|Squall|Scott)");
        ret = ret && username.equalsIgnoreCase(password);
        return ret;
    }

    @Override
    public boolean register(Credentials userCredentials) throws ServerException {
        if ( userCredentials == null ) 
            return false;

        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        
        boolean ret = User.isValidUsername( username );
        ret = ret && User.isValidPassword( password );
        return ret;
    }

    @Override
    public List<GameResponse> getGamesList() throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CreateGameResponse createGame(CreateGameRequest gameRequests) throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean joinGame(JoinGameRequest joinRequest) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean saveGame(SaveGameRequest saveRequest) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean loadGame(LoadGameRequest loadRequest) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getGameModel(int version) throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String resetGame() throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

}

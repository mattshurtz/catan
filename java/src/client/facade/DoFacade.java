package client.facade;

import client.proxy.IServerProxy;
import java.util.Random;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.RobPlayerRequest;
import shared.communication.params.moves.RollNumberRequest;
import shared.communication.params.moves.SendChatRequest;
import shared.definitions.ResourceType;
import shared.exceptions.ServerException;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.Model;

/**
 * A facade for all the "doing" actions, as opposed to "can do" -- rather than
 * just checking whether a player *can* play a road, these method actually play
 * a road, build a settlement, take a development card, etc.
 */
public class DoFacade {
    
    
    IServerProxy proxy;
    Model model;
    
    public DoFacade(IServerProxy proxy, Model model){
        this.proxy = proxy;
        this.model = model;
    }
    
    public void offerTrade(){
        
    }
    
    /**
     * type (acceptTrade),
     * playerIndex (integer): Who's accepting / rejecting this trade,
     * willAccept (boolean): Whether you accept the trade or not
     */
    public void acceptTrade(){
        
    }
    
    public void maritimeTrade(){
        
    }
    
        
    public void buyDevCard() throws ServerException{
        MoveRequest request = new MoveRequest();
        request.setType("buyDevCard");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buyDevCard(request);        
    }
    
    public void playYearOfPlenty() throws ServerException{
        
        //NOTE(Scott): somehow determine chosen resources
        ResourceType resource1 = null;
        ResourceType resource2 = null;
        
        PlayYearOfPlentyRequest request = new PlayYearOfPlentyRequest(resource1, resource2);
        request.setType("Year_of_Plenty");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playYearOfPlenty(request);
    }
    
    public void playRoadBuilding(){
        
    }
    
    public void playSoldier(){
        
    }
    
    public void playMonopoly(){
        
    }
    
    /**
     * Create a build road request using the model and use the proxy
     * to send this parameter to the server. 
     */
    public void buildRoad(){
       //Create the build roads parameter using the model and pass it into build road then proxy sends request to server
       //proxy.buildRoad();
    }
    
    /**
     * Create a build settlement request using the model and use the proxy
     * to send this parameter to the server. 
     * @param location where settlement is to be built
     */
    public void buildSettlement(VertexLocation location){
        
    }
    /**
    * Create a build city request using the model and use the proxy
    * to send this parameter to the server.
     * @param location where settlement is to be built
    */
    public void buildCity(VertexLocation location){
        
    }
    
    public void sendChat() throws ServerException{
        //NOTE(Scott): get message somehow
        String comment = "";
    
        SendChatRequest request = new SendChatRequest(comment);
        request.setType("sendChat");
        request.setPlayerIndex(CatanFacade.getMyPlayerIndex());
        
        proxy.sendChat(request);
    }
    
    public void rollNumber() throws ServerException{
        Random rand = new Random();
        
        int firstDice = rand.nextInt(6) + 1;
        int secondDice = rand.nextInt(6) + 1;
        
        int total = firstDice + secondDice;
        
        RollNumberRequest request = new RollNumberRequest(total);
        request.setType("rollNumber");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.rollNumber(request);
    }
    
    public void robPlayer() throws ServerException{
        //NOTE(Scott): choose victim somehow
        int victimIndex = -1;
        //Note(Scott): choose location somehow
        HexLocation location = new HexLocation(0,0);
        
        RobPlayerRequest request = new RobPlayerRequest(victimIndex, location);
        request.setType("robPlayer");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.robPlayer(request);
    }
    
    public void finishTurn() throws ServerException{
        MoveRequest request = new MoveRequest();
        request.setType("finishTurn");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.finishTurn(request);
    }
    
    
}

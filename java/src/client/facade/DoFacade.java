package client.facade;

import client.proxy.IServerProxy;
import java.util.Random;
import shared.communication.params.moves.AcceptTradeRequest;
import shared.communication.params.moves.BuildCityRequest;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.communication.params.moves.DiscardCardsRequest;
import shared.communication.params.moves.MaritimeTradeRequest;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.OfferTradeRequest;
import shared.communication.params.moves.PlayMonopolyRequest;
import shared.communication.params.moves.PlayRoadBuildingRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.RobPlayerRequest;
import shared.communication.params.moves.RollNumberRequest;
import shared.communication.params.moves.SendChatRequest;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.Player;
import shared.model.ResourceList;

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
    
    public void offerTrade() throws ServerException{
        //NOTE(Scott): somehow get the resources willing to trade
        ResourceList offer = null;
        int receiverIndex = -1;
        
        OfferTradeRequest request = new OfferTradeRequest(offer, receiverIndex);
        request.setType("offerTrade");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.offerTrade(request);
    }
    
    /**
     * type (acceptTrade),
     * playerIndex (integer): Who's accepting / rejecting this trade,
     * willAccept (boolean): Whether you accept the trade or not
     */
    public void acceptTrade() throws ServerException{
        //NOTE(Scott): determine if will accept trade
        boolean willAccept = false;
        
        AcceptTradeRequest request = new AcceptTradeRequest(willAccept);
        request.setType("acceptTrade");
        request.setPlayerIndex(CatanFacade.getMyPlayerIndex());
 
        proxy.acceptTrade(request);
    }
    
    public void maritimeTrade() throws ServerException{
        //NOTE(Scott): get ratio and the desired resources.
        int ratio = -1;
        ResourceType input = null;
        ResourceType output = null;
        
        MaritimeTradeRequest request = new MaritimeTradeRequest(ratio, input, output);
        request.setType("maritimeTrade");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
    
        proxy.maritimeTrade(request);
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
    
    public void playRoadBuilding() throws ServerException{
        //NOTE(Scott): somehow determine (AND CHECK) chosen locations.
        EdgeLocation spot1 = null;
        EdgeLocation spot2 = null;
        
        PlayRoadBuildingRequest request = new PlayRoadBuildingRequest(spot1, spot2);
        request.setType("Road_Building");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playRoadBuilding(request);
    }
    
    public void playSoldier() throws ServerException, GetPlayerException{
        //NOTE(Scott): choose victim somehow
        int victimIndex = -1;
        //Note(Scott): choose location somehow
        HexLocation location = new HexLocation(0,0);
        
        //increment Player soldiers played
        int currentPlayerIndex = CatanFacade.getModel().getTurnTracker().getCurrentTurn();
        Player current = CatanFacade.getModel().getPlayer(currentPlayerIndex);
        current.incrementSoldiers();
        
        //NOTE(Scott): check if now the largest Army)
        
        //build request
        RobPlayerRequest request = new RobPlayerRequest(victimIndex, location);
        request.setType("Soldier");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playSoldier(request); 
    }
    
    public void playMonopoly() throws ServerException{
        //NOTE(Scott): somehow determine resourcetype wanted
        ResourceType resource = null;
        
        PlayMonopolyRequest request = new PlayMonopolyRequest(resource);
        request.setType("Monopoly");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playMonopoly(request);
    }
    
    
    public void playMonument() throws ServerException, GetPlayerException {
        //increment victory points and number of monuments
        int currentPlayerIndex = CatanFacade.getModel().getTurnTracker().getCurrentTurn();
        Player current = CatanFacade.getModel().getPlayer(currentPlayerIndex);
        
        current.incrementMonuments();
        current.incrementVictoryPoints();
        
        MoveRequest request = new MoveRequest();
        request.setType("Monument");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playMonument(request);
    }
    /**
     * Create a build road request using the model and use the proxy
     * to send this parameter to the server. 
     */
    public void buildRoad() throws ServerException{
       //Create the build roads parameter using the model and pass it into build road then proxy sends request to server
       //NOTE(Scott): somehow determine location for road and if free 
       EdgeLocation location = null;
       boolean free = false;
        
       BuildRoadRequest request = new BuildRoadRequest(location, free);
       request.setType("buildRoad");
       request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
       
       proxy.buildRoad(request);
    }
    
    /**
     * Create a build settlement request using the model and use the proxy
     * to send this parameter to the server. 
     * @param location where settlement is to be built
     */
    public void buildSettlement(VertexLocation location) throws ServerException{
        //NOTE(Scott): get location to build
        boolean free = false;
        
        BuildSettlementRequest request = new BuildSettlementRequest(location, free);
        request.setType("buildSettlement");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buildSettlement(request);
    }
    /**
    * Create a build city request using the model and use the proxy
    * to send this parameter to the server.
     * @param location where settlement is to be built
    */
    public void buildCity(VertexLocation location) throws ServerException{
        BuildCityRequest request = new BuildCityRequest(location);
        request.setType("buildCity");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buildCity(request);
        
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
    
    public void discardCards() throws ServerException {
        //NOTE(Scott): get the cards that the player wants to discard
        ResourceList discardedCards = null;
        
        DiscardCardsRequest request = new DiscardCardsRequest(discardedCards);
        request.setType("discardCards");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.discardCards(request);
    }
    
    public void finishTurn() throws ServerException{
        MoveRequest request = new MoveRequest();
        request.setType("finishTurn");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.finishTurn(request);
    }
    
    
}

package client.facade;

import client.proxy.IServerProxy;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import shared.exceptions.InsufficientSupplies;
import shared.exceptions.InvalidLocation;
import shared.exceptions.ServerException;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.Player;
import shared.model.ResourceList;

/**
 * An abstract class defining all the CanDo methods, extended by the 
 * CanDo - MyTurn - facade (which actually checks whether things are doable)
 * and the CanDo - NotMyturn - facade (which typically returns false because you
 * can only accept trades and chat when it's not your turn).
 */
public class StateBase {

    IServerProxy proxy;
    Model model;

    public StateBase(IServerProxy proxy, Model model) {
        this.proxy = proxy;
        this.model = model;
    }

    /**
     * @param playerIndex of whom you offer the trade.
     * @param resourceList offer is a list or resources you offer.
     * @return whether the player has actually has the resources to be offered.
     */
    public boolean canOfferTrade(int playerIndex, ResourceList resourceList) {
        return false;
    }

    public boolean canAcceptTrade(ResourceList tradeOffer) {
        return false;
    }

    public boolean canOfferMaritimeTrade(ResourceType resourceType) {
        return false;
    }
    
    public boolean canAcceptMaritimeTrade(ResourceType resourceType) {
        return false;
    }

    public boolean canBuyDevCard() {
        return false;
    }
    
    public boolean canPlayMonument() {
        return false;
    }

    public boolean canPlayYearOfPlenty() {
        return false;
    }

    public boolean canPlayRoadBuilding() {
        return false;
    }

    public boolean canPlaySoldier() {
        return false;
    }

    public boolean canPlayMonopoly() {
        return false;
    }

    /**
     * @param roadLocation where player would like to place road
     * @return true if player has enough resources to buy a road, the edge
     * location in question is connected to a road or settlement/city belonging
     * to the player.
     */
    public boolean canBuildRoad(EdgeLocation roadLocation) throws InvalidLocation {
        return false;
    }
    
    /**
     * 
     * @return true if insufficentSupplies is not thrown. 
     */
    public boolean canBuyRoad() throws InsufficientSupplies {
        return false;
    }

    /**
     * @param vertex this is where the player would like build a
     * settlement
     * @return true if player has enough resources to buy a Settlement, has a
     * road connected to Hex vertex, and settlement is not within two hexEdges
     * of another Settlement.
     */
    public boolean canBuildSettlement(VertexLocation vertex) {
        return false;
    }

    public boolean canBuySettlement() {
    	return false;
    }
    
    /**
     * @param vertex this is where the player would like build a City
     * @return true if player owns a settlement at this location and has remaining cities
     */
    public boolean canBuildCity(VertexLocation vertex) {
        return false;
    }
    /**
     * @return true if the player has the resources to buy a city
     */
    public boolean canBuyCity() {
    	return false;
    }
    
    public boolean canSendChat() {
        return true;
    }

    public boolean canRollNumber() {
        return false;
    }

    public boolean canRobPlayer(int playerIndex) {
        return false;
    }

    public boolean canFinishTurn() {
        return false;
    }
    
    public boolean canDiscardCards( int playerIndex, ResourceList discardedCards ) {
        return false;
    }
    
    public void setModel( Model m ) {
        this.model = m;
    }
    
    ////////////////////////// Do Facade Functions ////////////////////////////
    
    
        public void offerTrade( ResourceList offer, int receiverIndex ) throws ServerException{
        
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
    public void acceptTrade( boolean willAccept ) throws ServerException{
        
        AcceptTradeRequest request = new AcceptTradeRequest(willAccept);
        request.setType("acceptTrade");
        request.setPlayerIndex(CatanFacade.getMyPlayerIndex());
 
        proxy.acceptTrade(request);
    }
    
    public void maritimeTrade( int ratio, ResourceType input, ResourceType output ) throws ServerException{
        MaritimeTradeRequest request = new MaritimeTradeRequest(0,ratio, input, output);
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
    
    public void playYearOfPlenty( ResourceType resource1, ResourceType resource2 ) throws ServerException{
        
        PlayYearOfPlentyRequest request = new PlayYearOfPlentyRequest(resource1, resource2);
        request.setType("Year_of_Plenty");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playYearOfPlenty(request);
    }
    
    public void playRoadBuilding( EdgeLocation spot1, EdgeLocation spot2 ) throws ServerException{
        PlayRoadBuildingRequest request = new PlayRoadBuildingRequest(CatanFacade.getMyPlayerIndex(), spot1, spot2);
        request.setType("Road_Building");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playRoadBuilding(request);
    }
    
    public void playSoldier( int victimIndex, HexLocation location ) throws ServerException, GetPlayerException{
        
        //increment Player soldiers played
        int currentPlayerIndex = CatanFacade.getModel().getTurnTracker().getCurrentTurn();
        Player current = CatanFacade.getModel().getPlayer(currentPlayerIndex);
        current.incrementSoldiers();
        
        // TODO check if now the largest Army
        
        //build request
        RobPlayerRequest request = new RobPlayerRequest(currentPlayerIndex,victimIndex, location);
        request.setType("Soldier");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playSoldier(request); 
    }
    
    public void playMonopoly( ResourceType resource ) throws ServerException{
        
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
    public void buildRoad( EdgeLocation location, boolean free ) throws ServerException{
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
    
    public void sendChat( String comment ) throws ServerException{
    
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
    
    public void robPlayer(int victimIndex, HexLocation location) throws ServerException{
        int currentPlayerIndex = CatanFacade.getModel().getTurnTracker().getCurrentTurn();
        RobPlayerRequest request = new RobPlayerRequest(currentPlayerIndex,victimIndex, location);
        request.setType("robPlayer");
        request.setPlayerIndex(currentPlayerIndex);
        
        proxy.robPlayer(request);
    }
    
    public void discardCards( ResourceList discardedCards ) throws ServerException {
        DiscardCardsRequest request = new DiscardCardsRequest(CatanFacade.getMyPlayerIndex(), discardedCards);
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

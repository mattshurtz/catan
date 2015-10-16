package client.facade;

import client.proxy.IServerProxy;
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
    
    /**
     * type (acceptTrade),
     * playerIndex (integer): Who's accepting / rejecting this trade,
     * willAccept (boolean): Whether you accept the trade or not
     */
    public void acceptTrade( boolean willAccept ) throws ServerException{
    }
    
    public void offerTrade( ResourceList offer, int receiverIndex ) throws ServerException{
    }
    
    
    
    public void maritimeTrade( int ratio, ResourceType input, ResourceType output ) throws ServerException{
        
    }
    
        
    public void buyDevCard() throws ServerException{
    }
    
    public void playYearOfPlenty( ResourceType resource1, ResourceType resource2 ) throws ServerException{
    }
    
    public void playRoadBuilding( EdgeLocation spot1, EdgeLocation spot2 ) throws ServerException{
    }
    
    public void playSoldier( int victimIndex, HexLocation location ) throws ServerException, GetPlayerException{
    }
    
    public void playMonopoly( ResourceType resource ) throws ServerException{
    }
    
    
    public void playMonument() throws ServerException, GetPlayerException {
    }
    /**
     * Create a build road request using the model and use the proxy
     * to send this parameter to the server. 
     */
    public void buildRoad( EdgeLocation location, boolean free ) throws ServerException{
    }
    
    /**
     * Create a build settlement request using the model and use the proxy
     * to send this parameter to the server. 
     * @param location where settlement is to be built
     */
    public void buildSettlement(VertexLocation location) throws ServerException{
    }
    /**
    * Create a build city request using the model and use the proxy
    * to send this parameter to the server.
     * @param location where settlement is to be built
    */
    public void buildCity(VertexLocation location) throws ServerException{
    }
    
    public void sendChat( String comment ) throws ServerException{
    
        SendChatRequest request = new SendChatRequest(comment);
        request.setType("sendChat");
        request.setPlayerIndex(CatanFacade.getMyPlayerIndex());
        
        proxy.sendChat(request);
    }
    
    public int rollNumber() throws ServerException{
        return 0;
    }
    
    public void robPlayer(int victimIndex) throws ServerException{
    }
    
    public boolean canPlaceRobber(HexLocation hexLocation){
        return false;
    }
    
    public void discardCards( ResourceList discardedCards ) throws ServerException {
    }
    
    public void finishTurn() throws ServerException{
    }
}

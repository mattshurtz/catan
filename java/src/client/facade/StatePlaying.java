package client.facade;

import client.proxy.IServerProxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.communication.params.moves.BuildCityRequest;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.communication.params.moves.MaritimeTradeRequest;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.OfferTradeRequest;
import shared.communication.params.moves.PlayMonopolyRequest;
import shared.communication.params.moves.PlayRoadBuildingRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.RobPlayerRequest;
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
 * A facade for when it's my turn -- separated from the NotMyTurn facade to
 * avoid an <code>if (it's my turn)</code> statement at the beginning of every
 * single method.
 */
public class StatePlaying extends StateBase {

    @Override
    public boolean canFinishTurn() {
        return model.canFinishTurn();
    }

    @Override
    public boolean canRollNumber() {
        return model.canRollNumber();
    }

    @Override
    public boolean canSendChat() {
        return true;
    }

    @Override
    public boolean canBuyCity() {
        return model.canBuyCity();
    }

    @Override
    public boolean canBuildCity(VertexLocation vertex) {
        return model.canBuildCity(vertex); 
    }

    @Override
    public boolean canBuySettlement() {
        return model.canBuySettlement();
    }

    @Override
    public boolean canBuildSettlement(VertexLocation vertex) {
        return canBuySettlement() && model.canBuildSettlement(vertex);
    }
    
    @Override
    public boolean canPlayMonument() {
        return model.canPlayMonopoly(CatanFacade.getMyPlayerIndex());
    }

    @Override
    public boolean canPlayMonopoly() {
        return model.canPlayMonopoly(CatanFacade.getMyPlayerIndex());
    }

    @Override
    public boolean canPlaySoldier() {
        return model.canPlaySoldier(CatanFacade.getMyPlayerIndex());
    }

    @Override
    public boolean canPlayRoadBuilding() {
        return model.canPlayRoadBuilding(CatanFacade.getMyPlayerIndex()); 
    }

    @Override
    public boolean canPlayYearOfPlenty() {
        return model.canPlayYearOfPlenty(CatanFacade.getMyPlayerIndex()); 
    }

    @Override
    public boolean canBuyDevCard() {
        return model.canBuyDevCard(CatanFacade.getMyPlayerIndex()); 
    }

    @Override
    public boolean canOfferMaritimeTrade(ResourceType resourceType) {
        return model.canOfferMaritimeTrade(resourceType);
    }
    
    @Override
    public boolean canAcceptMaritimeTrade(ResourceType resourceType) {
        return model.canAcceptMaritimeTrade(resourceType);
    }
    
    @Override
    public boolean canAcceptTrade(ResourceList tradeOffer) {
        return false;
    }

    @Override
    public boolean canOfferTrade(int playerIndex, ResourceList resourceList) {
        return true;
    }
    
    @Override
    public boolean canBuyRoad() {
    	return model.canBuyRoad();
    }
    
    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) {
    	return model.canBuyRoad() && model.canBuildRoad(roadLocation);
    }
    
   public StatePlaying(IServerProxy proxy, Model model) {
       super( proxy, model );
   }
   
   @Override
   public void offerTrade( ResourceList offer, int receiverIndex ) throws ServerException{
        
        OfferTradeRequest request = new OfferTradeRequest(offer, receiverIndex);
        request.setType("offerTrade");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.offerTrade(request);
    }
    
    @Override
    public void maritimeTrade( int ratio, ResourceType input, ResourceType output ) throws ServerException{
        MaritimeTradeRequest request = new MaritimeTradeRequest(0,ratio, input, output);
        request.setType("maritimeTrade");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
    
        proxy.maritimeTrade(request);
    }
    
    @Override    
    public void buyDevCard() throws ServerException{
        MoveRequest request = new MoveRequest();
        request.setType("buyDevCard");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buyDevCard(request);        
    }
    
    @Override
    public void playYearOfPlenty( ResourceType resource1, ResourceType resource2 ) throws ServerException{
        
        PlayYearOfPlentyRequest request = new PlayYearOfPlentyRequest(resource1, resource2);
        request.setType("Year_of_Plenty");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playYearOfPlenty(request);
    }
    
    @Override
    public void playRoadBuilding( EdgeLocation spot1, EdgeLocation spot2 ) throws ServerException{
        PlayRoadBuildingRequest request = new PlayRoadBuildingRequest(CatanFacade.getMyPlayerIndex(), spot1, spot2);
        request.setType("Road_Building");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playRoadBuilding(request);
    }
    
    @Override
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
    
    @Override
    public void playMonopoly( ResourceType resource ) throws ServerException{
        
        PlayMonopolyRequest request = new PlayMonopolyRequest(resource);
        request.setType("Monopoly");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.playMonopoly(request);
    }
    
    @Override
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
    
    @Override
    public void buildRoad( EdgeLocation location, boolean free ) throws ServerException{
       BuildRoadRequest request = new BuildRoadRequest(location, free);
       request.setType("buildRoad");
       request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
       
       proxy.buildRoad(request);
    }
    
    @Override
    public void buildSettlement(VertexLocation location) throws ServerException{
        //NOTE(Scott): get location to build
        boolean free = false;
        
        BuildSettlementRequest request = new BuildSettlementRequest(location, free);
        request.setType("buildSettlement");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buildSettlement(request);
    }
    
    @Override
    public void buildCity(VertexLocation location) throws ServerException{
        BuildCityRequest request = new BuildCityRequest(location);
        request.setType("buildCity");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buildCity(request);
    }
    
    @Override
    public void finishTurn() throws ServerException{
        MoveRequest request = new MoveRequest();
        request.setType("finishTurn");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.finishTurn(request);
    }
}

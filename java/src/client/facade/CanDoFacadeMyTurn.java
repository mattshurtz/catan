package client.facade;

import client.proxy.IServerProxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.InsufficientSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.ResourceList;

/**
 * A facade for when it's my turn -- separated from the NotMyTurn facade to
 * avoid an <code>if (it's my turn)</code> statement at the beginning of every
 * single method.
 */
public class CanDoFacadeMyTurn extends CanDoFacade {

    @Override
    public boolean canFinishTurn() {
        return true;
    }

    @Override
    public boolean canRobPlayer() {
        return super.canRobPlayer(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canRollNumber() {
        return true;
    }

    @Override
    public boolean canSendChat() {
        return super.canSendChat(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuyCity() {
        return super.canBuyCity(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuildCity(VertexLocation vertex) {
        return super.canBuildCity(vertex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuySettlement() {
        return super.canBuySettlement(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuildSettlement(VertexLocation vertex) {
        return super.canBuildSettlement(vertex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canPlayMonopoly() {
        return super.canPlayMonopoly(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canPlaySoldier() {
        return super.canPlaySoldier(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canPlayRoadBuilding() {
        return super.canPlayRoadBuilding(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canPlayYearOfPlenty() {
        return super.canPlayYearOfPlenty(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuyDevCard() {
        return super.canBuyDevCard(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canMaritimeTrade(String inputResource, String ouputResouce, double ratio) {
        return super.canMaritimeTrade(inputResource, ouputResouce, ratio); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canAcceptTrade(int playerIndex) {
        return super.canAcceptTrade(playerIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canOfferTrade(int playerIndex, ResourceList resourceList) {
        return super.canOfferTrade(playerIndex, resourceList); //To change body of generated methods, choose Tools | Templates.
    }

    /*@Override
   	public boolean canBuyRoad() throws InsufficientSupplies {
        try {
            return model.canBuyRoad();
        } catch (InsufficientSupplies ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    } */
    
    @Override
    public boolean canBuyRoad() {
    	return model.canBuyRoad();
    }

    /*
    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) throws InvalidLocation {
        try {
            return model.canBuildRoad(roadLocation);
        } catch (InvalidLocation ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return false;
    }
    */
    
    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) {
    	return model.canBuildRoad(roadLocation);
    }
    
   public CanDoFacadeMyTurn(IServerProxy proxy, Model model) {
       super( proxy, model );
   }
   
}

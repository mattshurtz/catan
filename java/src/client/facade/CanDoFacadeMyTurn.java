package client.facade;

import client.proxy.IServerProxy;
import shared.exceptions.InsufficentSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.model.Model;

/**
 * A facade for when it's my turn -- separated from the NotMyTurn facade to
 * avoid an <code>if (it's my turn)</code> statement at the beginning of every
 * single method.
 */
public class CanDoFacadeMyTurn extends CanDoFacade {

    @Override
    public boolean canBuyRoad() throws InsufficentSupplies {
        return super.canBuyRoad(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) throws InvalidLocation {
        return super.canBuildRoad(roadLocation); //To change body of generated methods, choose Tools | Templates.
    }
    
   public CanDoFacadeMyTurn(IServerProxy proxy, Model model) {
       super( proxy, model );
   }
}

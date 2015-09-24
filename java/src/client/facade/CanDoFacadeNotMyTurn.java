package client.facade;

import client.proxy.IServerProxy;
import shared.exceptions.InsufficentSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.model.Model;

/**
 * A "can do" facade for when it's not my turn -- basically just automatically
 * returns false for everything other than chatting and responding to trades.
 */
public class CanDoFacadeNotMyTurn extends CanDoFacade {

    @Override
    public boolean canBuyRoad() throws InsufficentSupplies {
        return super.canBuyRoad(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) throws InvalidLocation {
        return super.canBuildRoad(roadLocation); //To change body of generated methods, choose Tools | Templates.
    }
    public CanDoFacadeNotMyTurn(IServerProxy proxy, Model model) {
       super( proxy, model );
   }
}

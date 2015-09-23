package client.facade;

import client.proxy.IServerProxy;
import shared.exceptions.InsufficentSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.model.Model;

/**
 *
 * @author JanPaul
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

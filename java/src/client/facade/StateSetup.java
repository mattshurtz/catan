/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.exceptions.ServerException;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class StateSetup extends StateBase {

    public StateSetup(IServerProxy proxy, Model model) {
        super(proxy, model);
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
}

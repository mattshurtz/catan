package client.facade;

import client.proxy.IServerProxy;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.communication.params.moves.MoveRequest;
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
        System.out.println("build Road Location: "+location);
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

        boolean free = CatanFacade.isSetup();
        
        BuildSettlementRequest request = new BuildSettlementRequest(location, free);
        request.setType("buildSettlement");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.buildSettlement(request);
    }
    
    /**
     * We can always afford to buy a road when we're in setup
     * @return 
     */
    public boolean canBuyRoad() {
        return true;
    }
    
    /**
     * We can always afford to buy a settlement when we're in setup
     * @return 
     */
    public boolean canBuySettlement() {
        return true;
    }
    
    /**
     * Roads can only be placed next to settlements during setup, so we let this method
     * call the corresponding canDo method in the model.
     * 
     * @param roadLocation
     * @return 
     */
    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) {
    	return model.canBuildRoad(roadLocation);
    }
    
    /** 
     * Settlements can be placed basically anywhere during setup
     * 
     * @param vertex
     * @return 
     */
    @Override
    public boolean canBuildSettlement(VertexLocation vertex) {
        return true;
    }
    
    @Override
    public void finishTurn() throws ServerException{
        MoveRequest request = new MoveRequest();
        request.setType("finishTurn");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.finishTurn(request);
    }
}

package client.resources;

import java.util.*;

import client.base.*;
import client.facade.CatanFacade;
import client.misc.MessageView;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.definitions.TurnStatus;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;

/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, Observer {

    private Map<ResourceBarElement, IAction> elementActions;

    public ResourceBarController(IResourceBarView view) {

        super(view);
        CatanFacade.addObserver(this);
        elementActions = new HashMap<ResourceBarElement, IAction>();
    }

    @Override
    public IResourceBarView getView() {
        return (IResourceBarView) super.getView();
    }

    /**
     * Sets the action to be executed when the specified resource bar element is
     * clicked by the user
     *
     * @param element The resource bar element with which the action is
     * associated
     * @param action The action to be executed
     */
    public void setElementAction(ResourceBarElement element, IAction action) {

        elementActions.put(element, action);
    }

    /**
     * For each resourceType, buildingType, and cardType for your client's
     * player call getView().setElementAmount
     */
    public void setResources() {
        try {
    System.out.println("Setting Resources...");
            if (CatanFacade.getMyPlayerIndex() > -1 && CatanFacade.getMyPlayerIndex() < 4) {
                getView().setElementAmount(ResourceBarElement.BRICK, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getBrick());
                getView().setElementAmount(ResourceBarElement.ORE, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getOre());
                getView().setElementAmount(ResourceBarElement.SHEEP, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getSheep());
                getView().setElementAmount(ResourceBarElement.WHEAT, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getWheat());
                getView().setElementAmount(ResourceBarElement.WOOD, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getWood());

                getView().setElementAmount(ResourceBarElement.BUY_CARD, 0);
                getView().setElementAmount(ResourceBarElement.CITY, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getCities());
                getView().setElementAmount(ResourceBarElement.ROAD, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getRoads());
                getView().setElementAmount(ResourceBarElement.SETTLEMENT, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getSettlements());
                getView().setElementAmount(ResourceBarElement.SOLDIERS, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getSoldiers());
            }
        } catch (GetPlayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Verify they can build a road (resources and remaining settlement amount)
     * if so: call the executeElementAction(ResourceBarElement.ROAD); else
     * display message why they can't
     *
     */
    @Override
    public void buildRoad() {

        if (CatanFacade.getCurrentState().canBuyRoad()) {
            executeElementAction(ResourceBarElement.ROAD);
        } else {
            MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Not enough resources or settlements.");
            error.showModal();
        }
    }

    /**
     * same as for build road
     */
    @Override
    public void buildSettlement() {
        if (CatanFacade.getCurrentState().canBuySettlement()) {
            executeElementAction(ResourceBarElement.SETTLEMENT);
        } else {
            MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Not enough resources or settlements.");
            error.showModal();
        }

    }

    /**
     * same as for build road
     */
    @Override
    public void buildCity() {
    	if (CatanFacade.getCurrentState().canBuyCity()) {
    		executeElementAction(ResourceBarElement.CITY);
        } else {
            MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Not enough resources or cities.");
            error.showModal();
        }
        
    }

    @Override
    public void buyCard() {
        if(CatanFacade.getCurrentState().canBuyDevCard()){
            executeElementAction(ResourceBarElement.BUY_CARD);
        }else{
            MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Not enough resources or cities.");
            error.showModal(); 
        }
    }

    /**
     * same as for build road
     */
    @Override
    public void playCard() {
        executeElementAction(ResourceBarElement.PLAY_CARD);
    }

    private void executeElementAction(ResourceBarElement element) {

        if (elementActions.containsKey(element)) {

            IAction action = elementActions.get(element);
            action.execute();
        }
    }
    
    public void setButtonsNotMyTurn(){
        if(!CatanFacade.isMyTurn()){
            getView().setElementEnabled(ResourceBarElement.CITY, false);
            getView().setElementEnabled(ResourceBarElement.ROAD, false);
            getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
            getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
            getView().setElementEnabled(ResourceBarElement.PLAY_CARD, false); 
        }
    }

    @Override
    public void update(Observable o, Object arg) {
System.out.println("Updating Resource Bar...");
        setButtonsNotMyTurn();
        setResources();
        
        // Pop up the correct dialogs if it's setup
        if ( CatanFacade.isSetup() ) {
            doSetup();
        }
    }
    
    private void doSetup() {
        int playerIndex = CatanFacade.getMyPlayerIndex();
        Player p;
        try {
            p = CatanFacade.getModel().getPlayer( playerIndex );
        } catch ( GetPlayerException e ) {
            System.err.println("Error while getting player!");
            return;
        }
        // Check whether we're playing a road or a settlement
        int numSettlementsPlayed = 5 - p.getSettlements();
        int numRoadsPlayed = 15 - p.getRoads();

        // Have to use the delayed methods for building roads & settlements
        // here because we need to wait for the join game modal to close.
        if ( CatanFacade.isFirstRound() ) {
            if ( numSettlementsPlayed == 0 ) {
                // play first settlement
                delayedBuildSettlement();
            } else if ( numRoadsPlayed == 0 ) {
                // play first road
                delayedBuildRoad();
            } else {
                // They've played both -- finish the turn
                finishTurn();
            }
        } else { // second round
            if ( numSettlementsPlayed == 1 ) {
                // play another settlement
                delayedBuildSettlement();
            } else if ( numRoadsPlayed == 1 ) {
                // play another road
                delayedBuildRoad();
            } else {
                // They've played both -- finish the turn
                finishTurn();
            }
        }
    }
    
    private void finishTurn() {
        try {
            CatanFacade.getCurrentState().finishTurn();
        } catch (ServerException ex) {
            Logger.getLogger(ResourceBarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Calls buildSettlement() after a delay of 1 second.
     * (uses a timer so it's on a separate thread and this one can continue
     * doing stuff.)
     */
    private void delayedBuildSettlement() {
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    buildSettlement();
                }
            }, 
            750
        );
    }
    
    /**
     * Calls buildRoad() after a delay of 1 second.
     * (uses a timer so it's on a separate thread and this one can continue
     * doing stuff.)
     */
    private void delayedBuildRoad() {
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    buildRoad();
                }
            }, 
            750
        );
    }
    
}

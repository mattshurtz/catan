package client.resources;

import java.util.*;

import client.base.*;
import client.facade.CatanFacade;
import client.misc.MessageView;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;

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
        
        CatanFacade.setCurrentStateToPlaying();
        
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
        executeElementAction(ResourceBarElement.CITY);
    }

    @Override
    public void buyCard() {
        executeElementAction(ResourceBarElement.BUY_CARD);
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
        setButtonsNotMyTurn();
        setResources();
    }

}

package client.discard;

import shared.definitions.*;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.ResourceList;
import client.base.*;
import client.facade.CatanFacade;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
    private ResourceList discardedCards;

	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 *disable discardbutton in the getDiscardView()
	 *
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		discardedCards = new ResourceList();
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
        //return (IDiscardView)super.getView();
        try {
            discardedCards = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources();
            return (IDiscardView)super.getView();
        } catch (GetPlayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

		//return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	
	/**
	 * add recource to a list to be discarded
	 * getDiscardView().setStateMessage(message); where message is 
	 * how many out of required amount have been selected
	 * enable discard button if enough cards (but not more) have been selected
	 */
	@Override
	public void increaseAmount(ResourceType resource) {
        discardedCards.addResource(resource, 1);
        updateAfterIncreaseOrDecrease(resource);

	}

	/**
	 * undoes things you did in increase function
	 */
	@Override
	public void decreaseAmount(ResourceType resource) {
        discardedCards.subtractResource(resource, 1);
        updateAfterIncreaseOrDecrease(resource);

	}
	
	private void updateAfterIncreaseOrDecrease(ResourceType resource) {
        getDiscardView().setResourceDiscardAmount(resource, discardedCards.getResource(resource));
        boolean increase = false;
        boolean decrease = false;
        try {
			if(discardedCards.getResource(resource) < CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getResource(resource)){
				increase = true;
			}
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(discardedCards.getResource(resource) > 0){
        	decrease = true;
        }
        
        getDiscardView().setResourceAmountChangeEnabled(resource, increase, decrease);
	}

	/**
	 * send request to server with a map of resource types and how many of each are discarded
	 */
	@Override
	public void discard() {
        try {
            CatanFacade.getCurrentState().discardCards(discardedCards);
            getDiscardView().closeModal();
        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //getDiscardView().closeModal();

		getDiscardView().closeModal();
	}

}


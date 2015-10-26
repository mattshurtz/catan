package client.discard;

import shared.definitions.*;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;
import shared.model.ResourceList;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.facade.CatanFacade;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
    private ResourceList discardedCards;
    private int totalDiscardAmount;

	
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
		CatanFacade.addObserver( this );
		discardedCards = new ResourceList();
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
        //discardedCards = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources();

		
		IDiscardView view =  (IDiscardView)super.getView();			
		
		
		view.setDiscardButtonEnabled(false);		
		
		return view;
        //return null;
		
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
	
	/**
	 * Updates the buttons and count of a specified resource on the view.
	 * Also updates the total discard count (all resources) 
	 * 
	 * @param resource The resource to update
	 */
	private void updateAfterIncreaseOrDecrease(ResourceType resource) {
		
		//Get discard number and allowed total for this player
		int numToDiscard = discardedCards.getResource(resource);
		int allowedTotal = 0;
		
		try {
			//Decide if increase button should be enabled
			boolean increase = false;
			allowedTotal = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getResource(resource);
			if (numToDiscard < allowedTotal) {
				increase = true;
			}
			
			//Decide if decrease button should be enabled
			boolean decrease = false;
			if (numToDiscard > 0) {
				decrease = true;
			}
			
			//Update the specified resources count, and the increase/decrease buttons
			getDiscardView().setResourceDiscardAmount(resource, discardedCards.getResource(resource));
			getDiscardView().setResourceAmountChangeEnabled(resource, increase, decrease);
			
			//Update the discard message on the button and enable/disable the button
			getDiscardView().setStateMessage(discardedCards.getTotalResources() + "/" + totalDiscardAmount);
			if (discardedCards.getTotalResources() == totalDiscardAmount) {
				getDiscardView().setDiscardButtonEnabled(true);
			} else {
				getDiscardView().setDiscardButtonEnabled(false);
			}
			
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * send request to server with a map of resource types and how many of each are discarded
	 * Reset discard view to 0
	 */
	@Override
	public void discard() {
        try {
            CatanFacade.getCurrentState().discardCards(discardedCards);
            resetDiscardView();
            getDiscardView().closeModal();
        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	@Override
	public void update(Observable o, Object arg) {
		if (CatanFacade.isDiscarding()) {
			startDiscard();
		} //else {
			//if the wait view or discard view are showing, hide them
			//getWaitView().closeModal();
			//getDiscardView().closeModal();
		//}
	}

	public void startDiscard() {
		try {
			//Get current player find out how many cards they need to discard (half of their hand, rounding up)
			Player player = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex());
			int playerResources = player.getResources().getTotalResources();
			if (playerResources > 7) {
				getDiscardView().setResourceMaxAmount(ResourceType.BRICK, player.getResources().getBrick());
				getDiscardView().setResourceMaxAmount(ResourceType.ORE, player.getResources().getOre());
				getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, player.getResources().getSheep());
				getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, player.getResources().getWheat());
				getDiscardView().setResourceMaxAmount(ResourceType.WOOD, player.getResources().getWood());
				
				totalDiscardAmount = (int) Math.ceil(playerResources / 2.0);
				
				updateAllButtons();
			
				getDiscardView().showModal();
			} else {
				getWaitView().setMessage("Waiting for other players to discard");
				getWaitView().showModal();
			}
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void resetDiscardView() {
		discardedCards = new ResourceList();
		
		getDiscardView().setResourceMaxAmount(ResourceType.BRICK, 0);
		getDiscardView().setResourceMaxAmount(ResourceType.ORE, 0);
		getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, 0);
		getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, 0);
		getDiscardView().setResourceMaxAmount(ResourceType.WOOD, 0);
		
		getDiscardView().setDiscardButtonEnabled(false);
		
		getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, false);
		getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, false);
		getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, false);
		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, false);
		getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, false);
		
		totalDiscardAmount = 0;
	}
	
	private void updateAllButtons() {
		updateAfterIncreaseOrDecrease(ResourceType.BRICK);
		updateAfterIncreaseOrDecrease(ResourceType.ORE);
		updateAfterIncreaseOrDecrease(ResourceType.SHEEP);
		updateAfterIncreaseOrDecrease(ResourceType.WHEAT);
		updateAfterIncreaseOrDecrease(ResourceType.WOOD);
	}

}


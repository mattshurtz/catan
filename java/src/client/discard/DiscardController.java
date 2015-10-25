package client.discard;

import shared.definitions.*;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
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
    private int discardAmount = 0;

	
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
        getDiscardView().setStateMessage(discardedCards.getTotalResources() + "/" + discardAmount);
        
        if(discardAmount == discardedCards.getTotalResources()) {
        	getDiscardView().setDiscardButtonEnabled(true);
        } else {
        	getDiscardView().setDiscardButtonEnabled(false);
        }
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
        
		getDiscardView().closeModal();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			discardAmount = (int) Math.ceil(CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getTotalResources() / 2.0);
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			getDiscardView().setResourceMaxAmount(ResourceType.BRICK, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getBrick());
			getDiscardView().setResourceMaxAmount(ResourceType.ORE, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getOre());
			getDiscardView().setResourceMaxAmount(ResourceType.SHEEP, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getSheep());
			getDiscardView().setResourceMaxAmount(ResourceType.WHEAT, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getWheat());
			getDiscardView().setResourceMaxAmount(ResourceType.WOOD, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getWood());
			updateAfterIncreaseOrDecrease(ResourceType.BRICK);
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}


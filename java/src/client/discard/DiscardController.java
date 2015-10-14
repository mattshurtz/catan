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
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {
		
		super(view);
		
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
		try {
			discardedCards = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources();
			return (IDiscardView)super.getView();
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		discardedCards.addResource(resource, 1);
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		discardedCards.subtractResource(resource, 1);
	}

	@Override
	public void discard() {
		try {
			CatanFacade.getCurrentState().discardCards(discardedCards);
			getDiscardView().closeModal();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


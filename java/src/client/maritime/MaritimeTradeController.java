package client.maritime;

import shared.definitions.*;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.ResourceList;
import shared.model.map.Port;

import java.util.ArrayList;

import client.base.*;
import client.facade.CatanFacade;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController {

	private IMaritimeTradeOverlay tradeOverlay;
	
	private ResourceType give;
	private ResourceType get;
	private int ratio;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay) {
		
		super(tradeView);

		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	
	/**
	 *Gets called by the view when the roll dice button is pressed
	 *To Do (not necessarily in this class):
	 *Create and array of possible resource trade options (Resource types of a sufficent amount to trade) 
	 *and pass them into the overlay in .showGiveOptions(resurce[])
	 *disable trade button in view
	 *showModal on tradeOverlay
	 */
	@Override
	public void startTrade() {
		
		if(!CatanFacade.isMyTurn()){
			getTradeOverlay().setStateMessage("Not your turn.");
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().hideGiveOptions();
			getTradeOverlay().hideGetOptions();
			return;
		}
		
		getTradeOverlay().setStateMessage("Trade!");
		getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().hideGiveOptions();
		
		
		enabledResources = new ArrayList<ResourceType>();
		try {
			checkResourcesAndPorts(ResourceType.BRICK);
			checkResourcesAndPorts(ResourceType.ORE);
			checkResourcesAndPorts(ResourceType.SHEEP);
			checkResourcesAndPorts(ResourceType.WHEAT);
			checkResourcesAndPorts(ResourceType.WOOD);
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		getTradeOverlay().showGiveOptions(enabledResources.toArray(new ResourceType[enabledResources.size()]));
		
		
		
		getTradeOverlay().showModal();
	}

	private ArrayList<ResourceType> enabledResources;
	
	
	private boolean checkResourcesAndPorts(ResourceType resource) throws GetPlayerException {		
		int playTotal = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources().getResource(resource);
		int portTotal = CatanFacade.getModel().getMap().neededToOfferMaritimeTrade(CatanFacade.getMyPlayerIndex(), resource);
		if( playTotal >= portTotal){
			enabledResources.add(resource);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Verify there is a resource to give and recieve
	 * Send request to server
	 * closeModal
	 * */
	@Override
	public void makeTrade() {

		try {
			CatanFacade.getCurrentState().maritimeTrade(ratio, give, get);
			getTradeOverlay().closeModal();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * closeModal()
	 */
	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	
	/**
	 * check to make sure bank has the resource
	 * call selectGetOption() in tradeOverlay
	 * enable/disable trade button based on validity 
	 */
	@Override
	public void setGetResource(ResourceType resource) {
		if (CatanFacade.getModel().getBank().hasResource(resource)) {
			this.getTradeOverlay().selectGetOption(resource, 1);
			get = resource;
			getTradeOverlay().setTradeEnabled(true);
		}		
	}

	/**
	 * find out trade ratio for resource to give
	 * call selectGiveOption() in tradeOverlay
	 */
	@Override
	public void setGiveResource(ResourceType resource) {
		give = resource;
		int currentRatio = CatanFacade.getModel().getMap().neededToOfferMaritimeTrade(CatanFacade.getMyPlayerIndex(), resource);
		ResourceType[] allResources = {ResourceType.WOOD, ResourceType.BRICK, ResourceType.SHEEP, 
				ResourceType.WHEAT, ResourceType.ORE};
		ratio = currentRatio;
		getTradeOverlay().selectGiveOption(resource, currentRatio);
		getTradeOverlay().showGetOptions(allResources);
	}

	
	/**
	 * disable trade button
	 * redisplay the trade Options for getting resource
	 */
	@Override
	public void unsetGetValue() {
		getTradeOverlay().setTradeEnabled(false);
		getTradeOverlay().hideGetOptions();
		this.setGiveResource(give);
		//getTradeOverlay().closeModal();
		//startTrade();
	}

	
	/**
	 * disable trade button
	 * redisplay the trade Options for getting resource
	 */
	@Override
	public void unsetGiveValue() {
		getTradeOverlay().closeModal();
		startTrade();
	}

}


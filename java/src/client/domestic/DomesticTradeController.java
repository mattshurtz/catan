package client.domestic;

import shared.definitions.*;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;
import shared.model.ResourceList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.data.PlayerInfo;
import client.facade.CatanFacade;
import client.misc.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	
	private int playerTradeWith;

	private ResourceList status;
	private ResourceList available;
	private ResourceList offered;
	private ResourceList desired;
	

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		
		status = new ResourceList();
		available = new ResourceList();
		offered = new ResourceList();
		desired = new ResourceList();
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
		if(!CatanFacade.isMyTurn())
			return;
		
		getTradeOverlay().setStateMessage("set the trade you want to make");
		getTradeOverlay().setResourceSelectionEnabled(true);
		getTradeOverlay().setPlayerSelectionEnabled(false);
		getTradeOverlay().setTradeEnabled(false);
		
		Player player;
		try {
			player = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex());
			this.available = player.getResources();
		} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		PlayerInfo[] players = CatanFacade.getCurrentGamePlayers();
		ArrayList<PlayerInfo> arrayPlayer = new ArrayList<PlayerInfo>(Arrays.asList(players));
		
		arrayPlayer.remove(arrayPlayer.remove(CatanFacade.getMyPlayerIndex()));
		getTradeOverlay().setPlayers( arrayPlayer.toArray(new PlayerInfo[arrayPlayer.size()]));
		checkResourcesEnabled();
		getTradeOverlay().showModal();
		getTradeOverlay().setPlayerSelectionEnabled(true);
	}

	private void checkResourcesEnabled() {
		boolean decrease = false;	
		getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, this.available.getBrick() > 0, decrease);
		getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, this.available.getOre() > 0, decrease);
		getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, this.available.getSheep() > 0, decrease);
		getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, this.available.getWood() > 0, decrease);
		getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, this.available.getWheat() > 0, decrease);
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		//sending
		if (status.getResource(resource)==1) {
			this.offered.subtractResource(resource, 1);
			this.available.addResource(resource, 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, available.getResource(resource) > 0, offered.getResource(resource) > 0);
		} else { //Receiving
			this.desired.subtractResource(resource, 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, desired.getResource(resource) > 0);
		}
		checkTrade();
		
		
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		//sending
		if (status.getResource(resource)==1) {
			this.offered.addResource(resource, 1);
			this.available.subtractResource(resource, 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, available.getResource(resource) > 0, offered.getResource(resource) > 0);

		} else { //Receiving
			this.desired.addResource(resource, 1);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, desired.getResource(resource) > 0);
		}
		checkTrade();
		
	}

	@Override
	public void sendTradeOffer() {
		try {
			CatanFacade.getCurrentState().offerTrade(offered, playerTradeWith);
			getTradeOverlay().closeModal();
			getTradeOverlay().reset();
			getWaitOverlay().showModal();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		playerTradeWith = playerIndex;
		checkTrade();
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		status.subtractResource(resource, status.getResource(resource));
		status.addResource(resource, 2);
		available.addResource(resource, offered.getResource(resource));
		offered.subtractResource(resource, offered.getResource(resource));
		getTradeOverlay().setResourceAmount(resource, Integer.toString(desired.getResource(resource)));
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, desired.getResource(resource) > 0);
		checkTrade();
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		status.subtractResource(resource, status.getResource(resource));
		status.addResource(resource, 1);
		desired.subtractResource(resource, desired.getResource(resource));
		getTradeOverlay().setResourceAmount(resource, Integer.toString(offered.getResource(resource)));
		getTradeOverlay().setResourceAmountChangeEnabled(resource, available.getResource(resource) > 0, offered.getResource(resource) > 0);
		checkTrade();
	}

	@Override
	public void unsetResource(ResourceType resource) {
		status.addResource(resource, 0);
		available.addResource(resource, offered.getResource(resource));
		offered.subtractResource(resource, offered.getResource(resource));
		getTradeOverlay().setResourceAmount(resource, "0");
		getTradeOverlay().setResourceAmountChangeEnabled(resource, false, false);
		checkTrade();
	}

	@Override
	public void cancelTrade() {
		getTradeOverlay().closeModal();
		getTradeOverlay().reset();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		try {
			CatanFacade.getCurrentState().acceptTrade(willAccept);
			getAcceptOverlay().closeModal();
			getAcceptOverlay().reset();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
	}
	
	private void checkTrade() {
		getTradeOverlay().setTradeEnabled(false);
		if (desired.getTotalResources() == 0 && offered.getTotalResources() == 0) {
			getTradeOverlay().setStateMessage("set the trade you want to make");
			return;
		}
		if (playerTradeWith < 0 || playerTradeWith > 3 || playerTradeWith == CatanFacade.getMyPlayerIndex()) {
			getTradeOverlay().setStateMessage("select a player");
			return;
		}	
		getTradeOverlay().setStateMessage("Trade!");
		getTradeOverlay().setTradeEnabled(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		getTradeOverlay().setPlayers(CatanFacade.getCurrentGamePlayers());
		//getTradeOverlay().
	}

}


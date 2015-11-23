package client.domestic;

import shared.definitions.*;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;
import shared.model.ResourceList;
import shared.model.TradeOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.data.PlayerInfo;
import client.facade.CatanFacade;
import client.facade.StatePlaying;
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
		
		CatanFacade.addObserver(this);
		
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
        
		if(!CatanFacade.isMyTurn() || !(CatanFacade.getCurrentState() instanceof StatePlaying)) {
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().setStateMessage("not your turn or not in playing mode");
			getTradeOverlay().setResourceSelectionEnabled(false);
			getTradeOverlay().setPlayerSelectionEnabled(false);
			getTradeOverlay().showModal();
			return;
		}
		
		status = new ResourceList();
		available = new ResourceList();
		offered = new ResourceList();
		desired = new ResourceList();
		this.playerTradeWith = -1;
		getTradeOverlay().reset();
		
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
		PlayerInfo[] newPlayerArray = arrayPlayer.toArray(new PlayerInfo[arrayPlayer.size()]);
		
		getTradeOverlay().setPlayers(newPlayerArray);
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
		int brick = (this.offered.getBrick() == 0) ? -this.desired.getBrick() : this.offered.getBrick();
		int wood = (this.offered.getWood() == 0) ? -this.desired.getWood() : this.offered.getWood();
		int sheep = (this.offered.getSheep() == 0) ? -this.desired.getSheep() : this.offered.getSheep();
		int wheat = (this.offered.getWheat() == 0) ? -this.desired.getWheat() : this.offered.getWheat();
		int ore = (this.offered.getOre() == 0) ? -this.desired.getOre() : this.offered.getOre();
		ResourceList offer = new ResourceList(brick, wood, sheep, wheat, ore);
		
		
		try {
			CatanFacade.getCurrentState().offerTrade(offer, playerTradeWith);
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
		status.subtractResource(resource, status.getResource(resource));
		available.addResource(resource, offered.getResource(resource));
		offered.subtractResource(resource, offered.getResource(resource));
		desired.subtractResource(resource, desired.getResource(resource));
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
		TradeOffer offer = CatanFacade.getModel().getTradeOffer();
		if(offer != null){
			if(offer.getReceiver()==CatanFacade.getMyPlayerIndex() && !this.getAcceptOverlay().isModalShowing())
			{
				boolean canTrade = false;
				ResourceList resources = offer.getOffer();
				if(resources.getResource(ResourceType.BRICK) > 0)
					getAcceptOverlay().addGetResource(ResourceType.BRICK, resources.getResource(ResourceType.BRICK));
				else if (resources.getResource(ResourceType.BRICK) < 0) {
					getAcceptOverlay().addGiveResource(ResourceType.BRICK, Math.abs(resources.getResource(ResourceType.BRICK)));
				}
				
				if(resources.getResource(ResourceType.ORE) > 0)
					getAcceptOverlay().addGetResource(ResourceType.ORE, resources.getResource(ResourceType.ORE));
				else if (resources.getResource(ResourceType.ORE) < 0) {
					getAcceptOverlay().addGiveResource(ResourceType.ORE, Math.abs(resources.getResource(ResourceType.ORE)));
				}
				
				if(resources.getResource(ResourceType.SHEEP) > 0)
					getAcceptOverlay().addGetResource(ResourceType.SHEEP, resources.getResource(ResourceType.SHEEP));
				else if (resources.getResource(ResourceType.SHEEP) < 0) {
					getAcceptOverlay().addGiveResource(ResourceType.SHEEP, Math.abs(resources.getResource(ResourceType.SHEEP)));
				}
				
				if(resources.getResource(ResourceType.WHEAT) > 0)
					getAcceptOverlay().addGetResource(ResourceType.WHEAT, resources.getResource(ResourceType.WHEAT));
				else if (resources.getResource(ResourceType.WHEAT) < 0) {
					getAcceptOverlay().addGiveResource(ResourceType.WHEAT, Math.abs(resources.getResource(ResourceType.WHEAT)));
				}
				
				if(resources.getResource(ResourceType.WOOD) > 0)
					getAcceptOverlay().addGetResource(ResourceType.WOOD, resources.getResource(ResourceType.WOOD));
				else if (resources.getResource(ResourceType.WOOD) < 0) {
					getAcceptOverlay().addGiveResource(ResourceType.WOOD, Math.abs(resources.getResource(ResourceType.WOOD)));
				}
				
				getAcceptOverlay().setPlayerName(CatanFacade.getCurrentGamePlayers()[offer.getSender()].getName());
				
				getAcceptOverlay().setAcceptEnabled(CatanFacade.getCurrentState().canAcceptTrade(resources));
				getAcceptOverlay().showModal();
			}
		} else {
			if(this.getWaitOverlay().isModalShowing()) {
				this.getWaitOverlay().closeModal();
			}
		}
	}

}


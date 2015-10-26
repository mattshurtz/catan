package client.devcards;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import client.base.*;
import client.facade.CatanFacade;
import client.resources.ResourceBarElement;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		
		}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		try {
			CatanFacade.getCurrentState().buyDevCard();
			getBuyCardView().closeModal();
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void startPlayCard() {
		//SOLDIER, YEAR_OF_PLENTY, MONOPOLY, ROAD_BUILD, MONUMENT
        getPlayCardView().setCardEnabled(DevCardType.SOLDIER, CatanFacade.getCurrentState().canPlaySoldier());
        getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, CatanFacade.getCurrentState().canPlayMonopoly());
        getPlayCardView().setCardEnabled(DevCardType.MONUMENT, CatanFacade.getCurrentState().canPlayMonument());
        getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, CatanFacade.getCurrentState().canPlayRoadBuilding());
        getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, CatanFacade.getCurrentState().canPlayYearOfPlenty());
        
                try {
    System.out.println("Setting Resources...");
            if (CatanFacade.getMyPlayerIndex() > -1 && CatanFacade.getMyPlayerIndex() < 4) {
                getPlayCardView().setCardAmount(DevCardType.SOLDIER, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getTotalSoldiers());
                getPlayCardView().setCardAmount(DevCardType.MONOPOLY, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getTotalMonopoly());
                getPlayCardView().setCardAmount(DevCardType.MONUMENT, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getTotalMonuments()); 
                getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getTotalRoadBuilding());
                getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getTotalYearOfPlenty());
            }
        } catch (GetPlayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
        try {
            CatanFacade.getCurrentState().playMonopoly(resource);
        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
	}

	@Override
	public void playMonumentCard() {
        try {
            CatanFacade.getCurrentState().playMonument();
        } catch (ServerException | GetPlayerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

	@Override
	public void playRoadBuildCard() {
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
        try {
            CatanFacade.getCurrentState().playYearOfPlenty(resource1, resource2);
        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

}


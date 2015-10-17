package client.join;

import client.base.*;
import client.data.PlayerInfo;
import client.facade.CatanFacade;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	
	/**
	 * make a list of players from the current game
	 * getView().setPlayers(that list)
	 * getView().setAIChoices(if you happen to hve AI)
	 * showModal
	 */
	@Override
	public void start() {
        String[] AIs = CatanFacade.getGameHubFacade().listAI();
        getView().setAIChoices(AIs);
        
        PlayerInfo[] playas = CatanFacade.getCurrentGamePlayers();
        getView().setPlayers(playas);

		getView().showModal();
	}

	
	/**
	 * Do whatever you need to do to generate an AI and and it to the player list;
	 */
	@Override
	public void addAI() {
        CatanFacade.getGameHubFacade().addAI( getView().getSelectedAI() );
	}

}


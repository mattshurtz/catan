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

	@Override
	public void start() {
        String[] AIs = CatanFacade.getGameHubFacade().listAI();
        getView().setAIChoices(AIs);
        
        PlayerInfo[] playas = CatanFacade.getCurrentGamePlayers();
        getView().setPlayers(playas);
        
		getView().showModal();
	}

	@Override
	public void addAI() {

	}

}


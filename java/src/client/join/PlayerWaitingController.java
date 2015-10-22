package client.join;

import client.base.*;
import client.data.PlayerInfo;
import client.facade.CatanFacade;
import java.util.Observable;
import java.util.Observer;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {

	public PlayerWaitingController(IPlayerWaitingView view) {
		super(view);
        CatanFacade.addObserver( this );
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
        
        if ( CatanFacade.isWaitingForPlayers() )
            getView().showModal();
        else
            getView().closeModal();
	}

	
	/**
	 * Do whatever you need to do to generate an AI and and it to the player list;
	 */
	@Override
	public void addAI() {
        CatanFacade.getGameHubFacade().addAI( getView().getSelectedAI() );
	}

    @Override
    public void update(Observable o, Object arg) {
        start();
    }

}


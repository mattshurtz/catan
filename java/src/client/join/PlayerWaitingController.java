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
    
    private boolean modalIsUp;

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
	 * getView().setAIChoices(if you happen to have AI)
	 * showModal
	 */
	@Override
	public void start() {
        String[] AIs = CatanFacade.getGameHubFacade().listAI();
        getView().setAIChoices(AIs);
        
        PlayerInfo[] playas = CatanFacade.getCurrentGamePlayers();
        getView().setPlayers(playas);
        
        if ( CatanFacade.isWaitingForPlayers() && !getView().isModalShowing() ) {
            getView().showModal(); // open new one
            //modalIsUp = true;
        } else if ( getView().isModalShowing() ) {
            OverlayView.closeAllModals(); // Close all of them ... a new one opened up each time a new player joined
            //modalIsUp = false;
        } // else do nothing
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


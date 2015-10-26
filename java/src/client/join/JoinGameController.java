package client.join;

import shared.definitions.CatanColor;
import client.base.*;
import client.data.*;
import client.facade.CatanFacade;
import client.misc.*;
import java.util.Observable;
import java.util.Observer;
import shared.communication.params.CreateGameRequest;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, Observer {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
    
    private GameInfo currentGame;
	
	/**
	 * JoinGameController constructor
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {
		super(view);
		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
        CatanFacade.addObserver(this);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		this.messageView = messageView;
	}

	/**
	 * get list of games from server, save them into your pre-game model
	 * JoinGameView().setGames(list of games, your player info)
	 * showModal
	 */
	@Override
	public void start() {
		GameInfo[] games = CatanFacade.getGameHubFacade().listGames();
        getJoinGameView().setGames(games, CatanFacade.getMyPlayerInfo() );
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	
	/**
	 * Create a new Game board based on the options in the View(Random or not)
	 * Send create game request to server
	 * update Game List
	 * closeModal
	 */
	@Override
	public void createNewGame() {
        // Get game board options 
        CreateGameRequest cgr = new CreateGameRequest();
        cgr.setName( getNewGameView().getTitle() );
        cgr.setRandomNumbers( getNewGameView().getRandomlyPlaceNumbers() );
        cgr.setRandomPorts( getNewGameView().getUseRandomPorts() );
        cgr.setRandomTiles( getNewGameView().getRandomlyPlaceHexes() );
        // Send create game request to server
        CatanFacade.getGameHubFacade().createGame( cgr );
        // Update game lsit
        GameInfo[] games = CatanFacade.getGameHubFacade().listGames();
        getJoinGameView().setGames(games, CatanFacade.getMyPlayerInfo() );
        // Close modal
		getNewGameView().closeModal();
	}

	
	/**
	 * Iterate through player in GameInfo and 
	 * disable each color that has already been used in ColorSelectView
	 * check if you are already in
	 * if so, call JoinGame with the color you had already picked
	 */
	@Override
	public void startJoinGame(GameInfo game) {
        currentGame = game;
        
        // disable colors used by other payers.
        for(PlayerInfo player : currentGame.getPlayers())
        {
            if(!player.equals(CatanFacade.getMyPlayerInfo()))
            {
                getSelectColorView().setColorEnabled(player.getColor(), false);
            }
        }
        getSelectColorView().showModal();
    }
    
	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}
    
        @Override
    public void update(Observable o, Object arg) {
        if(CatanFacade.isOver()){
            start();
        }
    }

	
	/**
	 * call join game on server
	 */
	@Override
	public void joinGame(CatanColor color) {
		boolean success = CatanFacade.getGameHubFacade().join(currentGame, color.toString());
        if ( success ) {
            System.out.println("Join succeeded!");
            // If join succeeded
            OverlayView.closeAllModals();
            joinAction.execute();
            //this triggered update allows modals to pop up on rejoining games that have been started.
            CatanFacade.triggerUpdate();
        } else {
            System.out.println("Join failed.");
        }
	}
    
    

}


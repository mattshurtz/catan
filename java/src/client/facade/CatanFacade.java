package client.facade;

import client.data.PlayerInfo;
import client.paller.ServerPallTask;
import client.paller.ServerPaller;
import client.proxy.IServerProxy;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.definitions.CatanColor;
import shared.definitions.TurnStatus;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.DevCardList;
import shared.model.Model;
import shared.model.Player;
import shared.model.ResourceList;

/**
 * A singleton facade that gives out the actual facades. Everything in this
 * class is static.
 */
public class CatanFacade {

    // the singleton facades
    private static StateDiscarding discarding;
    private static StateRobbing robbing;
    private static StateRolling rolling;
    private static StateSetup setup;
    private static StatePlaying playing;
    private static StateNotMyTurn notMyTurn;

    // Pointer to one of the concrete states above
    private static StateBase currentState;

    private static GameHubFacade gameHubFacade;
    private static IServerProxy proxy;
    private static Model model;

    private static int myPlayerIndex = -1;
    private static PlayerInfo myPlayerInfo = new PlayerInfo();
    private static PlayerInfo[] currentGamePlayers = null;

    private static Observable observable;

    private static ServerPaller paller;

    public static void setCurrentGamePlayers(PlayerInfo[] players) {
        currentGamePlayers = players;
    }

    public static PlayerInfo[] getCurrentGamePlayers() {
        return currentGamePlayers;
    }

    public static void startServerPalling() {
        paller = new ServerPaller();
        paller.start();
    }

    // Methods to get the current state

    public static boolean isMyTurn() {
        TurnStatus currStatus = model.getTurnTracker().getStatus();
        int currPlayer = model.getTurnTracker().getCurrentTurn();
        
        return ( currStatus != null && currPlayer == myPlayerIndex );
    }
    
    public static boolean isSetup() {
        return ( ! isWaitingForPlayers() ) && ( currentState instanceof StateSetup );
    }
    
    public static boolean isFirstRound() {
        return isSetup() && (getModel().getTurnTracker().getStatus() == TurnStatus.FIRST_ROUND);
    }
    
    public static boolean isSecondRound() {
        return isSetup() && (getModel().getTurnTracker().getStatus() == TurnStatus.SECOND_ROUND);
    }
    
    public static boolean isPlaying() {
        return ( ! isWaitingForPlayers() ) && ( currentState instanceof StatePlaying );
    }
    
    public static boolean isDiscarding() {
        return ( ! isWaitingForPlayers() ) && ( currentState instanceof StateDiscarding );
    }
    
    public static boolean isNotMyTurn() {
        return ( ! isWaitingForPlayers() ) && ( currentState instanceof StateNotMyTurn );
    }
    
    public static boolean isRobbing() {
        return ( ! isWaitingForPlayers() ) && ( currentState instanceof StateRobbing );
    }
    
    public static boolean isRolling() {
        return ( ! isWaitingForPlayers() ) && ( currentState instanceof StateRolling );
    }
    
    public static boolean isWaitingForPlayers() {
        PlayerInfo[] inf = getCurrentGamePlayers();
        if ( inf == null || inf.length < 4 )
            return true;
        for ( PlayerInfo p : inf ) {
            if ( p == null )
                return true;
        }
        return false;
    }

    public static CatanColor getColorFromPlayerName(String source) {
        for ( PlayerInfo p : getCurrentGamePlayers() ) {
            if ( p.getName().equals( source ) )
                return p.getColor();
        }
        return null;
    }
    
    private CatanFacade() {
        // Can't be constructed -- is singleton class
    }

    public static void setup(IServerProxy proxy, Model model) {
        CatanFacade.model = model;
        CatanFacade.proxy = proxy;

        CatanFacade.discarding = new StateDiscarding(proxy, model);
        CatanFacade.rolling = new StateRolling(proxy, model);
        CatanFacade.setup = new StateSetup(proxy, model);
        CatanFacade.robbing = new StateRobbing(proxy, model);
        CatanFacade.playing = new StatePlaying(proxy, model);
        CatanFacade.notMyTurn = new StateNotMyTurn(proxy, model);
        updateCurrentState();

        CatanFacade.gameHubFacade = new GameHubFacade(proxy);

        observable = new Observable() {
            public void notifyObservers() {
                setChanged();
                super.notifyObservers();
            }
        };
    }

    /**
     * Update this.currentState pointer to the correct one corresponding to the
     * turn status in the current model
     */
    private static void updateCurrentState() {
        if (model == null) {
            return;
        }

        TurnStatus currStatus = model.getTurnTracker().getStatus();
        int currPlayer = model.getTurnTracker().getCurrentTurn();

        if (currPlayer != myPlayerIndex || currStatus == null) {
            currentState = notMyTurn;
        } else {
            // It's our turn, so determine which facade to use
            switch (currStatus) {
                case DISCARDING:
                    currentState = discarding;
                    break;

                case FIRST_ROUND:
                case SECOND_ROUND:
                    currentState = setup;
                    break;

                case PLAYING:
                    currentState = playing;
                    break;

                case ROBBING:
                    currentState = robbing;
                    break;

                case ROLLING:
                    currentState = rolling;
                    break;
            }
        }
    }

    public static Model getModel() {
        return model;
    }

    public static void setModel(Model model) {
        CatanFacade.model = model;
        discarding.setModel(model);
        notMyTurn.setModel(model);
        playing.setModel(model);
        robbing.setModel(model);
        rolling.setModel(model);
        setup.setModel(model);
        
        if (myPlayerIndex == -1) {
            setMyPlayerIndex();
        }
        
        updateCurrentState();

        if (model != null) // avoiding NullPointerExceptions
        {
            setCurrentGamePlayers(model.getPlayerInfos());
        }
    }

    public static void setMyPlayerIndex() {
        ArrayList<Player> playas = model.getPlayers();
        // finds by name

        for (int i = 0; i<playas.size();i++) {
            if ( playas.get(i) == null )
                continue;// skip it
            if (playas.get(i).getName().equals(myPlayerInfo.getName())) {
                int index = playas.get(i).getPlayerIndex();
                myPlayerInfo.setPlayerIndex(index);
                int id = playas.get(i).getPlayerID();
                myPlayerInfo.setId(id);
                setMyPlayerIndex(i);
            }
        }
    }

    public static StateBase getCurrentState() {
        return CatanFacade.currentState;
    }

    public static GameHubFacade getGameHubFacade() {
        return gameHubFacade;
    }

    public static int getMyPlayerIndex() {
        return myPlayerIndex;
    }

    public static void setMyPlayerIndex(int myPlayerIndex) {
        CatanFacade.myPlayerIndex = myPlayerIndex;
    }

    public static IServerProxy getProxy() {
        return proxy;
    }

    public static void addObserver(Observer o) {
        observable.addObserver(o);
    }

    public static void notifyObservers() {
        observable.notifyObservers();
    }

    public static PlayerInfo getMyPlayerInfo() {
        return myPlayerInfo;
    }

    public static void setMyPlayerInfo(PlayerInfo myPlayerInfo) {
        CatanFacade.myPlayerInfo = myPlayerInfo;
    }

    public static void updateGameModel() {

        int oldVersion = -1;
        int oldNumPlayers = 0;
        if ( model != null ) {
            oldVersion = model.getVersion();
            oldNumPlayers = model.getPlayerInfos().length;
        }
        
        try {
            model = proxy.getGameModel( oldVersion );
        } catch (ServerException ex) {
            Logger.getLogger(ServerPallTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int newVersion = model.getVersion();
        int newNumPlayers = model.getPlayerInfos().length;
        
        //hackPlayer();
        
        
        if ( oldVersion != newVersion || oldNumPlayers != newNumPlayers ) {
            // Replace old model with new one
            
            setModel(model);
            observable.notifyObservers();
        }
    }
    
    public static void setCurrentStateToPlaying(){
        currentState = playing;
    }
    
    /** 
     * To be called after an unsuccessful server request, so we can reset to back
     * before that request.
     */
    public static void triggerUpdate() {
        observable.notifyObservers();
    }
    
    public static void hackPlayer(){
        try {
            model.getPlayer(0).setResources(new ResourceList(5,5,5,5,5));
            model.getPlayer(0).setOldDevCards(new DevCardList(1,1,1,1,1));
        } catch (GetPlayerException ex) {
            Logger.getLogger(CatanFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

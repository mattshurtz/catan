package client.facade;

import client.proxy.IServerProxy;
import java.util.Observable;
import java.util.Observer;
import shared.definitions.TurnStatus;
import shared.model.Model;

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
    
    private static int myPlayerIndex = 0;
    
    private static Observable observable;
    
    private CatanFacade() {
        // Can't be constructed -- is singleton class
    }
    
    public static void setup( IServerProxy proxy, Model model ) {
        CatanFacade.model = model;
        CatanFacade.proxy = proxy;
        
        CatanFacade.discarding = new StateDiscarding( proxy, model );
        CatanFacade.rolling = new StateRolling( proxy, model );
        CatanFacade.setup = new StateSetup( proxy, model );
        CatanFacade.robbing = new StateRobbing( proxy, model );
        CatanFacade.playing = new StatePlaying(proxy, model);
        CatanFacade.notMyTurn = new StateNotMyTurn(proxy, model);
        updateCurrentState();
        
        CatanFacade.gameHubFacade = new GameHubFacade(proxy);
        
        observable = new Observable();
    }
    
    /** 
     * Update this.currentState pointer to the correct one corresponding
     * to the turn status in the current model
     */
    private static void updateCurrentState() {
        if ( model == null )
            return;
     
        TurnStatus currStatus = model.getTurnTracker().getStatus();
        int currPlayer = model.getTurnTracker().getCurrentTurn();
        
        if ( currPlayer != myPlayerIndex )
            currentState = notMyTurn;
        else {
            // It's our turn, so determine which facade to use
            switch ( currStatus ) {
                case DISCARDING:
                    currentState = discarding;
                    break;
                    
                case FIRST_ROUND:
                case SECOUND_ROUND:
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
        discarding.setModel( model );
        notMyTurn.setModel( model );
        playing.setModel( model );
        robbing.setModel( model );
        rolling.setModel( model );
        setup.setModel( model );
        updateCurrentState();
    }
    
    public static StateBase getCurrentState() {
        return CatanFacade.currentState;
    }
    
    public static GameHubFacade getGameHubFacade() {
        return gameHubFacade;
    }
    
//    public static boolean isServer() {
//        return isServer;
//    }
//
//    public static void setIsServer(boolean isServer) {
//        CatanFacade.isServer = isServer;
//    }

    public static int getMyPlayerIndex() {
        return myPlayerIndex;
    }

    public static void setMyPlayerIndex(int myPlayerIndex) {
        CatanFacade.myPlayerIndex = myPlayerIndex;
    }

    public static IServerProxy getProxy() {
        return proxy;
    }
    
    public static void addObserver( Observer o ) {
        observable.addObserver(o);
    }
    
    public static void notifyObservers() {
        observable.notifyObservers();
    }
}

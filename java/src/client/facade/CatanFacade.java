package client.facade;

import client.proxy.IServerProxy;
import shared.model.Model;

/**
 * A singleton facade that gives out the actual facades. Everything in this
 * class is static.
 */
public class CatanFacade {
    
    // the singleton facades
    private static CanDoFacadeMyTurn myTurn;
    private static CanDoFacadeNotMyTurn notMyTurn;
    private static DoFacade doFacade;
    private static GameHubFacade gameHubFacade;
    private static IServerProxy proxy;
    private static Model model;
    
    private static boolean isServer = false;
    private static int myPlayerIndex = 0;
    
    private CatanFacade() {
        // Can't be constructed -- is singleton class
    }
    
    public static void setup( IServerProxy proxy, Model model ) {
        CatanFacade.model = model;
        CatanFacade.proxy = proxy;
        CatanFacade.myTurn = new CanDoFacadeMyTurn(proxy, model);
        CatanFacade.notMyTurn = new CanDoFacadeNotMyTurn(proxy, model);
        CatanFacade.doFacade = new DoFacade(proxy, model);
        CatanFacade.gameHubFacade = new GameHubFacade(proxy, model);
    }
    
    private static boolean isMyTurn() {
        return ( isServer || model.getTurnTracker().isPlayersTurn( myPlayerIndex ) );
    }

    public static Model getModel() {
        return model;
    }

    public static void setModel(Model model) {
        CatanFacade.model = model;
    }
    
    public static CanDoFacade getCanDoFacade() {
        if ( isMyTurn() )
            return myTurn;
        else
            return notMyTurn;
    }
    
    public static DoFacade getDoFacade() {
        return doFacade;
    }
    
    public static GameHubFacade getGameHubFacade() {
        return gameHubFacade;
    }
    
    public static boolean isIsServer() {
        return isServer;
    }

    public static void setIsServer(boolean isServer) {
        CatanFacade.isServer = isServer;
    }

    public static int getMyPlayerIndex() {
        return myPlayerIndex;
    }

    public static void setMyPlayerIndex(int myPlayerIndex) {
        CatanFacade.myPlayerIndex = myPlayerIndex;
    }

    public static CanDoFacadeMyTurn getMyTurn() {
        return myTurn;
    }

    public static CanDoFacadeNotMyTurn getNotMyTurn() {
        return notMyTurn;
    }

    public static IServerProxy getProxy() {
        return proxy;
    }
    
    
}

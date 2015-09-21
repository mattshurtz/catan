package client.facade;

/**
 *
 * @author JanPaul
 */
public class CatanFacade {
    
    // the singleton facades
    private static CanDoFacadeMyTurn myTurn;
    private static CanDoFacadeNotMyTurn notMyTurn;
    private static DoFacade doFacade;
    private static GameHubFacade gameHubFacade;
    
    private static boolean isMyTurn = false;
    
    public static CanDoFacade getCanDoFacade() {
        if ( isMyTurn )
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
}

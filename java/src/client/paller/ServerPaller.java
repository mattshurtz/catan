package client.paller;

import client.facade.CatanFacade;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class to poll the server for changes, pull the changed data, and take care of 
 * any other p*lling operations (including peeling, piling, paling, pearling, prowling, 
 * praline, and, most importantly, palling).
 *
 * This class is intended to run as a separate thread from the rest of the client and will
 * independently poll the server for new data, pull it in if possible, and replace the old
 * data model with a new one.
 *
 * @author Jan Bergeson
 * @since 17 Sept 2015
 */
public class ServerPaller extends Thread {
    
    /**
     * The paller delay between palling in milliseconds (1/2 second = 500ms)
     */
    public static final int PALL_DELAY = 500;
    
    private final Timer timer = new Timer();
    
    private int timesPalled = 0;

    /**
     * This method is what is run by the thread and will kick off a timer that will go
     * off every half second or so. Every time this timer goes off it will trigger a
     * TimerTask event that will poll the server for new data.
     */
    @Override
    public void run() {
        ServerPallTask task = new ServerPallTask( CatanFacade.getProxy(), CatanFacade.getModel() );
        task.setParent( this );
        //System.out.println("About to start palling ...");
        timer.schedule( task, PALL_DELAY, PALL_DELAY );
    }
    
    public void endGame(){
        timer.cancel();
        timer.purge();
    }
    
    public int getTimesPalled() {
        return timesPalled;
    }
    
    public void incrementTimesPalled() {
        timesPalled++;
    }

}
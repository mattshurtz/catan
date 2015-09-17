package client.paller;

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

	private Timer timer;

	/**
	 * This method is what is run by the thread and will kick off a timer that will go
	 * off every half second or so. Every time this timer goes off it will trigger a
	 * TimerTask event that will poll the server for new data.
	 */
	@Override
	public void run() {
		// Start the timer, schedule the task
	}

}
package client.paller;

import java.net.Proxy;
import java.util.TimerTask;
import shared.model.Model;


/**
 * This is the task that the ServerPaller will execute every half second to ask for new data from
 * the server.
 */
public class ServerPallTask extends TimerTask {
    
        Model model;
        Proxy proxy;
        
        /**
         * This constructor is to support dependency injection so that the Poller can take in a real Proxy or Mock Proxy
         * @param proxy This proxy will either be a mock proxy or real proxy
         * @param model 
         */
        ServerPallTask(Proxy proxy, Model model){
            this.proxy = proxy;
            this.model = model;
        }
      
	/**
	 * Ask the server for new data and update the model.
	 */
	@Override
	public void run() {

	}
}
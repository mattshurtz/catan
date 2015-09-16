/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.ArrayList;
import shared.model.map.Map;


/**
*bank (ResourceList): The cards available to be distributed to the players.,
*chat (MessageList): All the chat messages.,
*log (MessageList): All the log messages.,
*map (Map),
*players (array[Player]),
*tradeOffer (TradeOffer, optional): The current trade offer, if there is one.,
*turnTracker (TurnTracker): This tracks who's turn it is and what action's being done.,
*version (index): The version of the model. This is incremented whenever anyone makes a
*move.,
*winner (index): This is -1 when nobody's won yet. When they have, it's their order index [0-3]
*/
public class Model {
    ResourceList bank;
    MessageList chat;
    MessageList log;
    Map map;
    ArrayList<Player> players;
    TradeOffer tradeOffer;
    TurnTracker turnTracker;
    int version;
    int winner;
            
    public Model(){
      bank = new ResourceList();
      chat = new MessageList();
      log = new MessageList();
      map = new Map();
      players = new ArrayList<Player>();
      tradeOffer = new TradeOffer();
      turnTracker = new TurnTracker();
      version = 0;
      winner = -1;
      
    }

    public Model(String serverModel ){
        //http://stackoverflow.com/questions/16377754/parse-json-file-using-gson
        //Gson gson = new Gson();
        //this = gson.fromJson(serverModel, Facade.class);
    }
    
}

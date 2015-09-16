/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.ArrayList;
import shared.model.map.Map;


/**
 *
 * @author karahartley
 */
public class Facade {
    
    public Facade(){
      ResourceList bank = new ResourceList();
      MessageList chat = new MessageList();
      MessageList log = new MessageList();
      Map map = new Map();
      ArrayList<Player> players = new ArrayList<Player>();
      TradeOffer tradeOffer = new TradeOffer();
      TurnTracker turnTracker = new TurnTracker();
      int version = 0;
      int winner = -1;
      
    }

    public Facade(String serverModel ){
        //http://stackoverflow.com/questions/16377754/parse-json-file-using-gson
        //Gson gson = new Gson();
        //this = gson.fromJson(serverModel, Facade.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.locations.HexLocation;

/**
 * This class is the same request params as the params to play a Soldier.
 * 
 * RobPlayer { type (robPlayer), playerIndex (integer): Who's doing the robbing,
 * victimIndex (integer): The order index of the player to rob, location
 * (HexLocation): the new location of the robber } HexLocation { x (string), y
 * (string) }
 */
public class RobPlayerRequest {
    private int victimIndex;
    private HexLocation location;

    public int getVictimIndex() {
        return victimIndex;
    }

    public void setVictimIndex(int victimIndex) {
        this.victimIndex = victimIndex;
    }

    public HexLocation getLocation() {
        return location;
    }

    public void setLocation(HexLocation location) {
        this.location = location;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.locations.EdgeLocation;

/**
 * String type "buildRoad"
 * int playerIndex 
 * EdgeValue roadLocation
 * boolean free
 */
public class BuildRoadRequest extends MoveRequest {
   
    private EdgeLocation roadLocation;
    private boolean free;

    public EdgeLocation getRoadLocation() {
        return roadLocation;
    }

    public void setRoadLocation(EdgeLocation roadLocation) {
        this.roadLocation = roadLocation;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
    
}

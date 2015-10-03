/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.definitions.ResourceType;

/**
 *
 * Year_of_Plenty_ { type (Year_of_Plenty), playerIndex (integer): Who's playing
 * this dev card, resource1 (Resource), resource2 (Resource) }
 */
public class PlayYearOfPlentyRequest extends MoveRequest {
    private ResourceType resource1;
    private ResourceType resource2;
    
    public PlayYearOfPlentyRequest(ResourceType resource1, ResourceType resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }
    
    public PlayYearOfPlentyRequest(int playerIndex, ResourceType resource1, ResourceType resource2) {
        this.setType("Year_of_Plenty");
        this.setPlayerIndex(playerIndex);
    	this.resource1 = resource1;
        this.resource2 = resource2;
    }
    
    public ResourceType getResource1() {
        return resource1;
    }

    public void setResource1(ResourceType resource1) {
        this.resource1 = resource1;
    }

    public ResourceType getResource2() {
        return resource2;
    }

    public void setResource2(ResourceType resource2) {
        this.resource2 = resource2;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.definitions.ResourceType;

/**
 */
public class ResourceList {
    int brick;
    int ore;
    int sheep;
    int wheat;
    int wood;    
    
    
     /** 
     * @param ResourceList accept is the list of resource necessary to accept the trade. 
     * @return true if player has enough resources to accept the trade
     */
    public boolean canAcceptTrade( ResourceList accept) {
    	return false;
    }
    
    /** 
     * @param ResourceList offer is the resources to be offered in trade. 
     * @return true if the player has the resources to be offered;
     */
    public boolean canOfferTade(ResourceList offer){
        return false;
    }
    
    /**
     * Use to add a resource to the resource list(trade, draw)
     * @param resourceType this is the type of resource to be incremented. 
     */
    public void addResource(ResourceType resourceType){
        
    }
    
    /**
     * Use to subtract a resource from the resource list. 
     * @param resourceType this is the type of resource to be decremented.
     */
    public void subResource(ResourceType resourceType){
        
    }
}

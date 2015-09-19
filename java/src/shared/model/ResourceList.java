/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

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
}

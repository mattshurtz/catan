/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.definitions.ResourceType;
import shared.locations.VertexLocation;

/**
 */
public class ResourceList {

    private int brick;
    private int ore;
    private int sheep;
    private int wheat;
    private int wood;

    public ResourceList() {
            this.brick = 0;
            this.wood = 0;
            this.sheep = 0;
            this.wheat = 0;
            this.ore = 0;
	}

    public ResourceList(int brick, int wood, int sheep, int wheat, int ore) {
            this.brick = brick;
            this.wood = wood;
            this.sheep = sheep;
            this.wheat = wheat;
            this.ore = ore;
    }

	/**
     * @param  accept is the list of resource necessary to accept
     * the trade.
     * @return true if player has enough resources to accept the trade
     */
    public boolean canAcceptTrade(ResourceList accept) {
      
         
        if(brick>=accept.brick && sheep>=accept.sheep && ore >= accept.ore 
                && wood>=accept.wood && wheat>=accept.wheat){
            return true;
        }

        return false;
    }

    /**
     * @param offer is the resources to be offered in trade.
     * @return true if the player has the resources to be offered;
     */
    public boolean canOfferResource(ResourceType resourceType, int amount) {
        
        switch(resourceType){
            case BRICK:
                if(brick>=amount){
                    return true;
                }
                break;
            case WHEAT:
                if(wheat>=amount){
                    return true;
                }
                break;
            case SHEEP:
                if(sheep>=amount){
                    return true;
                }
                break;
            case ORE:
                if(ore>=amount){
                    return true;
                }
                break;
            case WOOD:
                if(wood>=amount){
                    return true;
                }
                break;
             }  
        
        return false;
        
    }
  

    /**
     * @return true if player has enough resources to buy a road
     */
    public boolean canBuyRoad() {
        if (wood > 0 && brick > 0) {
            return true;
        }
        return false;
    }

    /**
     * subtracts one brick and one wood from the resourceList;
     */
    public void buyRoad() {
        brick--;
        wood--;
    }

    /**
     * @return true if player has enough resources to buy a Settlement
     */
    public boolean canBuySettlement() {
        if(wood>0 && brick>0 && wheat>0 && sheep>0){
            return true;
        }
        return false;
    }

    /**
     * @return true if player has the resources to buy a development card
     */
    public boolean canBuyDevCard() {
        if(ore > 0 && sheep > 0 && wheat > 0) {
            return true;
        }
        
        return false;
    }
    
    /**
     * subtracts one brick, wood, sheep, and wheat from the resourceList;
     */
    public void buySettlement() {
        brick--;
        wood--;
        sheep--;
        wheat--; 
    }

    /**
     * @return true if player has enough resources to buy a City
     */
    public boolean canBuyCity() {
       if(ore>2 && wheat>1){
           return true;
       }
       return false;
    }

    /**
     * subtracts three ore and two wheat from ResourceList
     */
    public void buyCity() {
        ore = ore - 3;
        wheat = wheat -2;
    }

    /**
     * Use to add a resource to the resource list(trade, draw)
     *
     * @param resourceType this is the type of resource to be incremented.
     */
    public void addResource(ResourceType resourceType, int numberToAdd) {
        switch(resourceType){
            case BRICK:
                brick+= numberToAdd;
                break;
            case WHEAT:
                wheat+=numberToAdd;
                break;
            case SHEEP:
                sheep+=numberToAdd;
                break;
            case ORE:
                ore+=numberToAdd;
                break;
            case WOOD:
                wood+=numberToAdd;
                break;
             }  
    }

    /**
     * Use to subtract a resource from the resource list.
     *
     * @param resourceType this is the type of resource to be decremented.
     */
    public void subtractResource(ResourceType resourceType,int amount) {
        switch(resourceType){
            case BRICK:
                brick-= amount;
                break;
            case WHEAT:
                wheat-=amount;
                break;
            case SHEEP:
                sheep-=amount;
                break;
            case ORE:
                ore-=amount;
                break;
            case WOOD:
                wood-=amount;
                break;
             } 
    }
    
    public boolean canMarritimeTrade(){
        
        
        return false;
    }

    /**
     * @param resource - the resource to be considered for distribution.
     * if need amount is greater than in the bank, then not allowed.
     *
     */
    public boolean canDistribute(ResourceType resource) {
    	return false;
    }

    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getTotalResources() {
        return brick + ore + sheep + wheat + wood;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.brick;
        hash = 47 * hash + this.ore;
        hash = 47 * hash + this.sheep;
        hash = 47 * hash + this.wheat;
        hash = 47 * hash + this.wood;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResourceList other = (ResourceList) obj;
        if (this.brick != other.brick) {
            return false;
        }
        if (this.ore != other.ore) {
            return false;
        }
        if (this.sheep != other.sheep) {
            return false;
        }
        if (this.wheat != other.wheat) {
            return false;
        }
        if (this.wood != other.wood) {
            return false;
        }
        return true;
    } 
}

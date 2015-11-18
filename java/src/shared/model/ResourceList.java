/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.ArrayList;
import java.util.Random;
import shared.definitions.ResourceType;

/**
 */
public class ResourceList {

    static final int MAX_RESOURCE = 19;
    
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
        ResourceList request = new ResourceList();
        if(accept.brick<0){
            request.addResource(ResourceType.BRICK, Math.abs(accept.brick));
        }
        if(accept.sheep<0){
            request.addResource(ResourceType.SHEEP, Math.abs(accept.sheep));
        }
        if(accept.ore<0){
            request.addResource(ResourceType.ORE, Math.abs(accept.ore));
        }             
        if(accept.wood<0){
            request.addResource(ResourceType.WOOD, Math.abs(accept.wood));
        }        
        if(accept.wheat<0){
            request.addResource(ResourceType.WHEAT, Math.abs(accept.wheat));
        } 
        
        if(this.hasResources(request)){
            return true;
        }

        return false;
    }
    
/**
 * Checks if the player has the resources they are trying to offer. 
 * @param resourceType
 * @param amount
 * @return 
 */
    public boolean hasAmountOfResource(ResourceType resourceType, int amount) {
        
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
    
    public void buyDevCard(){
        wheat--;
        ore--;
        sheep--;
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
    
    public boolean hasResources(ResourceList offer){
        if(this.hasAmountOfResource(ResourceType.WHEAT, offer.getWheat())&&
                this.hasAmountOfResource(ResourceType.BRICK, offer.getBrick())&&
                this.hasAmountOfResource(ResourceType.ORE, offer.getOre())&&
                this.hasAmountOfResource(ResourceType.WOOD, offer.getWood())&&
                this.hasAmountOfResource(ResourceType.SHEEP, offer.getSheep())){
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
    
    public void discardResources(ResourceList discard){
        subtractResource(ResourceType.BRICK,discard.brick);
        subtractResource(ResourceType.WHEAT,discard.wheat);
        subtractResource(ResourceType.ORE,discard.ore);
        subtractResource(ResourceType.WOOD,discard.wood);
        subtractResource(ResourceType.SHEEP,discard.sheep);
    }
    
    public void addResources(ResourceList add){
        addResource(ResourceType.BRICK,add.brick);
        addResource(ResourceType.WHEAT,add.wheat);
        addResource(ResourceType.ORE,add.ore);
        addResource(ResourceType.WOOD,add.wood);
        addResource(ResourceType.SHEEP,add.sheep);
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
    
    public boolean hasResource(ResourceType resourceType) {
        switch(resourceType) {
            case WOOD:
                if(wood > 0) {
                    return true;
                }
                break;  
            case WHEAT:
                if(wheat > 0) {
                    return true;
                }
                break;
            case ORE:
                if(ore > 0) {
                    return true;
                }
                break;
            case BRICK:
                if(brick > 0) {
                    return true;
                }
                break;
            case SHEEP:
                if(sheep > 0) {
                    return true;
                }
                break;
        }
        
        return false;
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

	public int getResource(ResourceType resource) {
		switch (resource){
			case BRICK:
				return getBrick();
			case ORE:
				return getOre();
			case SHEEP:
				return getSheep();
			case WHEAT:
				return getWheat();
			case WOOD:
				return getWood();
			default:
				return -1;
		}
	}
    
    public void initializeBank(){
        this.brick = MAX_RESOURCE;
        this.ore = MAX_RESOURCE;
        this.sheep = MAX_RESOURCE;
        this.wheat = MAX_RESOURCE;
        this.wood = MAX_RESOURCE;
    }
    
    public ResourceType robResource(){
        ArrayList<ResourceType> robbableResources = new ArrayList<ResourceType>();
        addToRobbableResouces(ResourceType.BRICK, robbableResources);
        addToRobbableResouces(ResourceType.WHEAT, robbableResources);
        addToRobbableResouces(ResourceType.ORE, robbableResources);
        addToRobbableResouces(ResourceType.WOOD, robbableResources);
        addToRobbableResouces(ResourceType.SHEEP, robbableResources);
        
        Random rand = new Random();
        int randomNum = rand.nextInt( robbableResources.size() );
        
        ResourceType robbed = robbableResources.get( randomNum );
        this.subtractResource(robbed, 1);
        return robbed;
    }
    
    public void addToRobbableResouces(ResourceType resource, ArrayList<ResourceType> robbableResources){
        for(int i = 0; i<getResource(resource); i++){
            robbableResources.add(resource);
        }
    }

    public void payForCity() {
        ore += 3;
        wheat += 2;
    }

    public void payForRoad() {
        brick++;
        wood++;
    }

    public void payForSettlement() {
        brick++;
        wood++;
        sheep++;
        wheat++;        
    }
    
    public void payForDevCard() {
        wheat++;
        ore++;
        sheep++;              
    }
}

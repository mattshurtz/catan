/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.io.Serializable;
import java.util.Random;
import shared.definitions.DevCardType;
import shared.exceptions.InsufficientSupplies;

/**
 * Container for development cards
 * 
 */
public class DevCardList implements Serializable{
    
    static final int MAX_YEAROFPLENTY = 2;
    static final int MAX_MONOPOLY = 2;
    static final int MAX_ROADBUILDING = 2;
    static final int MAX_SOLDIER = 14;
    static final int MAX_MONUMENT = 5;
    
	// 2 monopoly, 2 yearOfPlenty, 2 roadBuilding, 14 soldier, 5 monument     
    int yearOfPlenty;
    int monopoly;
    int soldier;
    int roadBuilding;
    int monument;

    //Default constructor - for players (not deck)
    public DevCardList(){
    	yearOfPlenty = 0;
    	monopoly = 0;
    	soldier = 0;
    	roadBuilding = 0;
    	monument = 0;
    }
    
    

	public DevCardList(int yearOfPlenty, int monopoly, int soldier, int roadBuilding, int monument) {
		super();
		this.yearOfPlenty = yearOfPlenty;
		this.monopoly = monopoly;
		this.soldier = soldier;
		this.roadBuilding = roadBuilding;
		this.monument = monument;
	}

        public void initializeDeck()
        {
            this.yearOfPlenty = MAX_YEAROFPLENTY;
            this.monopoly = MAX_MONOPOLY;
            this.soldier = MAX_SOLDIER;
            this.roadBuilding = MAX_ROADBUILDING;
            this.monument = MAX_MONUMENT;            
        }

	public int getMonopoly() {
		return monopoly;
	}

	/**
	 * Add one monopoly card to list
	 */
	public void AddMonopoly() {
		this.monopoly++;
	}	
	
	/**
	 * If there is at least one monopoly card, removes it from list.
	 * @throws InsufficientSupplies if there are no monopoly cards.
	 */
	public void removeMonopoly() {
		if(monopoly > 0)
            this.monopoly--;
	}

	public int getMonument() {
		return monument;
	}

	/**
	 * Add one monument card to list
	 */
	public void AddMonument() {
		this.monument++;
	}
	

	public int getRoadBuilding() {
		return roadBuilding;
	}

	/**
	 * Add one road building card to list
	 */
	public void AddRoadBuilding() {
		this.roadBuilding++;
	}
	
	/**
	 * If there is at least one road building card, removes it from list.
	 * @throws InsufficientSupplies if there are no road building cards.
	 */
	public void removeRoadBuilding() {
		if(roadBuilding > 0)
            this.roadBuilding--;
	}
    
        public void removeMonument() {
        	if(monument > 0)
        		this.monument--;
        }

        public int getSoldier() {
                return soldier;
        }

	/**
	 * Add one soldier card to list
	 */
	public void AddSoldier() {
		this.soldier++;
	}
	
	/**
	 * If there is at least one soldier card, removes it from list.
	 * @throws InsufficientSupplies if there are no soldier cards.
	 */
	public void removeSoldier() {
		if(this.soldier > 0)
            this.soldier--;
	}

	public int getYearOfPlenty() {
		return yearOfPlenty;
	}

	/**
	 * Add one year of plenty card to list
	 */
	public void AddYearOfPlenty() {
		this.yearOfPlenty++;
	}
	
	/**
	 * If there is at least one year of plenty card, removes it from list.
	 * @throws InsufficientSupplies if there are no year of plenty cards.
	 */
	public void removeYearOfPlenty() {
		if(this.yearOfPlenty > 0)
            this.yearOfPlenty--;
	}
	
        /**
         * Checks if there is at least one of the specified DevCardType to play.
         * 
         * @param cardType the type of DevCard to be played.
         * @return true if at least one card of cardType
         */
        public boolean canPlayDevCard(DevCardType cardType) {
            switch(cardType) {
                case SOLDIER:
                    if(soldier > 0){
                        return true;
                    }
                    break;
                case YEAR_OF_PLENTY:
                    if(yearOfPlenty > 0){
                        return true;
                    }
                    break;
                case MONOPOLY:
                    if(monopoly > 0){
                        return true;
                    }
                    break;
                case ROAD_BUILD:
                    if(roadBuilding > 0){
                        return true;
                    }
                    break;
                case MONUMENT:
                    if(monument > 0){
                        return true;
                    }
                    break;
            }
            
            return false;
        }
        
        
	/**
	 * Checks if there is at least one monopoly card to play. It does not check other playing restraints.
	 * @return true if there is at least one monopoly card to play
	 */
	public boolean canPlayMonopoly() {
		if(monopoly > 0) 
			return true;
		return false;
	}
	
	/**
	 * Checks if there is at least one road building card to play. It does not check other playing restraints.
	 * @return true if there is at least one road building card to play
	 */
	public boolean canPlayRoadBuilding() {
		if(roadBuilding > 0) 
			return true;
		return false;
	}
	
	/**
	 * Checks if there is at least one soldier card to play. It does not check other playing restraints.
	 * @return true if there is at least one soldier card to play
	 */
	public boolean canPlaySoldier() {
		if(soldier > 0) 
			return true;
		return false;
	}
	
	/**
	 * Checks if there is at least one year of plenty card to play. It does not check other playing restraints.
	 * @return true if there is at least one year of plenty card to play
	 */
	public boolean canPlayYearOfPlenty() {
		if(yearOfPlenty > 0) 
			return true;
		return false;
	}

    public void setMonopoly(int monopoly) {
        this.monopoly = monopoly;
    }

    public void setMonument(int monument) {
        this.monument = monument;
    }

    public void setRoadBuilding(int roadBuilding) {
        this.roadBuilding = roadBuilding;
    }

    public void setSoldier(int soldier) {
        this.soldier = soldier;
    }

    public void setYearOfPlenty(int yearOfPlenty) {
        this.yearOfPlenty = yearOfPlenty;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.yearOfPlenty;
        hash = 79 * hash + this.monopoly;
        hash = 79 * hash + this.soldier;
        hash = 79 * hash + this.roadBuilding;
        hash = 79 * hash + this.monument;
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
        final DevCardList other = (DevCardList) obj;
        if (this.yearOfPlenty != other.yearOfPlenty) {
            return false;
        }
        if (this.monopoly != other.monopoly) {
            return false;
        }
        if (this.soldier != other.soldier) {
            return false;
        }
        if (this.roadBuilding != other.roadBuilding) {
            return false;
        }
        if (this.monument != other.monument) {
            return false;
        }
        return true;
    }
    
    public int totalCards() {
        return monopoly + monument + roadBuilding + soldier + yearOfPlenty;
    }
    
    DevCardType pickDevCard() {
        Random rand = new Random();
        int pick = rand.nextInt(totalCards());
        
        return determinePick(pick);
    }
    
    private DevCardType determinePick(int pickNum) {
        DevCardType choice = null;
        
        if(pickNum < monopoly)
        {
            choice = DevCardType.MONOPOLY;
            removeMonopoly();
        }
        else if(pickNum < monopoly + monument)
        {
            choice = DevCardType.MONUMENT;
            removeMonument();
        }
        else if(pickNum < monopoly + monument + roadBuilding)
        {
            choice = DevCardType.ROAD_BUILD;
            removeRoadBuilding();
        }
        else if(pickNum < monopoly + monument + roadBuilding + soldier)
        {
            choice = DevCardType.SOLDIER;
            removeSoldier();
        }
        else if(pickNum < monopoly + monument + roadBuilding + soldier + yearOfPlenty)
        {
            choice = DevCardType.YEAR_OF_PLENTY;
            removeYearOfPlenty();
        }
        
        return choice;
    }

    void addDevCard(DevCardType purchased) {
        switch(purchased)
        {
            case MONOPOLY: {
                AddMonopoly();
                break;
            }
            case MONUMENT: {
                AddMonument();
                break;
            }
            case SOLDIER: {
                AddSoldier();
                break;
            }
            case ROAD_BUILD: {
                AddRoadBuilding();
                break;
            }
            case YEAR_OF_PLENTY: {
                AddYearOfPlenty();
                break;
            }
            default: {
               //System.out.println("INVALID DEV CARD TYPE.");
               break;
            }
        }
    }
}

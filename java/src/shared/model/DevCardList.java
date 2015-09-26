/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.exceptions.InsufficentSupplies;

/**
 * Container for development cards
 * 
 */
public class DevCardList {
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
	 * @throws InsufficentSupplies if there are no monopoly cards.
	 */
	public void removeMonopoly() throws InsufficentSupplies {
		if (monopoly > 0) {
			this.monopoly--;
		} else {
			throw new InsufficentSupplies("Player does not have a Monopoly card to play");
		}		
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
	 * @throws InsufficentSupplies if there are no road building cards.
	 */
	public void removeRoadBuilding() throws InsufficentSupplies {
		if (roadBuilding > 0) {
			this.roadBuilding--;
		} else {
			throw new InsufficentSupplies("Player does not have a Road Building card to play");
		}		
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
	 * @throws InsufficentSupplies if there are no soldier cards.
	 */
	public void removeSoldier() throws InsufficentSupplies {
		if (soldier > 0) {
			this.soldier--;
		} else {
			throw new InsufficentSupplies("Player does not have a Road Building card to play");
		}		
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
	 * @throws InsufficentSupplies if there are no year of plenty cards.
	 */
	public void removeYearOfPlenty() throws InsufficentSupplies {
		if (yearOfPlenty > 0) {
			this.yearOfPlenty--;
		} else {
			throw new InsufficentSupplies("Player does not have a Year of Plenty card to play");
		}		
	}
	
	/**
	 * Checks if there is at least one monopoly card to play. It does not check other playing restraints.
	 * @return true if there is at least one monopoly card to play
	 */
	public boolean canPlayMonopoly() {
		return false;
	}
	
	/**
	 * Checks if there is at least one road building card to play. It does not check other playing restraints.
	 * @return true if there is at least one road building card to play
	 */
	public boolean canPlayRoadBuilding() {
		return false;
	}
	
	/**
	 * Checks if there is at least one soldier card to play. It does not check other playing restraints.
	 * @return true if there is at least one soldier card to play
	 */
	public boolean canPlaySoldier() {
		return false;
	}
	
	/**
	 * Checks if there is at least one year of plenty card to play. It does not check other playing restraints.
	 * @return true if there is at least one year of plenty card to play
	 */
	public boolean canPlayYearOfPlenty() {
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.Objects;
import shared.definitions.TurnStatus;

/**
*currentTurn (index): Who's turn it is (0-3),
*status (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or
*'SecondRound']: What's happening now,
*longestRoad (index): The index of who has the longest road, -1 if no one has it
*largestArmy (index): The index of who has the biggest army (3 or more), -1 if no one has it
*/
public class TurnTracker {
    private int currentTurn;
    private TurnStatus status;
    private int longestRoad;
    private int largestArmy;
    
    //Default empty constructor
    public TurnTracker() {
    	currentTurn = 0;
    	//Default to rolling
    	status = TurnStatus.ROLLING;
    	longestRoad = 0;
    	largestArmy = 0;
    }

    //Constructor
    public TurnTracker(int currentTurn, TurnStatus status, int longestRoad, int largestArmy) {
            this.currentTurn = currentTurn;
            this.status = status;
            this.longestRoad = longestRoad;
            this.largestArmy = largestArmy;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public TurnStatus getStatus() {
            return status;
    }

    public void setStatus(TurnStatus status) {
            this.status = status;
    }

    public int getLongestRoad() {
            return longestRoad;
    }

    public void setLongestRoad(int longestRoad) {
            this.longestRoad = longestRoad;
    }

    public int getLargestArmy() {
            return largestArmy;
    }

    public void setLargestArmy(int largestArmy) {
            this.largestArmy = largestArmy;
    }

    public boolean isPlayersTurn(int myPlayerIndex) {
        return (currentTurn == myPlayerIndex);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.currentTurn;
        hash = 47 * hash + Objects.hashCode(this.status);
        hash = 47 * hash + this.longestRoad;
        hash = 47 * hash + this.largestArmy;
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
        final TurnTracker other = (TurnTracker) obj;
        if (this.currentTurn != other.currentTurn) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (this.longestRoad != other.longestRoad) {
            return false;
        }
        if (this.largestArmy != other.largestArmy) {
            return false;
        }
        return true;
    }
    
}

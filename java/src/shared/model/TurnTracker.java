/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.definitions.TurnStatus;

/**
*currentTurn (index): Who's turn it is (0-3),
*status (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or
*'SecondRound']: What's happening now,
*longestRoad (index): The index of who has the longest road, -1 if no one has it
*largestArmy (index): The index of who has the biggest army (3 or more), -1 if no one has it
*/
public class TurnTracker {
    int currentTurn;
    TurnStatus status;
    int longestRoad;
    int largestArmy;
    
    public TurnTracker() {
		super();
		// TODO Auto-generated constructor stub
	}

    public TurnTracker(int currentTurn, TurnStatus status, int longestRoad, int largestArmy) {
            super();
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
    
    

}

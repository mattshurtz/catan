/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

/**
*currentTurn (index): Who's turn it is (0-3),
*status (string) = ['Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or
*'SecondRound']: What's happening now,
*longestRoad (index): The index of who has the longest road, -1 if no one has it
*largestArmy (index): The index of who has the biggest army (3 or more), -1 if no one has it
*/
public class TurnTracker {
   int currentTurn;
   String status;
   int longestRoad;
   int largestArmy;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

/**
 *
 * RollNumber { type (rollNumber), playerIndex (integer): Who's sending this
 * command (0-3), number (integer): what number was rolled (2-12) }
 */
public class RollNumberRequest extends MoveRequest {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

/**
 *AcceptTrade {
 *type (acceptTrade),
 *playerIndex (integer): Who's accepting / rejecting this trade,
 *willAccept (boolean): Whether you accept the trade or not
 *}
 */
public class AcceptTradeRequest extends MoveRequest {
    private boolean willAccept;

    public AcceptTradeRequest(boolean willAccept) {
        this.willAccept = willAccept;
    }
    
    public boolean isWillAccept() {
        return willAccept;
    }

    public void setWillAccept(boolean willAccept) {
        this.willAccept = willAccept;
    }
    
    
}

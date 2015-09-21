/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.responses;

/**
 * Wrapper object containing a list of games currently being played on the server.  This includes game title, ID, and an array of Players (color, name, player ID).
 * This object comes back from the IServerProxy.getGamesList() method.
 */
public class GetGameListResponse {
    //A list of GameFromJSON objects, each containing the game's title, id, and a list of current players
}

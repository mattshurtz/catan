package server.commands.game;

import server.commands.Command;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class listAI extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        return "[]";
    }
    
}

package server.commands.game;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

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

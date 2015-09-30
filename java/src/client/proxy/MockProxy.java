package client.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shared.communication.params.*;
import shared.communication.params.moves.AcceptTradeRequest;
import shared.communication.params.moves.BuildCityRequest;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.communication.params.moves.MaritimeTradeRequest;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.OfferTradeRequest;
import shared.communication.params.moves.PlayMonopolyRequest;
import shared.communication.params.moves.PlayRoadBuildingRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.RobPlayerRequest;
import shared.communication.params.moves.RollNumberRequest;
import shared.communication.params.moves.SendChatRequest;
import shared.communication.responses.*;
import shared.exceptions.ServerException;
import shared.json.Deserializer;
import shared.model.Model;
import shared.model.User;

public class MockProxy implements IServerProxy {

    public MockProxy(){
        
    }
    
    @Override
    public String[] listAi() throws ServerException {
        return new String[] { "LARGEST_ARMY" };
    }

    @Override
    public Model postCommands(PostCommandsRequest postCommandsRequest) throws ServerException {
        try {
            return new Deserializer().getTestModel();
        } catch (IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getCommands() throws ServerException {
        return "buildRoad, and things";
    }

    @Override
    public boolean addAi( AddAiRequest req ) throws ServerException {
        if(req.getAIType()=="LARGEST_ARMY")
        	return true;
        return false;
    }

    @Override
    public Model buildRoad( BuildRoadRequest req ) throws ServerException {
        try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model offerTrade( OfferTradeRequest req ) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model acceptTrade( AcceptTradeRequest req ) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model maritimeTrade( MaritimeTradeRequest req ) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model buyDevCard(MoveRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model playYearOfPlenty(PlayYearOfPlentyRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model playRoadBuilding(PlayRoadBuildingRequest req) throws ServerException {
        try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model playSoldier(MoveRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model playMonopoly(PlayMonopolyRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model buildSettlement(BuildSettlementRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
   }

    @Override
    public Model buildCity(BuildCityRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model sendChat(SendChatRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model robPlayer(RobPlayerRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model finishTurn(MoveRequest req) throws ServerException {
    	try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean login(Credentials userCredentials) throws ServerException {
        if ( userCredentials == null ) 
            return false;

        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        if ( username == null || password == null )
            return false;
        
        return true;
        
//        boolean ret = username.matches("(Sam|Brooke|Pete|Mark|Ken|Squall|Scott)");
//        ret = ret && username.equalsIgnoreCase(password);
//        return ret;
    }

    @Override
    public boolean register(Credentials userCredentials) throws ServerException {
        if ( userCredentials == null ) 
            return false;

        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();
        
        boolean ret = User.isValidUsername( username );
        ret = ret && User.isValidPassword( password );
        
        return ret;
    }

    @Override
    public List<GameResponse> getGamesList() throws ServerException {
        List<GameResponse> list = new ArrayList<>();
        list.add( GameResponse.getDefaultSampleGameResponse() );
        return list;
    }

    @Override
    public CreateGameResponse createGame(CreateGameRequest gameRequests) throws ServerException {
        if ( gameRequests == null )
            return null;
        
        CreateGameResponse response = new CreateGameResponse();
        response.setTitle( gameRequests.getName() );
        response.setId(5);
        List<EmptyPlayerResponse> epr = new ArrayList<>();
        for (int i = 0; i < 4; i++ )
            epr.add( new EmptyPlayerResponse() );
        response.setPlayers(epr);
        
        return response;
    }

    @Override
    public boolean joinGame(JoinGameRequest joinRequest) throws ServerException {
        return true;
    }

    @Override
    public boolean saveGame(SaveGameRequest saveRequest) throws ServerException {
        return true;
    }

    @Override
    public boolean loadGame(LoadGameRequest loadRequest) throws ServerException {
        return true;
    }

    @Override
    public Model getGameModel(int version) throws ServerException {
        try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Model resetGame() throws ServerException {
        try {
            return new Deserializer().getTestModel();
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

	@Override
	public Model rollNumber(RollNumberRequest req) throws ServerException {
		// TODO Auto-generated method stub
		if(req.getPlayerIndex() > 3 || req.getPlayerIndex() < 0)
			throw new ServerException("Invalid PlayerIndex");
		if(req.getNumber() > 12 || req.getNumber() < 2)
			throw new ServerException("Invalid Number");
		return null;
	}


}

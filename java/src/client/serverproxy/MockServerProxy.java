package client.serverproxy;

import java.util.List;

import shared.communication.params.*;
import shared.communication.responses.*;
import shared.communication.responses.model.*;
import shared.exceptions.ServerException;

public class MockServerProxy implements IServerProxy {

	@Override
	public boolean login(Credentials userCredentials) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(Credentials userCredentials) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<GameResponse> getGamesList() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewGameResponse createGame(CreateGameRequest gameRequests) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean joinGame(JoinGameRequest joinRequest) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveGame(SaveGameRequest saveRequest) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loadGame(LoadGameRequest loadRequest) throws ServerException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModelResponse getGameModel(int version) throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelResponse resetGame() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

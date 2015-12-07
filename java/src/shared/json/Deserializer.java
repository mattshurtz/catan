package shared.json;

import client.data.PlayerInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import server.data.PlayerInfoCookie;

import shared.communication.params.Credentials;
import shared.communication.params.SerializableCommand;
import shared.communication.params.moves.BuildRoadRequest;

import shared.communication.responses.CreateGameResponse;
import shared.communication.responses.GameResponse;
import shared.communication.responses.PlayerResponse;
import shared.definitions.CatanColor;
import shared.model.MessageLine;
import shared.model.Model;
import shared.model.Player;

/**
 * The Deserializer is used to receive the Json representation of the model and
 * set the client side model to the Json model.
 */
public class Deserializer {
    
    private Gson gson;
    
    public Deserializer() {
        gson = new GsonBuilder().create();
    }

    /**
     * @param json this will be the Json representation of the model returned
     * from the server.
     * @return 
     * @return a new Model class representation of the current model on the
     * server.
     */
    public Object toClass(Class classType, String json) {
    	if(json==null || classType==null) {
    		return null;
    	}
    	
    	return gson.fromJson(json, classType);
    }
    
    public Object toClass(Type type, String json) {
        return gson.fromJson(json, type);
    }
    
    public Credentials toCredentials(String json) {
    	if( json == null ) {
    		return null;
    	}
    	
        return gson.fromJson(json, Credentials.class);
    }
    
    public PlayerInfoCookie toPlayerInfoCookie(String json) {
    	if( json == null ) {
    		return null;
    	}
    	
        return gson.fromJson(json, PlayerInfoCookie.class);
    }
    
    public Model toJavaModel(String json) {
    	if( json == null ) {
    		return null;
    	}
    	
        return gson.fromJson(json, Model.class);
    }

    public String[] toStringArray(String json) {
    	if(json == null) {
    		return null;
    	}
        return gson.fromJson(json, String[].class);
    }
    
    public CreateGameResponse toGameResponse(String json) {
    	if(json == null) {
    		return null;
    	}
    	
        return gson.fromJson(json, CreateGameResponse.class);
    }
    
    /**
     * We added this for our phase 3 command class for building roads. 
     * @param json
     * @return 
     */
    public BuildRoadRequest toBuildRoadRequest(String json) {
    	if(json == null) {
    		return null;
    	}
        return gson.fromJson(json, BuildRoadRequest.class);
    }
    
    /**
     * @param json this will be the Json representation of the message returned
     * from the server.
     * @return a new Model representation of the message line on the
     * server.
     */
    public MessageLine toJavaMessage(String json) {
        return gson.fromJson(json, MessageLine.class);
    }

    public List<GameResponse> toGamesList(String response) {
        GameResponse[] array = gson.fromJson( response, GameResponse[].class);
        List<GameResponse> list = Arrays.asList( array );
        return list;
    }
    
    /**
     * Returns a sample deserialized Model from some sample JSON
     * @return
     * @throws IOException
     * @throws FileNotFoundException 
     */
    public Model getTestModel() throws IOException, FileNotFoundException {
        File file = new File("java/test/shared/json/sample_model_json.txt");
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String sampleModelJson = new String(data, "UTF-8");
        
        Deserializer instance = new Deserializer();
        Model result = instance.toJavaModel( sampleModelJson );
        return result;
    }
    
    public PlayerInfo toPlayerInfo( PlayerResponse pr ) {
        if ( pr == null )
            return null;
        
        PlayerInfo pi = new PlayerInfo();
        pi.setColor( CatanColor.fromString( pr.getColor() ) );
        pi.setId(pr.getId());
        pi.setName(pr.getName());
        
        return pi;
    }
    
    public PlayerInfo toPlayerInfo( Player p ) {
        if ( p == null )
            return null;
        
        PlayerInfo pi = new PlayerInfo();
        pi.setColor( p.getColor() );
        pi.setId( p.getPlayerID() );
        pi.setName( p.getName() );
        pi.setPlayerIndex(p.getPlayerIndex());
        return pi;
    }

    public String decodeURIComponent(String s) {
        if (s == null)
        {
            return null;
        }

        String result = null;

        try
        {
            result = URLDecoder.decode(s, "UTF-8");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e)
        {
            result = s;  
        }

        return result;
    }
}

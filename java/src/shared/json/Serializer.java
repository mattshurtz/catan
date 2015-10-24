package shared.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 */
public class Serializer {
    
    private GsonBuilder gsonBuilder;
    private Gson gson;
    
    public Serializer() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(VertexLocation.class, new VertexLocationSerializer());
        gsonBuilder.registerTypeAdapter(EdgeLocation.class, new EdgeLocationSerializer());
        gson = gsonBuilder.create();
    }
    
    /**
     * @param o The Java object to be sent to the server. 
     * @return the json representation of package class
     */
    public String toJson(Object o){
        String json = gson.toJson(o);
//        System.out.println( "Returning json: " + json );
        return json;
    }
    
}

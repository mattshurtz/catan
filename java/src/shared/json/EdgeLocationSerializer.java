package shared.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 *
 * @author JanPaul
 */
public class EdgeLocationSerializer implements JsonSerializer<EdgeLocation> {

    @Override
    public JsonElement serialize(EdgeLocation t, Type type, JsonSerializationContext jsc) {
        JsonObject ret = new JsonObject();
        
        JsonPrimitive xVal = new JsonPrimitive( t.getHexLoc().getX() );
        ret.add( "x", xVal );
        
        JsonPrimitive yVal = new JsonPrimitive( t.getHexLoc().getY() );
        ret.add( "y", yVal );
        
        JsonPrimitive direction = new JsonPrimitive( t.getDir().toString() );
        ret.add( "direction", direction );
        
        return ret;
    }
    
}

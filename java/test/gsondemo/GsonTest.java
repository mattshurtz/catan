package gsondemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.fail;
import org.junit.Test;
import shared.model.Player;
import shared.model.TurnTracker;


/**
 * A test demonstrating how to use 
 * @author janpaul
 */
public class GsonTest {
	
//    @Test
//    public void gsonDemo() {
//        try (FileReader in = new FileReader( "java/test/gsondemo/credentials1.json" ) )
//        {
//            Gson gson = new GsonBuilder().create();
//            Credentials cred = gson.fromJson( in, Credentials.class );
//            System.out.println( cred.toString() );
//        } catch (IOException ex) {
//            Logger.getLogger(GsonTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    @Test
//    public void testObjWithList() {
//        String json = "{ \"id\" : \"5\", \"strings\" : [ \"string1\", \"string2\" ] }";
//        Gson gson = new GsonBuilder().create();
//        ObjectWithList owl = gson.fromJson( json, ObjectWithList.class );
//        System.out.println( owl.toString() );
//    }
//    
//    @Test
//    public void testObjWithListOfObjects() {
//        String json = "{\n" +
//                    "\"name\" : \"hello there!\",\n" +
//                    "\"list\" : [\n" +
//                    "	{ \"id\" : \"5\", \"strings\" : [ \"string1\", \"string2\" ] },\n" +
//                    "	{ \"id\" : \"6\", \"strings\" : [ \"string3\", \"string4\" ] },\n" +
//                    "	{ \"id\" : \"7\", \"strings\" : [ \"string5\", \"string6\" ] },\n" +
//                    "	{ \"id\" : \"8\", \"strings\" : [ \"string7\", \"string8\" ] },\n" +
//                    "	{ \"id\" : \"9\", \"strings\" : [ \"string9\", \"string10\" ] },\n" +
//                    "	{ \"id\" : \"0\", \"strings\" : [ \"string11\", \"string12\" ] }\n" +
//                    "	] \n" +
//                    "}";
//        Gson gson = new GsonBuilder().create();
//        ObjectWithListOfObjects owlol = gson.fromJson( json, ObjectWithListOfObjects.class );
//        System.out.println( owlol.toString() );
//    }
    
    @Test
    public void testImportPlayer() {
        Player p;
        try (FileReader in = new FileReader( "test/gsondemo/sample-player.json" ) )
        {
            Gson gson = new GsonBuilder().create();
            p = gson.fromJson( in, Player.class );
            System.out.println("Made it.");
        } catch (IOException ex) {
            Logger.getLogger(GsonTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
    
    @Test
    public void testImportTurnTracker() {
        TurnTracker t;
        try (FileReader in = new FileReader( "test/gsondemo/sample-turntracker.json" ) )
        {
            Gson gson = new GsonBuilder().create();
            t = gson.fromJson( in, TurnTracker.class );
            System.out.println("Made it.");
        } catch (IOException ex) {
            Logger.getLogger(GsonTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }
}

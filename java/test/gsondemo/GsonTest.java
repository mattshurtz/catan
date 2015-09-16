package gsondemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import shared.communication.Credentials;

/**
 * A test demonstrating how to use 
 * @author janpaul
 */
public class GsonTest {
	
	@Test
	public void gsonDemo() {
		try (FileReader in = new FileReader( "java/test/gsondemo/credentials1.json" ) )
		{
			Gson gson = new GsonBuilder().create();
			Credentials cred = gson.fromJson( in, Credentials.class );
			System.out.println( cred.toString() );
		} catch (IOException ex) {
			Logger.getLogger(GsonTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

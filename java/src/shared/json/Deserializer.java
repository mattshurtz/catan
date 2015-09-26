package shared.json;

import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import shared.model.MessageLine;
import shared.model.Model;

/**
 * The Deserializer is used to receive the Json representation of the model and
 * set the client side model to the Json model.
 */
public class Deserializer {

    /**
     * @param json this will be the Json representation of the model returned
     * from the server.
     * @return a new Model class representation of the current model on the
     * server.
     */
    public Model toJavaModel(String json) {
        return new GsonBuilder().create().fromJson(json, Model.class);
    }

    /**
     * @param json this will be the Json representation of the message returned
     * from the server.
     * @return a new Model representation of the message line on the
     * server.
     */
    public MessageLine toJavaMessage(String json) {
        return new MessageLine();
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
        
        System.out.println("toJavaModel");
        Deserializer instance = new Deserializer();
        Model result = instance.toJavaModel( sampleModelJson );
        return result;
    }
}

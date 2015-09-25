package shared.json;

import com.google.gson.GsonBuilder;
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
}

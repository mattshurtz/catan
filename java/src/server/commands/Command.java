/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands;

import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.Model;

/**
 *
 */
public class Command {


    
    private Model model;
    private Deserializer deserializer;
    private Serializer serializer;
    
    
    public void execute(String json){
        
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
        public Deserializer getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(Deserializer deserializer) {
        this.deserializer = deserializer;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }
    
}

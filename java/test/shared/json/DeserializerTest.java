/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.json;

import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shared.model.MessageLine;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class DeserializerTest {
    
    public DeserializerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toJavaModel method, of class Deserializer.
     */
    @Test
    public void testToJavaModel() throws IOException, FileNotFoundException {
        Model result = new Deserializer().getTestModel();
        System.out.println( new GsonBuilder().create().toJson( result ));
    }
    
    public Model getTestModel() throws IOException, FileNotFoundException {
        File file = new File("java/test/shared/json/testModel");
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


    /**
     * Test of toJavaMessage method, of class Deserializer.
     */
    @Test
    public void testToJavaMessage() {
        System.out.println("toJavaMessage");
        String json = "";
        Deserializer instance = new Deserializer();
        MessageLine expResult = null;
        MessageLine result = instance.toJavaMessage(json);
        assertEquals(expResult, result);
        
    }
    
}
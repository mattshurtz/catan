/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsondemo;

import java.util.List;

/**
 *
 * @author JanPaul
 */
public class ObjectWithList {
    int id;
    List<String> strings;

    @Override
    public String toString() {
        String strings_str = "";
        for ( String s : strings ){
            strings_str += s + ",";
        }
        return "ObjectWithArray{" + "id=" + id + ", strings=" + strings_str + '}';
    }
    
    
    
}

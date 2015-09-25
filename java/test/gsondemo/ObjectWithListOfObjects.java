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
public class ObjectWithListOfObjects {
    String name;
    List<ObjectWithList> list;

    @Override
    public String toString() {
        String list_str = "";
        for ( ObjectWithList owl : list ) {
            list_str += "[" + owl.toString() + "]\n";
        }
        return "ObjectWithListOfObjects{" + "name=" + name + ", list=\n" + list_str + '}';
    }
    
    
}

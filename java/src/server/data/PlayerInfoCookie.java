/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.data;

/**
 *
 * @author JanPaul
 */
public class PlayerInfoCookie {
    private String name, password;
    private int playerID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return playerID;
    }

    public void setId(int id) {
        this.playerID = id;
    }
    
    
}

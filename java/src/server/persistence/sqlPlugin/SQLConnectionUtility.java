/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.persistence.DAO.IConnections;

/**
 *
 */
public class SQLConnectionUtility implements IConnections {

    
    private Connection conn = null;

    
    static final String createCurrentGamesSql = 
            "create table if not exists CurrentGames (\n" +
            "	id INTEGER PRIMARY KEY,\n" +
            "	version int,\n" +
            "	name text,\n" +
            "	player0_id int,\n" +
            "	player1_id int,\n" +
            "	player2_id int,\n" +
            "	player3_id int,\n" +
            "	state blob\n" +
            ");";
    
    static final String createUsersSql = 
            "create table if not exists Users (\n" +
            "	id int,\n" +
            "	username text,\n" +
            "	password text\n" +
            ");";
    
    static final String createCommandsSql = 
            "create table if not exists Commands (\n" +
            "	id INTEGER PRIMARY KEY   AUTOINCREMENT ,\n" +
            "	command text,\n" +
            "	json text,\n" +
            "	player_id int,\n" +
            "	game_id int,\n" +
            "	version int,\n" +
            "   randomValue text"+
            ");";
    
    public SQLConnectionUtility(){
        this.openConnection();
    }
    
    @Override
    public void startTransaction() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void endTransaction() {
    	try {
    		if(conn.getAutoCommit())
            	return;
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rollBack() {
        
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void openConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:persistence/test.db");
            
            // Create CurrentGames table
            createTable();
            
        } catch (Throwable e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }    

    @Override
    public void closeConnection() {

        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    private void createTable(){
        
        try {
            
            Statement s = conn.createStatement();
            s.execute(createCurrentGamesSql);
            s.execute(createUsersSql);
            s.execute(createCommandsSql);
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnectionUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public Connection getConnection(){
        return conn;
    }
    
}

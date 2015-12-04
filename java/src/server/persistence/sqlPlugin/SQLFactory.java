/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.persistence.sqlPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.IFactory;

/**
 *
 */
public class SQLFactory implements IFactory{

    static final String createCurrentGamesSql = 
            "create table if not exists CurrentGames (\n" +
            "	id int,\n" +
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
            "	id int,\n" +
            "	command text,\n" +
            "	json text,\n" +
            "	player_id int,\n" +
            "	game_id int,\n" +
            "	version int\n" +
            ");";
    
    private Connection conn = null;
    
    @Override
    public IGamesDAO getGameDAO() {
        return new SQLGamesDAO( this.conn );
    }

    @Override
    public IUsersDAO getUserDAO() {
        return new SQLUsersDAO( this.conn );
    }

    @Override
    public void init() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:persistence/test.db");
            
            // Create CurrentGames table
            try ( Statement s = c.createStatement() ) {
                s.execute(createCurrentGamesSql);
                s.execute(createUsersSql);
                s.execute(createCommandsSql);
            }
            
            this.conn = c;
        } catch (Throwable e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    
    public Connection getConnection(){
        return conn;
    }
    
}

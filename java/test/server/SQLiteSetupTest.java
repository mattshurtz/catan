package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author JanPaul
 */
public class SQLiteSetupTest {
    
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
    
    public static void main(String args[]) {
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
            
        } catch (Throwable e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
}

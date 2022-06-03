/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author PC
 */
public class GameDatabase {
    private final DBManager dbmanager;
    private final Connection conn;
    private Statement statement;

    public GameDatabase() {
        dbmanager = new DBManager();
        conn = dbmanager.getConnection();
    }
    
    //Connect to embedded database
    public void connectGameDB() {
        try {
            this.statement = conn.createStatement();
            this.checkExistedTable("PYRAMIDSTATS");
            this.checkExistedTable("REDORBLACKSTATS");
            this.checkExistedTable("OVERALLSTATS");
            this.statement.addBatch("CREATE  TABLE PYRAMIDSTATS  (USERNAME VARCHAR(50),   PYRAMIDSCOMPLETED   INT,   PYRAMIDSFAILED   INT)");
            this.statement.addBatch("CREATE  TABLE REDORBLACKSTATS  (USERNAME VARCHAR(50),   REDORBLACKCORRECT INT)");
            this.statement.addBatch("CREATE  TABLE OVERALLSTATS  (USERNAME VARCHAR(50),   TOTALDRINKS   INT)");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Create record of players current session
    public void createPlayerRecord(Player p){
        try{
            this.statement = conn.createStatement();
            this.statement.addBatch("INSERT INTO REDORBLACKSTATS VALUES (" + "'"+p.getName()+"'" + ", 0)");
            this.statement.addBatch("INSERT INTO PYRAMIDSTATS VALUES (" + "'"+p.getName()+"'" + ", 0, 0)");
            this.statement.addBatch("INSERT INTO OVERALLSTATS VALUES (" + "'"+p.getName()+"'" + ", 0)");
            this.statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    //Update player drink stats in database
    public void updateDrinks(Player p){
        String sql = "UPDATE OVERALLSTATS SET TOTALDRINKS = " + p.getDrinks() + " WHERE USER = " + "'" + p.getName() + "'";
        this.dbmanager.updateDB(sql);
    }
    
    //Update player Pyramid stats in database
    public void updatePyramidStats(Player p){
        String sql = "UPDATE PYRAMIDSTATS SET PYRAMIDSCOMPLETED = " + p.getPyramids_completed() + ", PYRAMIDSFAILED = " + p.getPyramid_failed() + " WHERE USER = " + "'" + p.getName() + "'";
        this.dbmanager.updateDB(sql);
    }
    
    //Update player Red or Black stats in database
    public void updateROBStats(Player p){
        String sql = "UPDATE REDORBLACKSTATS SET REDORBLACKCORRECT = " + p.getRedOrBlack_correct_guesses() + " WHERE USER = " + "'" + p.getName() + "'";
        this.dbmanager.updateDB(sql);
    }
    
    //Check if table exists in database
    public void checkExistedTable(String name) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name);
                if (table_name.equalsIgnoreCase(name)) {
                    statement.executeUpdate("Drop table " + name);
                    System.out.println("Table " + name + " has been deleted.");
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Close the connection
    public void closeConnection() {
        this.dbmanager.closeConnections();
    }
    
}

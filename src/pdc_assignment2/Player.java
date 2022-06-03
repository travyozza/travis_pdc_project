/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;

/**
 *
 * @author PC
 */
public class Player {
    private final String name;
    private int drinks;
    private int pyramids_completed;
    private int pyramid_failed;
    private int redOrBlack_correct_guesses;
    private GameDatabase gameDB;

    public Player(String name) {
        this.name = name;
        this.drinks = 0;
        this.pyramids_completed = 0;
        this.pyramid_failed = 0;
        this.redOrBlack_correct_guesses = 0;
        this.gameDB = new GameDatabase();
        this.gameDB.connectGameDB();
        this.gameDB.createPlayerRecord(this);
    }

    public String getName() {
        return this.name;
    }

    public int getDrinks() {
        return this.drinks;
    }

    public void addDrink(){
        this.drinks++;
        gameDB.updateDrinks(this);
    }
    
    public void pyramidComplete(){
        this.pyramids_completed++;
        this.gameDB.updatePyramidStats(this);
    }
    
    public void redOrBlackCorrect(){
        this.redOrBlack_correct_guesses++;
        this.gameDB.updateROBStats(this);
    }
    
    public void pyramidReset(){
        this.pyramid_failed++;
        this.gameDB.updatePyramidStats(this);
    }

    public int getPyramids_completed() {
        return pyramids_completed;
    }

    public int getPyramid_failed() {
        return pyramid_failed;
    }

    public int getRedOrBlack_correct_guesses() {
        return redOrBlack_correct_guesses;
    }
    
    public void endSession(){
        this.gameDB.closeConnection();
    }
        
}

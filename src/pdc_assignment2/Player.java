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

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getDrinks() {
        return this.drinks;
    }

    public void addDrink(){
        this.drinks++;
    }
    
    //For grammar purposes within games
    public String drinkPlural(){
        if (this.getDrinks() == 1)
        {
            return " drink";
        }
        else
        {
            return " drinks";
        }
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;

import java.util.Scanner;

/**
 *
 * @author PC
 */
abstract class Game{
    
   //Starts the game
    public abstract void Play();
    
    //Reads rules of specific game from file and prints to screen.
    public abstract void readRules();
    
    
    //Loads game object to somewhat of a menu interface including rules and allows user to either play the game or exit
    public void Load(){
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        
        this.readRules();
        
        System.out.print("\nWould you like to play? ");
        String input = scan.nextLine();
        
        valid = (input.trim().equalsIgnoreCase("y") || input.trim().equalsIgnoreCase("n") || input.trim().equalsIgnoreCase("yes") || input.trim().equalsIgnoreCase("no"));
        
        while (!valid)
        {
            System.out.print("Invalid Input! Please select Yes or No (y/n) ");
            input = scan.nextLine();
            valid = (input.trim().equalsIgnoreCase("y") || input.trim().equalsIgnoreCase("n") || input.trim().equalsIgnoreCase("yes") || input.trim().equalsIgnoreCase("no"));
        }
        
        if (input.trim().equalsIgnoreCase("y") || input.trim().equalsIgnoreCase("yes"))
        {
            System.out.println();
            this.Play();
        }
        else
        {
            System.out.println("\nMaybe next time!");
            System.exit(0);
        }
    }
    
}

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
public class MainMenu {
    
    //Returns an integer value which will be used to load a game within main
    public int printMenu(Player p){
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        
        System.out.println("\nWelcome " + p.getName() + " to...\n");
        
        String banner = "**********************";
     
        System.out.println(banner + "\nClassic Drinking Games\n" + banner);
        System.out.print("These are just a few of my favourite drinking games! \nGrab a drink and get playing!\n");
        System.out.println("\n1. Play Pyramid");
        System.out.println("2. Play Red or Black");
        System.out.print("\nSelection: ");
        
        char input = scan.next().charAt(0);
        
        valid = (input == '1' || input == '2');
        
        while (!valid)
        {
            System.out.print("Invalid Input! Select a game: ");
            input = scan.next().charAt(0);
            valid = (input == '1' || input == '2');
        }
        
        System.out.println();
        
        return Character.getNumericValue(input);
    
    } 
   
    //Returns a game based on user input from the printMenu method
    public Game menuSelection(int selection, Player p){
        Game g;
        
        if (selection == 1)
        {
            g = new Pyramid(p);
        }
        else
        {
            g = new RedOrBlack(p);
        }
        
        return g;
    }
    
    
}

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
public class RedOrBlack extends Game{
    Player player;
    Deck deck;
    GameFileIO file_io;

    public RedOrBlack(Player player) {
        this.player = player;
        this.deck = new Deck();
    }
    
    //Overrides abstract method from Game class and begins a game of Red or Black
    @Override
    public void Play() {
        Scanner scan = new Scanner(System.in);
        this.file_io = new GameFileIO("RedOrBlack Console"); 
        int round_num = 1;
        int correct = 0;
        
        
        this.file_io.writeToFile("<< " + this.player.getName() + " has started playing Red Or Black >>");
        
        System.out.println("************");
        System.out.println("Red Or Black");
        System.out.println("************\n");
        
        while (!this.deck.getDeck().isEmpty())
        {   
            System.out.println("Cards Remaining: " + this.deck.getDeck().size());
            Card c = this.deck.DrawCard();
            boolean valid = false;
            
            System.out.println("Red or Black? (press x to quit)");
            String input = scan.nextLine();
            
            valid = this.isValid(input);
            
            while(!valid){
                System.out.print("Invalid Input! Try Again: \nRed or Black? ");
                input = scan.nextLine();
                valid = this.isValid(input);
            }
           
            System.out.println();
            
            if (input.trim().equalsIgnoreCase("x")) 
            {
                System.out.println("Thanks for playing " + this.player.getName() + "!");
                System.out.println("Correct Guesses: " + correct + "/" + (round_num - 1));
                System.out.println("You had: " + this.player.getDrinks() + this.player.drinkPlural());
                this.file_io.writeToFile("<< " + this.player.getName() + " exited early >>");
                this.file_io.appendRedOrBlackResults(player, round_num - 1, correct, false);
                System.exit(0);
            }
            else if (c.isRed() && input.equalsIgnoreCase("Red") || c.isRed() && input.trim().equalsIgnoreCase("r")){
                System.out.println("Correct! " + c + " is Red");
                this.file_io.RedOrBlackConsoleUpdate(this.player,c, true);
                correct++;
            }
            else if (c.isBlack() && input.equalsIgnoreCase("Black") || c.isBlack() && input.trim().equalsIgnoreCase("b")){
                System.out.println("Correct! " + c + " is black");
                this.file_io.RedOrBlackConsoleUpdate(this.player,c, true);
                correct++;
            }
            else{
                System.out.println("Incorrect! " + c + " is not " + this.returnColour(input) + "\nTake a drink!");
                this.file_io.RedOrBlackConsoleUpdate(this.player,c, false);
                this.player.addDrink();
            }
            
            System.out.println("Your drinks: " + this.player.getDrinks() + "\n");
            round_num++;
        }
         System.out.println("Thanks for playing " + this.player.getName() + "!");
         System.out.println("Correct Guesses: " + correct + "/" + (round_num - 1));
         System.out.println("You had: " + this.player.getDrinks() + this.player.drinkPlural());
         this.file_io.writeToFile("<< " + this.player.getName() + " completed the deck! >>");
         this.file_io.appendRedOrBlackResults(player, round_num - 1, correct, true);
         this.file_io.pw.close(); 
    }
    
    //Overrides abstract method from game class and prints rules of Red or Black to screen before playing
    @Override
    public void readRules(){
        GameFileIO g = new GameFileIO();
        g.readFile("./resources/RedOrBlackRules.txt");   
    }
    
    //Checks if user input is valid
    public boolean isValid(String input){
        return (input.equalsIgnoreCase("red") || input.equalsIgnoreCase("black") || input.trim().equalsIgnoreCase("r") || input.trim().equalsIgnoreCase("b") || input.trim().equalsIgnoreCase("x"));        
    }
    
    //Returns string value of card colour to screen
    public String returnColour(String input){
        String colour = "";
        if (input.equalsIgnoreCase("red") || input.equalsIgnoreCase("r"))
        {
            colour = "Red";
        }
        if (input.equalsIgnoreCase("black") || input.equalsIgnoreCase("b"))
        {
            colour = "Black";
        }
        
        return colour;
    
    }
    
}

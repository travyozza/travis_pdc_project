/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Pyramid extends Game{
    Player player;
    Deck deck;
    ArrayList<Card> card_pyramid;
    GameFileIO file_io;

    public Pyramid(Player player) {
        this.player = player;
        this.deck = new Deck();
        this.card_pyramid = new ArrayList<>();
        
        for (int i = 0; i < 10; i++)
        {
            card_pyramid.add(deck.DrawCard());
        }
    }
    
    
    //Overrides abstract method from Game class and begins a game of Pyramid
    @Override
    public void Play(){
        Scanner scan = new Scanner(System.in);
        int current_row = 1;
        int attempts = 1;
        String pyramid = this.defaultPyramid();
        boolean won = false;
        
        this.file_io = new GameFileIO("Pyramid Console");
        this.file_io.writeToFile("<< " + this.player.getName() + " has started playing Pyramid >>");
        
        System.out.println("*******");
        System.out.println("Pyramid");
        System.out.println("*******\n");
        
        while (!won && !this.deck.getDeck().isEmpty() && this.card_pyramid.size() == 10){
            boolean valid = false;
            
            System.out.println(pyramid);
            
            System.out.print("Select a card from row " + current_row + ": ");
            char input = scan.next().charAt(0);
            valid = this.isValid(input, current_row);
            
            while(!valid){
                System.out.print("Invalid Input! Try Again: ");
                input = scan.next().charAt(0);
                valid = this.isValid(input, current_row);
            }
            
            if (input == 'x' || input == 'X'){
                System.out.println("\nThanks for playing " + this.player.getName() + "!");
                this.file_io.appendPyramidResults(this.player, attempts, false);
                System.exit(0);
            }
            
            Card c = this.card_pyramid.get(Character.getNumericValue(input));
            
            if (!c.isFace())
            {
                System.out.println("\nYou drew a " + c);
                
                if (current_row != 4){
                    System.out.println("You can move on to row " + (current_row+1) + "!" );
                }
                
                System.out.println();
                pyramid = this.updatePyramid(input, pyramid);
                current_row++;
                attempts++;
            }
            else
            {
                System.out.println("\nOh no! You drew a " + c + "!");
                System.out.println("Take a drink and start again!\n");
                this.player.addDrink();
                System.out.println("");
                
                pyramid = this.defaultPyramid();
                current_row = 1;
            }
            
            this.card_pyramid.set(Character.getNumericValue(input), this.deck.DrawCard());
            
            if (current_row == 5){
            
                won = true;
            }
            
            this.file_io.PyramidConsoleUpdate(this.player,c,current_row);
        }
        
        if (won)
        {
            System.out.println("Congratulations " + this.player.getName() + "! " + "You completed the Pyramid!");
            this.file_io.appendPyramidResults(this.player, attempts, true);
        }
        else
        {
            System.out.println("You could not complete the Pyramid " + this.player.getName() + "! Better luck next time...");
            this.file_io.appendPyramidResults(this.player, attempts, true);
        }

        System.out.println("You had " + this.player.getDrinks() + this.player.drinkPlural());
    }  
    
    @Override
    public void readRules(){
        GameFileIO g = new GameFileIO();
        g.readFile("./resources/PyramidRules.txt");  
    }
    
    //Prints a pyramid of numbers to the screen where each number represents an index within the card_pyramid arraylist
    public String defaultPyramid(){
         String pyramid = "Four:    0\nThree:  1 2\nTwo:   3 4 5\nOne:  6 7 8 9";
         return pyramid;
     
    }
    
    //Updates the pyramid and replaces the users inputted number position with an X if the number does not represent a face card
    public String updatePyramid(char num, String pyramid){
        pyramid = pyramid.replace(num, 'X');
        return pyramid;
    }
    
    //Checks if user input is valid
    public boolean isValid(char input, int current_row){
        if (current_row == 1 && input <= '9' && input >= '6')
        {
            return true;
        }
        if (current_row == 2 && input <= '5' && input >= '3')
        {
            return true;
        }
        if (current_row == 3 && input <= '2' && input >= '1')
        {
            return true;
        }
        if (current_row == 4 && input == '0')
        {
            return true;
        }
        if (input == 'x' || input == 'X')
        {
            return true;
        }
        else
        {
            return false;
        }       
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;

/**
 *
 * @author PC
 */
public class RedOrBlackModel{
    Player player;
    Deck deck;
    Card drawn_card;
    int round_number;
    String result = "";
    

    public RedOrBlackModel(Player player) {
        this.player = player;
        this.deck = new Deck();
        this.round_number = 0;
    }
    
    //Draw card for selection
    public void generateDraw(){
        drawn_card = deck.DrawCard();
        round_number++;
    }
    
    //Check if drawn card is red or black 
    public void getAnswer(String answer){
        if (answer.equals("red") && drawn_card.isRed())
        {
            result = "Correct! " + drawn_card + " is Red";
        }
        else if (answer.equals("black") && drawn_card.isBlack())
        {
            result = "Correct! " + drawn_card + " is Black";
        }
        else
        {
            this.player.addDrink();
            result = "Incorrect! " + drawn_card + " is not " + returnColour(answer) + ". Take a drink!";
        }
    }
    
    //Checks if Red or Black deck is empty
    public boolean isdeckEmpty(){
        return (deck.isEmpty());
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

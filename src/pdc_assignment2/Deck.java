    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author PC
 */
public class Deck{
    
    private ArrayList<Card> deck;
    
    // Inititalizes a deck object by iterating through the Suit and CardRank enums and creating a standard deck of 52
    public Deck() {
        deck = new ArrayList<>();
        
        for (Suit suit : Suit.values()){
    
            for (CardRank rank : CardRank.values())
            {
                this.deck.add(new Card(rank,suit));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    
    //Draws random card from deck. Removed the need for shuffling.
    public Card DrawCard()
    {
        Random rand = new Random();
        int index = rand.nextInt(this.deck.size());
       
        Card drawn = this.deck.get(index);
        this.deck.remove(index);
        
        return drawn;
    }
    
    
    
  
  
  
  
  }


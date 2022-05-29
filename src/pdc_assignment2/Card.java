/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;

/**
 *
 * @author PC
 * 
 * 
 */
public class Card implements Comparable<Card>{
   
    private Suit suit;
    private CardRank rank;

    public Card(CardRank value, Suit suit) {
        this.suit = suit;
        this.rank = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public void setValue(CardRank value) {
        this.rank = value;
    }

    @Override
    public String toString() {
        return this.getRank() + " of " + this.getSuit();
    }
    
    @Override 
    public int compareTo(Card c)
    {
        return this.getSuit().compareTo(c.getSuit());
    }
    
    // Returns true if the card colour is red. Very useful for Red or Black implementation
    public boolean isRed(){
        return (this.getSuit() == Suit.HEARTS || this.getSuit() == Suit.DIAMONDS);    
    }
    
    // Returns true if the card colour is black. Very useful for Red or Black implementation
    public boolean isBlack(){
        return (this.getSuit() == Suit.CLUBS || this.getSuit() == Suit.SPADES);    
    }
    
    // Returns true if card holds a face value. Crucial to thhe Pyramid games implementation
    public boolean isFace(){
        return (this.getRank() == CardRank.ACE || this.getRank() == CardRank.KING || this.getRank() == CardRank.QUEEN || this.getRank() == CardRank.JACK);
    }
}
 

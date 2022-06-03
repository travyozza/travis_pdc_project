/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class PyramidModel{
    Player player;
    Deck deck;
    ArrayList<Card> card_pyramid;
    int current_row;
    Card current_card;
    String result = "";
    boolean complete = false;

    public PyramidModel(Player player) {
        this.player = player;
        this.card_pyramid = new ArrayList<Card>();
        this.deck = new Deck();
        this.current_row  = 1;
        
        for (int i = 0; i < 15; i++)
        {
            card_pyramid.add(deck.DrawCard());
        }
    }
    
    //Redraw new pyramid
    public void reinitializePyramid(){
        this.deck = new Deck();
        this.card_pyramid = new ArrayList<Card>();
        this.current_row = 1;
        for (int i = 0; i < 15; i++)
        {
            this.card_pyramid.add(this.deck.DrawCard());
        }
    
    }
    
    //Select card in pyramid and show
    public void selectCard(int num){
        current_card = card_pyramid.get(num);
        if (!this.card_pyramid.get(num).isFace())
        {
            if (current_row < 5)
            {
                current_row++;
                result = "NOT a face card. Select a card from row " + current_row + "!";
            }
            else
            {
              complete = true;
              result = "";
            }  
        }
        else
        {
            player.addDrink();
            result = "FACE CARD DRAWN! Take a drink and start from the bottom!";
            reinitializePyramid();
        }
    }
    
}

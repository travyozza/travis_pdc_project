/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdc_assignment2;

import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 *
 * @author PC
 */
public class GameFileIO {
    
    public PrintWriter pw; 

    public GameFileIO(){
    }
    
    public GameFileIO(String filename) {
        try{
            this.pw = new PrintWriter(new FileOutputStream("./resources/" + filename  + ".txt"));
        } catch (FileNotFoundException e)
        {
            System.out.println("Error Creating File!");
        }
    }
    
    //Reads and prints file contents to screen based on location which is provided within the methods arguments
    public void readFile(String filelocation){ 
       
        try (BufferedReader br = new BufferedReader(new FileReader(filelocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException e)
        {
            System.out.println("Error reading from file");
        }
        
    }
    
    //Allows writing to a file
    public void writeToFile(String text){    
        this.pw.println(text);
        this.pw.println();
        this.pw.flush();
    }
    
    //Appends the results and statistics to the RedOrBlack History file once a Red or Black game has been completed or exited
    public void appendRedOrBlackResults(Player p, int round_num, int correct, boolean completed_deck){
        PrintWriter pw = null;
        
        try{
            pw = new PrintWriter(new FileOutputStream("./resources/RedOrBlack History.txt", true));
            pw.println("\nPlayer: " + p.getName() + "\nCorrect guesses: " + correct + "/" + round_num + "\nDrinks: " + p.getDrinks() + "\nCompleted deck: " + completed_deck);
            pw.close();
        }catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found.");
        }
    }
    
    //Appends the results and statistics to the Pyramid History file once a Pyramid game has been completed or exited
    public void appendPyramidResults(Player p, int attempts, boolean completed){
        PrintWriter pw = null;
        
        try{
            pw = new PrintWriter(new FileOutputStream("./resources/Pyramid History.txt", true));
            pw.println("\nPlayer: " + p.getName() + "\nAttempts: " + attempts + "\nDrinks: " + p.getDrinks() + "\nCompleted: " + completed);
            pw.close();
        }catch(FileNotFoundException e)
        {
            System.out.println("Error! File not found.");
        }
    }
    
    //Updates the RedOrBlack Console file with console like output of the current or latest game of Red or Black
    public void RedOrBlackConsoleUpdate(Player p, Card c, boolean correct){
        String line = "<< ";
        
        if (!correct){
            line += p.getName() + " drew a " + c + " and must drink! >>";
        }
        else{
            line += p.getName() + " drew a " + c + " and does not need to drink! >>";
        }
        
        this.writeToFile(line);
    }
    
    //Updates the Pyramid Console file with console like output of the current or latest game of Pyramid
    public void PyramidConsoleUpdate(Player p, Card c, int row){
        String line = "<< ";
        
        if (c.isFace() && row != 5)
        {
            line += p.getName() + " has drawn a face card and must start from the bottom! >>";
        }
        if (c.isFace() && row == 4)
        {
            line += p.getName() + " drew a face card from the top and must start from the bottom.How unlucky! >>";
        }
        if (!c.isFace() && row != 5)
        {
            line += p.getName() + " has drawn a " + c + " and can move up the pyramid >>";
        }
        if (!c.isFace() && row == 5)
        {
            line += p.getName() + " has completed the pyramid! Well done! >>";
        }
             
        this.writeToFile(line);
    }
}

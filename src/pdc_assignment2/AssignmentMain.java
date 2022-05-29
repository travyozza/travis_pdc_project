/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pdc_assignment2;
import java.util.*;

/**
 *
 * @author PC
 */
public class AssignmentMain{

    public static void main(String[] args) {
        
        MainMenu menu = new MainMenu();
        Scanner scan_name = new Scanner(System.in);
        Game g;
        Player p;
        
        System.out.print("Welcome! Please enter a username: ");
        String name = scan_name.nextLine();
        p = new Player(name.trim());
        
        int selection = menu.printMenu(p);
        
        g = menu.menuSelection(selection, p);
        g.Load();
        
        
    }
    
}

/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;
import javax.swing.*; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */

public class EscapeRoom{
    
  public static void main(String[] args) 
  {      
    
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?"};
  
    // set up game
    boolean play = true;
    while (play)
    {
      if (game.x >430 && game.y>250){
        game.endGame();
        score += game.endGame();
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
      }
      System.out.print("Enter your movement: ");
      String movement = UserInput.getValidInput(validCommands);
      if (movement.equals("right") || movement.equals("r")) { 
        game.movePlayer(60, 0);
      } 
      else if (movement.equals("left") || movement.equals("l")) { 
        game.movePlayer(-60, 0);
      }
      else if (movement.equals("up") || movement.equals("u")) { 
        game.movePlayer(0, -60);
      }
      else if (movement.equals("down") || movement.equals("d")) { 
          game.movePlayer(0, 60);
      }
      else if (movement.equals("pickup") || movement.equals("p")) { 
        game.pickupPrize();
        score += 5;
      }
      else if (movement.equals("replay")) { 
        game.replay();
      }
      else if (movement.equals("jumpup") || movement.equals("ju")) {
        game.movePlayer(0, -120);
      }
      else if (movement.equals("jumpright") || movement.equals("jr")) { 
        game.movePlayer(120, 0);
      }
      else if (movement.equals("jumpleft") || movement.equals("jl")) {
        game.movePlayer(-120, 0);
      }
      else if (movement.equals("jumpdown") || movement.equals("jd")) { 
        game.movePlayer(0, 120);
      }
      else if (movement.equals("quit") || movement.equals("q")) {
        game.endGame();
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
        play = false;
      }
      else if  (movement.equals("help") || movement.equals("?")) { 
        System.out.println("To use these functions you must type the command in the terminal");
        System.out.println("right or r: move the player 1 square right");
        System.out.println("left or l: move the player 1 square left");
        System.out.println("up or u: move the player 1 square up");
        System.out.println("down or d: move the player 1 square down");
        System.out.println("quit or q: quit the game");
        System.out.println("replay: restart the game");
        System.out.println("pickup or p: pickup the prize");
        System.out.println("jumpup or ju: jumps the player 2 squares up");
        System.out.println("jumpdown or jd: jump the player 2 squares down");
        System.out.println("jumpright or jr: jump the player 2 squares right");
        System.out.println("jumpleft or lf: jump the player 2 squares left");
        System.out.println("help or ?: get a list of valid commands");
      }
      else {
        System.out.println("Invalid input.");
        score -= 10;
      }
      if (game.isTrap(60, 0) || game.isTrap(-60, 0) || game.isTrap(0, 60) || game.isTrap(0, -60)) {
        System.out.print("There's a trap nearby! Do you want to spring it? (yes/no): ");
        String springTrap = UserInput.getValidInput(new String[]{"yes", "no"});
        if (springTrap.equals("yes")) {
            score += game.springTrap(60, 0);
        }
      }
    } 
  }
}

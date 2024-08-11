package game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getGlobal();
    public static Panel gui;
    public static int dealerHand, playerHand, cardCount;
    public static int[] dealerCards;
    public static ArrayList<Integer> playerCards;
    public static boolean betting;
    public static Player player = new Player();
    public static HashMap<Integer, Boolean> deckCards = new HashMap<>();
    public static GamePlay gamePlay = new GamePlay();
    public static HashMap<String, JButton> allButtons = new HashMap<>();
    public static HashMap<Integer, Integer> betChips = new HashMap<>();
    public static Color bgColor = new Color(0, 100, 35);
    public static Chip c1 = new Chip(1,"./images/chips/1.png");
    public static Chip c5 = new Chip(5,"./images/chips/5.png");
    public static Chip c10 = new Chip(10,"./images/chips/10.png");
    public static Chip c25 = new Chip(25,"./images/chips/25.png");
    public static Chip c100 = new Chip(100,"./images/chips/100.png");
    public static Chip c500 = new Chip(500,"./images/chips/500.png");
    public static Chip c1000 = new Chip(1000,"./images/chips/1k.png");
    public static Chip c5000 = new Chip(5000,"./images/chips/5k.png");
    public static Chip c25000 = new Chip(25000,"./images/chips/25k.png");
    public static Chip c100000 = new Chip(100000,"./images/chips/100k.png");


    public static void main(String[] args) {
        // create window
        gui = new Panel();
        gamePlay.run();
    }
}
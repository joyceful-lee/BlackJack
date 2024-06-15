package game;

import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getGlobal();
    public static Panel gui;


    public static void main(String[] args) {
        // create window
        gui = new Panel();
    }
}
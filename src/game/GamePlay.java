package game;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static game.Main.logger;


public class GamePlay {
    public static Player player = Main.player;
    public static int[] dealerCards = Main.dealerCards;
    public static ArrayList<Integer> playerCards = Main.playerCards;
    public static boolean ace;
    public static boolean aceDealer;
    public void run(){
        dealerCards = new int[2];
        playerCards = new ArrayList<>();
        ace = false;
        aceDealer = false;
        player.setBetTotal();
        // pre-
        Main.betting = true;
        setPlayerMoney();
        buildDeck();

        // gamecycle

        Main.cardCount = 0;
        Main.gui.updateChips();


    }

    public static void restart(){
        ace = false;
        aceDealer = false;
        Main.betting = true;
        Arrays.fill(dealerCards, 0);
        playerCards.clear();
        Main.betChips.clear();
        Main.playerHand = 0;
        Main.dealerHand = 0;
        Main.gui.cardReset();
        player.betReset();
        Main.gui.updateBet();
        buildDeck();

        Main.cardCount = 0;
        player.setTotalSum(player.getTotalSum());
        Main.gui.updateChips();
    }

    public static void setPlayerMoney(){
        JFrame temp = new JFrame();
        String result = (String)JOptionPane.showInputDialog(
                temp,
                "How much money do you want to start with?",
                "Money",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Enter value here"
        );
        player.setTotalSum(Integer.parseInt(result));
    }

    public static void buildDeck(){
        HashMap<Integer, Boolean> deckCards = new HashMap<>();
        for(int i = 1; i < 53; i++){
            deckCards.put(i, false);
        }
        Main.deckCards = deckCards;
        logger.info("Build deck done");
    }

    public static void dealDealer(){
        for(int i = 0; i < 2; i++) {
            int index = (int) (Math.random() * 52)+1; // * range + min
            Card temp = checkCards(index);
            dealerCards[i] = temp.index();
            int value = temp.value();
            if (dealerCards[i] <= 4 && !ace) {
                aceDealer = true;
                Main.dealerHand += 11;
            } else if (dealerCards[i] <= 4) {
                Main.dealerHand += 1;
            } else {
                Main.dealerHand += value;
            }
        }
        Main.gui.updateDealer(dealerCards, false);
    }

    public static void dealPlayer(){
        int rand = (int)(Math.random() * 52)+1; // * range + min
        Card temp = checkCards(rand);
        int index = temp.index();
        int value = temp.value();
        playerCards.add(temp.index());
        if(index <= 4 && !ace){
            ace = true;
            Main.playerHand += 11;
        }else if(index <= 4){
            Main.playerHand += 1;
        }else {
            Main.playerHand += value;
        }
        Main.cardCount = Main.gui.updatePlayer(playerCards, Main.cardCount);
    }

    public static Card checkCards(int index){
        boolean exists = Main.deckCards.containsKey(index);
        while(exists){
            index = (int)(Math.random() * 52)+1;
            exists = Main.deckCards.get(index);
        }
        index = (int)(Math.random() * 52);
        if(index > 40){
            return new Card(index, 10);
        }else if(index < 4) {
            return new Card(index, 1);
        }else if(index % 4 == 0) {
            return new Card(index, index/4);
        }else{
            return new Card(index, (int)Math.ceil(index/4.0));
        }
    }

    public static void totalCheck(){
        Main.gui.updateDealer(dealerCards, true);
        JFrame temp = new JFrame();
        System.out.println(Main.playerHand + " " + Main.dealerHand);
        if(Main.playerHand <= 21 && Main.playerHand > Main.dealerHand){
            player.setTotalSum(player.getTotalSum() + (player.getBetTotal()*2));
            if (JOptionPane.showConfirmDialog(
                    temp,
                    "You Won and now have " + player.getTotalSum() + "Dollars! Play Again?",
                    "Congratulations",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                restart();
            } else {
                // no option
                System.exit(0);
            }
        }else if(Main.playerHand <= 21 && Main.playerHand == Main.dealerHand){
            player.setTotalSum(player.getTotalSum());
            if (JOptionPane.showConfirmDialog(
                    temp,
                    "You Tied! Play Again?",
                    "Maybe Next Time",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                restart();
            } else {
                // no option
                System.exit(0);
            }
        }else{
            player.setTotalSum(player.getTotalSum());
            if (JOptionPane.showConfirmDialog(
                    temp,
                    "You Lost! Play Again?",
                    "Maybe Next Time",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                restart();
            } else {
                // no option
                System.exit(0);
            }
        }

        System.out.println(player.getTotalSum() + " " + player.getMoneySum());
    }
}

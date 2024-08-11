package game;

import javax.swing.*;
import java.util.HashMap;
import java.util.logging.Logger;

import static game.Main.player;

public class PanelActions {
    public static Logger logger = Main.logger;
    public void addButtonActions(
            HashMap<String, JButton> allButtons,
            HashMap<String, JButton> playerButtons
            ){
        allButtons.get("1").addActionListener(e -> moneyTracker(1, allButtons));

        allButtons.get("5").addActionListener(e -> moneyTracker(5, allButtons));

        allButtons.get("10").addActionListener(e -> moneyTracker(10, allButtons));

        allButtons.get("25").addActionListener(e -> moneyTracker(25, allButtons));

        allButtons.get("100").addActionListener(e -> moneyTracker(100, allButtons));

        allButtons.get("500").addActionListener(e -> moneyTracker(500, allButtons));

        allButtons.get("1000").addActionListener(e -> moneyTracker(1000, allButtons));

        allButtons.get("5000").addActionListener(e -> moneyTracker(5000, allButtons));

        allButtons.get("25000").addActionListener(e -> moneyTracker(25000, allButtons));

        allButtons.get("100000").addActionListener(e -> moneyTracker(100000, allButtons));

        playerButtons.get("hit").addActionListener(e -> GamePlay.dealPlayer());

        playerButtons.get("stay").addActionListener(e -> GamePlay.totalCheck());

        playerButtons.get("bet").addActionListener(e -> {

            Main.betting = false;
            player.setBet();
            Main.gui.updateBet();
            player.setTotalSum(player.getTotalSum() - player.getBetTotal());
            Main.gui.updateChips();
            GamePlay.dealDealer();
            GamePlay.dealPlayer();
            GamePlay.dealPlayer();
        });


    }

    // subtract amount, add to bet
    private void moneyTracker(int chip, HashMap<String, JButton> allButtons){
        logger.info(chip + " pressed");
        if(Main.betting) {
            if (player.getMoney(chip) > 1) {
                allButtons.get(String.valueOf(chip)).setEnabled(true);
                player.setIndividual(chip);
            } else if (player.getMoney(chip) == 1) {
                allButtons.get(String.valueOf(chip)).setEnabled(false);
                player.setIndividual(chip);
            } else if (player.getMoney(chip) == 0){
                allButtons.get(String.valueOf(chip)).setEnabled(false);
            }
        }else{
            allButtons.get(String.valueOf(chip)).setEnabled(false);
        }
        player.addbetTotal(chip);
        Main.betChips.put(chip, Main.betChips.getOrDefault(chip,0)+1);
    }
}

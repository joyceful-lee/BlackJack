package game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.logging.Logger;
public class Chip {
    public static Logger logger = Main.logger;
    public Player player = Main.player;
    private final int value;
    private final String path;
    private final Color bgColor = Main.bgColor;
    public JButton button = new JButton();
    public JLabel bets = new JLabel();
    private final JLabel amount = new JLabel();
    private final JLabel betAmount = new JLabel();

    public Chip(int value, String path){
        this.value = value;
        this.path = path;
    }
    public int getValue(){
        return this.value;
    }

    public JLabel getImgBet(){
        try {
            this.bets.setIcon(getIcon(this.path));
            this.bets.setOpaque(false);
            this.bets.setBackground(bgColor);
            this.bets.setBorder(null);
            this.bets.setEnabled(player.getBet(value) > 0);
            logger.info("Chip " + this.value + " Done");
        } catch(Exception e) {
            System.out.println("Chips Image Error");
        }
        return this.bets;
    }

    public JButton getImg(){
        try {
            this.button.setIcon(getIcon(this.path));
            this.button.setOpaque(false);
            this.button.setBackground(bgColor);
            this.button.setBorder(null);
            Main.allButtons.put(String.valueOf(value), Main.allButtons.getOrDefault(String.valueOf(value), this.button));
            if(Main.betting) {
                this.button.setEnabled(player.getMoney(this.value) > 0);
            }else{
                this.button.setEnabled(false);
            }
            logger.info("Chip " + this.value + " Done");
        } catch(Exception e) {
            System.out.println("Chips Image Error");
        }
        return this.button;
    }

    public JLabel updateAmount(int num){
        this.amount.setText(String.valueOf(num));
        return this.amount;
    }

    public JLabel updateBetAmount(int num){
        this.betAmount.setText(String.valueOf(num));
        return this.betAmount;
    }

    public ImageIcon getIcon(String path){
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image i = img.getImage();
        Image newimg = i.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }
}

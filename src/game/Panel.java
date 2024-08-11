package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class Panel extends JFrame{
    public static Logger logger = Main.logger;
    private final JPanel North, Center, South;
    private final Color bgColor = Main.bgColor;

    public JPanel money, dealer, player, bet;
    public JLabel dealer1, dealer2;
    public JLabel player1, player2, player3, player4;
    private final HashMap<String, JButton> buttonActions, playerButtons;
    public GridBagConstraints gbc = new GridBagConstraints();

    /*
     * Initialize the GUI Game.Window
     */
    public Panel() {
        buttonActions = new HashMap<>();
        playerButtons = new HashMap<>();

        this.gbc.fill = GridBagConstraints.BOTH;
        JFrame frame = new JFrame("Blackjack");
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        // Add dealer section
        String path = "./images/cards/placeholder.png";
        this.North = new JPanel();
        addNorth(path, path);
        container.add(this.North, BorderLayout.NORTH);

        // Add player section
        this.Center = new JPanel();
        addCenter(path);
        container.add(this.Center, BorderLayout.CENTER);

        // Add play buttons
        this.South = new JPanel();
        addSouth();
        container.add(this.South, BorderLayout.SOUTH);

        frame.add(container);

        PanelActions panelActions = new PanelActions();
        panelActions.addButtonActions(
                this.buttonActions,
                this.playerButtons
        );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        logger.info("Window initialized");

    }

    // edit gridbag constraints
    private GridBagConstraints editGBC(GridBagConstraints gbc, int x, int y){
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    private ImageIcon getIcon(String path, int height) {
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        Image i = img.getImage();
        Image newimg = i.getScaledInstance(100 ,height, Image.SCALE_SMOOTH);
        img = new ImageIcon(newimg);
        return img;
    }

    private JLabel getImage(String path, int height){
        JLabel image = new JLabel();
        try {
            image = new JLabel(getIcon(path, height));
            image.setBorder(new EmptyBorder(10,10,10,10));
            logger.info("Card Image Done");
        } catch(Exception e) {
            System.out.println("Placeholder Image Error");
        }
        return image;
    }

    private JPanel dealerStart(String path1, String path2){
        this.dealer = new JPanel(new GridBagLayout());
        this.dealer.setBackground(bgColor);
        // card placeholder images
        this.dealer1 = getImage(path1, 145);
        this.dealer2 = getImage(path2, 145);
        this.dealer.add(this.dealer1, editGBC(gbc,0,0));
        this.dealer.add(this.dealer2, editGBC(gbc, 1,0));
        return this.dealer;
    }

    private JPanel playerStart(String path){
        this.player = new JPanel(new GridBagLayout());
        this.player.setBackground(bgColor);
        // card placeholder images
        this.player1 = getImage(path, 145);
        this.player2 = getImage(path, 145);
        this.player3 = getImage(path, 145);
        this.player4 = getImage(path, 145);
        this.player.add(this.player1, editGBC(gbc,0,0));
        this.player.add(this.player2, editGBC(gbc, 1,0));
        this.player.add(this.player3, editGBC(gbc, 2,0));
        this.player.add(this.player4, editGBC(gbc, 3,0));
        return this.player;
    }

    public void cardReset(){
        ImageIcon img = getIcon("./images/cards/placeholder.png", 145);
        this.player1.setIcon(img);
        this.player2.setIcon(img);
        this.player3.setIcon(img);
        this.player4.setIcon(img);
        this.dealer1.setIcon(img);
        this.dealer2.setIcon(img);
    }

    public void updateDealer(int[] hand, boolean end){
        try {
            ImageIcon img;
            if(end){
                img = getIcon("./images/cards/" + hand[1] + ".png",  145);
            }else{
                img = getIcon("./images/cards/card back red.png",  145);
            }
            this.dealer1.setIcon(img);
            img = getIcon("./images/cards/" + hand[0] + ".png", 145);
            this.dealer2.setIcon(img);
        } catch(Exception e) {
            System.out.println("Dealer Card Error");
        }
    }

    public int updatePlayer(ArrayList<Integer> hand, int count){
        try {
            ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("./images/cards/" + hand.get(count) + ".png")));
            Image i = img.getImage();
            Image newimg = i.getScaledInstance(100, 145, Image.SCALE_SMOOTH);
            img = new ImageIcon(newimg);
            switch (count) {
                case 0 -> this.player1.setIcon(img);
                case 1 -> this.player2.setIcon(img);
                case 2 -> this.player3.setIcon(img);
                case 3 -> this.player4.setIcon(img);
            }
            count++;
        } catch(Exception e) {
            System.out.println("Player Card Error");
        }
        return count;
    }

    private void chipsStart(){

        this.money.add(Main.c1.getImg(), editGBC(gbc, 1, 0));
        this.money.add(Main.c5.getImg(), editGBC(gbc, 2, 0));
        this.money.add(Main.c10.getImg(), editGBC(gbc, 3, 0));
        this.money.add(Main.c25.getImg(), editGBC(gbc, 4, 0));
        this.money.add(Main.c100.getImg(), editGBC(gbc, 5, 0));
        this.money.add(Main.c500.getImg(), editGBC(gbc, 6, 0));
        this.money.add(Main.c1000.getImg(), editGBC(gbc, 7, 0));
        this.money.add(Main.c5000.getImg(), editGBC(gbc, 8, 0));
        this.money.add(Main.c25000.getImg(), editGBC(gbc, 9, 0));
        this.money.add(Main.c100000.getImg(), editGBC(gbc, 10, 0));

        buttonActions.put(String.valueOf(Main.c1.getValue()), Main.c1.button);
        buttonActions.put(String.valueOf(Main.c5.getValue()), Main.c5.button);
        buttonActions.put(String.valueOf(Main.c10.getValue()), Main.c10.button);
        buttonActions.put(String.valueOf(Main.c25.getValue()), Main.c25.button);
        buttonActions.put(String.valueOf(Main.c100.getValue()), Main.c100.button);
        buttonActions.put(String.valueOf(Main.c500.getValue()), Main.c500.button);
        buttonActions.put(String.valueOf(Main.c1000.getValue()), Main.c1000.button);
        buttonActions.put(String.valueOf(Main.c5000.getValue()), Main.c5000.button);
        buttonActions.put(String.valueOf(Main.c25000.getValue()), Main.c25000.button);
        buttonActions.put(String.valueOf(Main.c100000.getValue()), Main.c100000.button);

        this.money.add(Main.c1.updateAmount(Main.player.getMoney(1)), editGBC(this.gbc, 1, 1));
        this.money.add(Main.c5.updateAmount(Main.player.getMoney(5)), editGBC(this.gbc, 2, 1));
        this.money.add(Main.c10.updateAmount(Main.player.getMoney(10)), editGBC(this.gbc, 3, 1));
        this.money.add(Main.c25.updateAmount(Main.player.getMoney(25)), editGBC(this.gbc, 4, 1));
        this.money.add(Main.c100.updateAmount(Main.player.getMoney(100)), editGBC(this.gbc, 5, 1));
        this.money.add(Main.c500.updateAmount(Main.player.getMoney(500)), editGBC(this.gbc, 6, 1));
        this.money.add(Main.c1000.updateAmount(Main.player.getMoney(1000)), editGBC(this.gbc, 7, 1));
        this.money.add(Main.c5000.updateAmount(Main.player.getMoney(5000)), editGBC(this.gbc, 8, 1));
        this.money.add(Main.c25000.updateAmount(Main.player.getMoney(25000)), editGBC(this.gbc, 9, 1));
        this.money.add(Main.c100000.updateAmount(Main.player.getMoney(100000)), editGBC(this.gbc, 10, 1));


    }

    private void betChipsStart(){
        this.bet.add(Main.c1.getImgBet(), editGBC(gbc, 1, 0));
        this.bet.add(Main.c5.getImgBet(), editGBC(gbc, 2, 0));
        this.bet.add(Main.c10.getImgBet(), editGBC(gbc, 3, 0));
        this.bet.add(Main.c25.getImgBet(), editGBC(gbc, 4, 0));
        this.bet.add(Main.c100.getImgBet(), editGBC(gbc, 5, 0));
        this.bet.add(Main.c500.getImgBet(), editGBC(gbc, 6, 0));
        this.bet.add(Main.c1000.getImgBet(), editGBC(gbc, 7, 0));
        this.bet.add(Main.c5000.getImgBet(), editGBC(gbc, 8, 0));
        this.bet.add(Main.c25000.getImgBet(), editGBC(gbc, 9, 0));
        this.bet.add(Main.c100000.getImgBet(), editGBC(gbc, 10, 0));

        this.bet.add(Main.c1.updateBetAmount(Main.player.getBet(1)), editGBC(this.gbc, 1, 1));
        this.bet.add(Main.c5.updateBetAmount(Main.player.getBet(5)), editGBC(this.gbc, 2, 1));
        this.bet.add(Main.c10.updateBetAmount(Main.player.getBet(10)), editGBC(this.gbc, 3, 1));
        this.bet.add(Main.c25.updateBetAmount(Main.player.getBet(25)), editGBC(this.gbc, 4, 1));
        this.bet.add(Main.c100.updateBetAmount(Main.player.getBet(100)), editGBC(this.gbc, 5, 1));
        this.bet.add(Main.c500.updateBetAmount(Main.player.getBet(500)), editGBC(this.gbc, 6, 1));
        this.bet.add(Main.c1000.updateBetAmount(Main.player.getBet(1000)), editGBC(this.gbc, 7, 1));
        this.bet.add(Main.c5000.updateBetAmount(Main.player.getBet(5000)), editGBC(this.gbc, 8, 1));
        this.bet.add(Main.c25000.updateBetAmount(Main.player.getBet(25000)), editGBC(this.gbc, 9, 1));
        this.bet.add(Main.c100000.updateBetAmount(Main.player.getBet(100000)), editGBC(this.gbc, 10, 1));

    }

    public void moneyTextUpdate(){
        Main.c1.updateAmount(Main.player.getMoney(1));
        Main.c5.updateAmount(Main.player.getMoney(5));
        Main.c10.updateAmount(Main.player.getMoney(10));
        Main.c25.updateAmount(Main.player.getMoney(25));
        Main.c100.updateAmount(Main.player.getMoney(100));
        Main.c500.updateAmount(Main.player.getMoney(500));
        Main.c1000.updateAmount(Main.player.getMoney(1000));
        Main.c5000.updateAmount(Main.player.getMoney(5000));
        Main.c25000.updateAmount(Main.player.getMoney(25000));
        Main.c100000.updateAmount(Main.player.getMoney(100000));
    }

    public void betTextUpdate(){
        Main.c1.updateBetAmount(Main.player.getBet(1));
        Main.c5.updateBetAmount(Main.player.getBet(5));
        Main.c10.updateBetAmount(Main.player.getBet(10));
        Main.c25.updateBetAmount(Main.player.getBet(25));
        Main.c100.updateBetAmount(Main.player.getBet(100));
        Main.c500.updateBetAmount(Main.player.getBet(500));
        Main.c1000.updateBetAmount(Main.player.getBet(1000));
        Main.c5000.updateBetAmount(Main.player.getBet(5000));
        Main.c25000.updateBetAmount(Main.player.getBet(25000));
        Main.c100000.updateBetAmount(Main.player.getBet(100000));

    }

    public void updateChips() {
        Main.c1.getImg();
        Main.c5.getImg();
        Main.c10.getImg();
        Main.c25.getImg();
        Main.c100.getImg();
        Main.c500.getImg();
        Main.c1000.getImg();
        Main.c5000.getImg();
        Main.c25000.getImg();
        Main.c100000.getImg();


        moneyTextUpdate();
    }

    public void updateBet(){
        Main.c1.getImgBet();
        Main.c5.getImgBet();
        Main.c10.getImgBet();
        Main.c25.getImgBet();
        Main.c100.getImgBet();
        Main.c500.getImgBet();
        Main.c1000.getImgBet();
        Main.c5000.getImgBet();
        Main.c25000.getImgBet();
        Main.c100000.getImgBet();
        betTextUpdate();
    }

    // Add dealer's hand
    private void addNorth(String path1, String path2) {
        JPanel panel = new JPanel();
        JPanel chips = new JPanel(new FlowLayout());
        panel.setLayout(new GridBagLayout());
        panel.setBackground(bgColor);
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10,10,10,10));
        chips.setBackground(bgColor);

        JLabel dealer = new JLabel("Dealer");
        dealer.setBorder(new EmptyBorder(10,10,0,10));
        this.North.add(dealer);

        // card placeholder image
        String path = "./images/chips/chips.png";
        chips.add(getImage(path, 100));
        panel.add(dealerStart(path1, path2), editGBC(gbc, 0,1));
        panel.add(chips, editGBC(gbc, 1, 1));
        // add panels to North
        this.North.setLayout(new GridBagLayout());
        this.North.add(panel, editGBC(gbc, 0,1));

        this.North.setBackground(bgColor);

        logger.info("North Done");

    }

    private void addCenter(String path){
        this.Center.setLayout(new GridBagLayout());
        this.Center.setBackground(bgColor);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(bgColor);
        panel.setBorder(new EmptyBorder(10,10,10,10));

        this.bet = new JPanel(new GridBagLayout());
        this.bet.setBackground(bgColor);
        betChipsStart();
        this.Center.add(this.bet,editGBC(gbc, 0,0));

        // card placeholder images
        panel.add(playerStart(path), editGBC(gbc, 0,1));

        // add panels to
        this.Center.add(panel, editGBC(gbc, 0,1));

        logger.info("Center Done");
    }

    private void addSouth(){
        this.South.setBackground(bgColor);
        this.South.setBorder(new EmptyBorder(10,10,10,10));
        this.South.setLayout(new GridBagLayout());
        JPanel buttons = new JPanel();
        buttons.setBackground(bgColor);

        JButton hitButton = new JButton("Hit");
        buttons.add(hitButton);

        JButton stayButton = new JButton("Stay");
        buttons.add(stayButton);

        playerButtons.put("stay", stayButton);
        playerButtons.put("hit", hitButton);

        this.South.add(buttons, editGBC(gbc, 0, 0));

        this.money = new JPanel(new GridBagLayout());
        this.money.setBackground(bgColor);
        chipsStart();
        this.South.add(this.money, editGBC(this.gbc,0,1));
        JPanel betPanel = new JPanel();
        betPanel.setBackground(bgColor);
        JButton betButton = new JButton("Done Bet");
        betPanel.add(betButton);

        playerButtons.put("bet", betButton);
        this.South.add(betPanel, editGBC(this.gbc,0,2));

        logger.info("South Done");
    }

}
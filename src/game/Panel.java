package game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;
import java.util.logging.Logger;

public class Panel extends JFrame{
    public static Logger logger = Main.logger;
    public JPanel container;
    public JPanel North;
    public JPanel Center;
    public JPanel South;

    /*
     * Initialize the GUI Game.Window
     */
    public Panel() {
        JFrame frame = new JFrame("Blackjack");
        this.container = new JPanel();
        container.setLayout(new BorderLayout());
        // Add dealer section
        String path = "./cards/placeholder.png";
        this.North = new JPanel();
        this.addNorth(path, path);
        container.add(this.North, BorderLayout.NORTH);

        // Add player section
        this.Center = new JPanel();
        this.addCenter(path, path, path, path);
        container.add(this.Center, BorderLayout.CENTER);

        // Add play buttons
        this.South = new JPanel();
        this.addSouth();
        container.add(this.South, BorderLayout.SOUTH);

        frame.add(container);

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

    private JLabel cardPlaceholder(String path){
        JLabel image = new JLabel();
        try {
            ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
            Image i = img.getImage();
            Image newimg = i.getScaledInstance(100,145, Image.SCALE_SMOOTH);
            img = new ImageIcon(newimg);

            image = new JLabel(img);
            image.setBorder(new EmptyBorder(10,10,10,10));
            logger.info("Placeholder Done");
        } catch(Exception e) {
            System.out.println("Placeholder Image Error");
        }
        return image;
    }

    private String updateImg(String path){
        return path;
    }

    // Add dealer's hand
    private void addNorth(String path1, String path2) {
        JPanel panel = new JPanel();
        JPanel text = new JPanel();
        panel.setLayout(new GridBagLayout());
        //panel.setBackground(new Color(0, 100, 35));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        JLabel totalLabel = new JLabel("Dealer's Total: ");
        JTextField totalDealer;
        // card placeholder images
        panel.add(cardPlaceholder(path1), editGBC(gbc, 0,0));
        panel.add(cardPlaceholder(path2), editGBC(gbc, 1,0));
        // add panels to North
        this.North.setLayout(new GridBagLayout());
        this.North.add(panel, editGBC(gbc, 0,0));

        // For total text
        text.setLayout(new GridBagLayout());
        text.setBackground(new Color(0, 100, 35));
        text.setBorder(new EmptyBorder(10,10,0,10));
        text.add(totalLabel, editGBC(gbc, 0,0));

        totalDealer = new JTextField("0");
        totalDealer.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                totalDealer.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        totalDealer.setBorder(BorderFactory.createEmptyBorder());
        totalDealer.setBackground(new Color(0, 100, 35));
        totalDealer.setEditable(false);
        text.add(totalDealer, editGBC(gbc, 1, 0));
        this.North.add(text, editGBC(gbc, 0,1));
        this.North.setBackground(new Color(0, 100, 35));

        logger.info("North Done");

    }

    private void addCenter(String path1, String path2, String path3, String path4){
        JPanel text = new JPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(0, 100, 35));
        panel.setBorder(new EmptyBorder(10,10,10,10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        JLabel totalLabel = new JLabel("Player's Total: ");
        totalLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        JTextField totalPlayer;

        // For total text
        text.setLayout(new GridBagLayout());
        text.setBackground(new Color(0, 100, 35));
        text.setBorder(new EmptyBorder(10,10,0,10));
        text.add(totalLabel, editGBC(gbc, 0,0));

        totalPlayer = new JTextField("0");
        totalPlayer.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                totalPlayer.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        totalPlayer.setBorder(BorderFactory.createEmptyBorder());
        totalPlayer.setBackground(new Color(0, 100, 35));
        totalPlayer.setEditable(false);
        text.add(totalPlayer, editGBC(gbc, 1, 0));
        this.Center.add(text, editGBC(gbc, 0,0));
        this.Center.setBackground(new Color(0, 100, 35));

        // card placeholder images
        panel.add(cardPlaceholder(path1), editGBC(gbc, 0,0));
        panel.add(cardPlaceholder(path2), editGBC(gbc, 1,0));
        panel.add(cardPlaceholder(path3), editGBC(gbc, 2,0));
        panel.add(cardPlaceholder(path4), editGBC(gbc, 3,0));
        // add panels to Center
        this.Center.setLayout(new GridBagLayout());
        this.Center.add(panel, editGBC(gbc, 0,1));

        Main.logger.info("Center Done");
    }

    private void addSouth(){
        this.South.setBackground(new Color(0, 100, 35));
        this.South.setBorder(new EmptyBorder(10,10,10,10));

        JButton hitButton = new JButton("Hit");
        this.South.add(hitButton);
        hitButton.addActionListener(e -> System.out.println("hit button pressed"));

        JButton stayButton = new JButton("Stay");
        this.South.add(stayButton);

        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stay button pressed");
            }
        });

        logger.info("South Done");
    }

}
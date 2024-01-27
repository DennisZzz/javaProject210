package ui;

import model.Account;
import model.Event;
import model.EventLog;
import model.Game;
import model.History;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represent a game panel that allow you to play a game with bet
public class GamePanel extends JFrame implements ActionListener {
    private boolean gameOver;
    private final Account account;
    private JLabel yourCard;
    private JLabel houseCard;
    private JLabel yourTotal;
    private JLabel houseTotal;
    private JLabel feedback;
    private final Game game;
    private int bet;
    private String output;
    private String result;
    private JLabel imageLabel;

    // MODIFIES: this
    // EFFECTS: create a game panel with bet, allow user to play the game and decide whether to update their account
    //          or not
    public GamePanel(Account account, int bet) {
        super("Game with" + bet + "$");
        this.account = account;
        this.bet = bet;
        this.game = new Game();
        this.gameOver = false;
        this.result = "Default";
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(600,520));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        add();
        addB();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        String output = game.userHit();
        yourCard.setText(output);
        yourTotal.setText("You total number is " + game.getUserNum());
        output = game.houseHit();
        houseCard.setText(output);
        houseTotal.setText("House's total number is " + game.getHouseNum());
        printEventLogWhenClose();
    }

    public void printEventLogWhenClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                dispose();
            }
        });
    }

    public void printLog(EventLog eventlog) {
        for (Event event : eventlog) {
            System.out.println(event.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: Add buttons related to game to the panel
    public void addB() {
        JButton hit = new JButton("Hit");
        hit.setActionCommand("Hit");
        hit.addActionListener(this);
        JButton noHit = new JButton("Not Hit");
        noHit.setActionCommand("NoHit");
        noHit.addActionListener(this);
        JButton back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        JButton addHistory = new JButton("Add History to Account (Only If Game Is Over)");
        addHistory.setActionCommand("AddHistory");
        addHistory.addActionListener(this);
        add(hit);
        add(noHit);
        add(back);
        add(addHistory);
    }

    // MODIFIES: add instruction and feedback text box to the panel
    public void add() {
        yourCard = new JLabel("Your Card is:");
        houseCard = new JLabel("House Card is:");
        yourTotal = new JLabel("Your Total Number is: 0");
        houseTotal = new JLabel("House Total Number is: 0");
        feedback = new JLabel("Do you want to hit or not");
        imageLabel = new JLabel();
        add(yourCard);
        add(houseCard);
        add(yourTotal);
        add(houseTotal);
        add(feedback);
        add(imageLabel);
    }

    // MODIFIES: this, AccountApp
    // EFFECTS: update the balance and add history of account, and provide game final win/loss
    private void updateAccount(Game game, int bet) {
        yourTotal.setText("You total number is " + game.getUserNum());
        houseTotal.setText("House's total number is " + game.getHouseNum());
        if (game.userWin()) {
            feedback.setText("You win the game.");
            this.result = "Win";
        } else {
            feedback.setText("You loss the game.");
            this.bet = - bet;
            this.result = "Loss";
        }
    }

    // MODIFIES: this
    // EFFECTS: 1. if hit button being pressed, preform hitDetail()
    //          2. if noHit button being pressed, preform noHitDetail()
    //          3. if back button being pressed, create a new account panel with this account, and dispose this account
    //          4. if addHistory button being pressed, preform the related action and update account, and pop a image
    //             that confirm the update. if game not finished, give related feedback
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Hit")) {
            hitDetail();
        } else if (e.getActionCommand().equals("NoHit")) {
            noHitDetail();
        } else if (e.getActionCommand().equals("Back")) {
            new CasinoPanel(this.account);
            dispose();
        } else if (e.getActionCommand().equals("AddHistory")) {
            if (this.result == "Default") {
                feedback.setText("game is not over, please hit");
            } else if (this.result == "Loss") {
                this.account.changeAmount(bet);
                History history = new History(bet, "Loss");
                account.addHistory(history);
                loadImage();
            } else if (this.result == "Win") {
                this.account.changeAmount(bet);
                History history = new History(bet, "Win");
                account.addHistory(history);
                loadImage();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: pop an image on the panel as a conformation of account updated successfully
    public void loadImage() {
        String imagePath = "data/image1.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        imageLabel.setIcon(imageIcon);
    }

    // MODIFIES: this
    // EFFECTS: update game and account, and set game status to game over
    public void update() {
        updateAccount(game, bet);
        gameOver = true;
    }


    // MODIFIES: this
    // EFFECTS: preform the action when user hit, and update game
    public void hitDetail() {
        if (!gameOver) {
            output = game.userHit();
            yourCard.setText(output);
            yourTotal.setText("You total number is " + game.getUserNum());

            for (output = "House's total number is "; game.getUserNum() > game.getHouseNum() && game.gameNotOver(); ) {
                game.houseHit();
                output = output + ", " + game.getCardName();
            }

            houseCard.setText(output);
            houseTotal.setText("House's total number is " + game.getHouseNum());
            if (game.gameNotOver()) {
                feedback.setText("Do you want to hit or not?");
            } else {
                update();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: preform the action when user didn't hit, and update game
    public void noHitDetail() {
        if (!gameOver) {
            for (output = "House's total number is "; game.getUserNum() > game.getHouseNum() && game.gameNotOver(); ) {
                game.houseHit();
                output = output + ", " + game.getCardName();
            }
            houseCard.setText(output);
            houseTotal.setText("House's total number is " + game.getHouseNum());
            update();
        }
    }
}

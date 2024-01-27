package ui;

import model.Account;
import model.Event;
import model.EventLog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represent a Casino panel that allow you to select different games with different bets
public class CasinoPanel extends JFrame implements ActionListener {
    private Account account;

    // MODIFIES: this
    // EFFECTS: create a Casino Panel that allow you to choice different games with different bets
    public CasinoPanel(Account account) {
        super("Casino");
        this.account = account;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(350,350));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        add();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
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
    // EFFECTS: add different games (with different bets) buttons and a quit button
    public void add() {
        addGame1();
        addGame2();
        addGame3();
        addGame4();
        addGame5();
        addquit();
    }

    // MODIFIES: this
    // EFFECTS: add game with 100$ button to the panel
    public void addGame1() {
        JButton g1 = new JButton("Game with 100$ bet");
        g1.setActionCommand("100");
        g1.addActionListener(this);
        add(g1);
    }

    // MODIFIES: this
    // EFFECTS: add game with 150$ button to the panel
    public void addGame2() {
        JButton g2 = new JButton("Game with 150$ bet");
        g2.setActionCommand("150");
        g2.addActionListener(this);
        add(g2);
    }

    // MODIFIES: this
    // EFFECTS: add game with 200$ button to the panel
    public void addGame3() {
        JButton g3 = new JButton("Game with 200$ bet");
        g3.setActionCommand("200");
        g3.addActionListener(this);
        add(g3);
    }

    // MODIFIES: this
    // EFFECTS: add game with 500$ button to the panel
    public void addGame4() {
        JButton g4 = new JButton("Game with 500$ bet");
        g4.setActionCommand("500");
        g4.addActionListener(this);
        add(g4);
    }

    // MODIFIES: this
    // EFFECTS: add game with 500$ button to the panel
    public void addGame5() {
        JButton g5 = new JButton("Game with 1000$ bet");
        g5.setActionCommand("1000");
        g5.addActionListener(this);
        add(g5);
    }

    // MODIFIES: this
    // EFFECTS: add quit button to the panel
    public void addquit() {
        JButton quit = new JButton("Quit");
        quit.setActionCommand("Quit");
        quit.addActionListener(this);
        add(quit);
    }


    // MODIFIES: this
    // EFFECTS: For different bets value games, create a new game panel that has that much of bet,
    //          and dispose this panel
    //          For quit button, create a new account panel with this account, and dispose this panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("100")) {
            new GamePanel(account, 100);
            dispose();
        } else if (e.getActionCommand().equals("150")) {
            new GamePanel(account, 150);
            dispose();
        } else if (e.getActionCommand().equals("200")) {
            new GamePanel(account, 200);
            dispose();
        } else if (e.getActionCommand().equals("500")) {
            new GamePanel(account, 500);
            dispose();
        } else if (e.getActionCommand().equals("1000")) {
            new GamePanel(account, 1000);
            dispose();
        } else if (e.getActionCommand().equals("Quit")) {
            new AccountPanel(account);
            dispose();
        }
    }
}

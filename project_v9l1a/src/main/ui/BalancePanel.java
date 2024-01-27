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


// Represent a panel that shows the balance of the account
public class BalancePanel extends JFrame implements ActionListener {
    private Account account;
    private JLabel balance;

    // MODIFIES: this
    // EFFECTS: create a new balance panel that allow you to check the balance of your account
    public BalancePanel(Account account) {
        super("Balance");
        this.account = account;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(350,250));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        JButton back = new JButton("Return to Your Personal Account");
        back.setActionCommand("Back");
        back.addActionListener(this);
        balance = new JLabel(String.valueOf(account.getBalance()));

        add(balance);
        add(back);
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
    // EFFECTS: when pressing "back", create a new account panel with this account and dispose this panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            new AccountPanel(account);
            dispose();
        }
    }
}

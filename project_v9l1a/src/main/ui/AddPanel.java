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

// Represent an add money panel that allow you to add money to your account
public class AddPanel extends JFrame implements ActionListener {
    private Account account;
    private JLabel addInstruction;
    private JTextField add;

    // REQUIRES: input amount must be positive
    // MODIFIES: this
    // EFFECTS: create a new panel that allow you to add an input amount of money to your account
    public AddPanel(Account account) {
        super("Add Money");
        this.account = account;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(350,250));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        JButton addB = new JButton("Add Money to your account");
        JButton back = new JButton("Return to Your Personal Account");
        back.setActionCommand("Back");
        back.addActionListener(this);
        addB.setActionCommand("Add");
        addB.addActionListener(this);
        addInstruction = new JLabel("How much money do you want to add to your account");
        add = new JTextField(10);

        add(addInstruction);
        add(add);
        add(addB);
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

    // REQUIRES: input amount must be positive
    // MODIFIES: this
    // EFFECTS: 1. Add input money to account when "Add Money", and create a new account panel with this account, and
    //             dispose this account
    //          2. create a new account panel with this account and dispose this account when pressing "Back"
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            new AccountPanel(this.account);
            dispose();
        } else if (e.getActionCommand().equals("Add")) {
            this.account.changeAmount(Double.parseDouble(add.getText()));
            new AccountPanel(this.account);
            dispose();
        }
    }
}

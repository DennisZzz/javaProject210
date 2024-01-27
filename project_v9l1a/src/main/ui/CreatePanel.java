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


// Represent a create account panel to create new account
public class CreatePanel extends JFrame implements ActionListener {
    private Account account;
    private JTextField name;
    private JTextField age;
    private JTextField amount;
    private JLabel nameInstruction;
    private JLabel ageInstruction;
    private JLabel amountInstruction;

    // REQUIRES: name must be string, age must be int, and amount must be double
    // MODIFIES: this
    // EFFECTS: Create a new panel that allow you to generate new account with name, age, and amount
    public CreatePanel() {
        super("Create Personal Account");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(450,270));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());
        JButton create = new JButton("Create new personal account");
        create.setActionCommand("Create");
        create.addActionListener(this);
        add();
        add(create);
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
    // EFFECTS: add required JLables and JField to the panel
    public void add() {
        nameInstruction = new JLabel("Your name is:");
        ageInstruction = new JLabel("Your age is:");
        amountInstruction = new JLabel("Add money to your account :");
        name = new JTextField(10);
        age = new JTextField(10);
        amount = new JTextField(10);
        add(nameInstruction);
        add(name);
        add(ageInstruction);
        add(age);
        add(amountInstruction);
        add(amount);
    }

    // MODIFIES: this
    // EFFECTS: create new account with input name, age, and amount, create new panel,
    //          and dispose this panel when press button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create")) {
            String name = this.name.getText();
            int age = Integer.parseInt(this.age.getText());
            double amount = Double.parseDouble(this.amount.getText());
            account = new Account(name,age,amount);
            new AccountPanel(this.account);
            dispose();
        }
    }
}

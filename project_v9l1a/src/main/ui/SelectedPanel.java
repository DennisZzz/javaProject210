package ui;

import model.Account;
import model.Event;
import model.EventLog;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

// Represent a select account panel that allow you to select account
public class SelectedPanel extends JFrame implements ActionListener {
    private Account account;
    private JTextField field;
    private JLabel nameInstruction;
    private JsonReader jsonReader;
    private String jsonStore;

    // REQUIRES: the input name must be an existing account name
    // MODIFIES: this
    // EFFECTS: create a new select account panel that has one input field, and one button:
    //          1. button to select the input account
    //          2. input field to input the accoutn name
    public SelectedPanel() {
        super("Select Personal Account");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(450,270));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        JButton select = new JButton("Select this account");
        select.setActionCommand("Select");
        select.addActionListener(this);

        nameInstruction = new JLabel("Your name is:");
        field = new JTextField(10);
        add(nameInstruction);
        add(field);
        add(select);
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
    // EFFECTS: load the account when press button, create a new panel, and dispose this panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Select")) {
            String name = this.field.getText();
            doLoadAccount(name);
            new AccountPanel(this.account);
            dispose();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the account with input name
    private void doLoadAccount(String name) {
        this.jsonStore = "./data/" + name + ".json";
        jsonReader = new JsonReader(jsonStore);
        try {
            this.account = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Can't read the file: " + jsonStore);
        }
    }
}

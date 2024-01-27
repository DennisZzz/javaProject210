package ui;

import model.EventLog;
import model.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Represent a Main Menu of the GUI application, as an opening page of application
public class MainMenuPanel extends JFrame implements ActionListener {

    // MODIFIES: this
    // EFFECTS: create a new Main Menu that has three button:
    //          1. create a new account: lead to create account panel
    //          2. select an old account: lead to select account panel
    //          3. quit the application
    public MainMenuPanel() {
        super("Casino App Main Menu");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(350,250));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        JButton create = new JButton("Create new personal account");
        JButton select = new JButton("Select your personal account");
        JButton quit = new JButton("Quit Casino application");

        create.setActionCommand("Create");
        select.setActionCommand("Select");
        quit.setActionCommand("Quit");

        create.addActionListener(this);
        select.addActionListener(this);
        quit.addActionListener(this);

        add(create);
        add(select);
        add(quit);

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

    // EFFECTS: Follow the instruction of pressed button, create new panel, and dispose this panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create")) {
            new CreatePanel();
            dispose();
        } else if (e.getActionCommand().equals("Select")) {
            new SelectedPanel();
            dispose();
        } else if (e.getActionCommand().equals("Quit")) {
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new MainMenuPanel();
    }
}

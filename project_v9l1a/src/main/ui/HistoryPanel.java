package ui;

import model.Account;
import model.Event;
import model.EventLog;
import model.History;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;

// Represent a History panel that shows all the histories of the account, and can show "Win' or "Loss" history, and can
// clear all history
public class HistoryPanel extends JFrame implements ActionListener {
    private Account account;
    private List<String> textElements;
    private JList<String> textList;
    private JScrollPane scrollPane;


    // MODIFIES: this
    // EFFECTS: create panel that show all required histories and related interaction buttons
    public HistoryPanel(Account account) {
        super("History");
        this.account = account;
        textElements = new ArrayList<>();
        getAllHistory();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(400,500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());

        add();
        addB();
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
    // EFFECTS: Add a scroll down text box to show required histories
    public void add() {
        scrollPane = new JScrollPane(textList);
        add(scrollPane, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: Add 4 buttons that allow user to interact with the History and a quit button
    public void addB() {
        JButton all = new JButton("Show all History");
        all.setActionCommand("All");
        all.addActionListener(this);
        add(all);
        JButton win = new JButton("Show Win History");
        win.setActionCommand("Win");
        win.addActionListener(this);
        add(win);
        JButton loss = new JButton("Show Loss History");
        loss.setActionCommand("Loss");
        loss.addActionListener(this);
        add(loss);
        JButton clear = new JButton("Clear History");
        clear.setActionCommand("Clear");
        clear.addActionListener(this);
        add(clear);
        JButton back = new JButton("Back");
        back.setActionCommand("Back");
        back.addActionListener(this);
        add(back);
    }

    // MODIFIES: this
    // EFFECTS: get all histories of the account
    public void getAllHistory() {
        this.textElements.clear();
        for (History history : this.account.getHistoryList()) {
            textElements.add(history.printHistory());
        }
        textList = new JList<>(textElements.toArray(new String[0]));
    }

    // MODIFIES: this
    // EFFECTS: get all win histories of the account
    public void getWinHistory() {
        this.textElements.clear();
        for (History history : this.account.getHistoryList()) {
            if (history.getResult().equals("Win")) {
                textElements.add(history.printHistory());
            }
        }
        textList = new JList<>(textElements.toArray(new String[0]));
    }

    // MODIFIES: this
    // EFFECTS: get all loss histories of the account
    public void getLossHistory() {
        this.textElements.clear();
        for (History history : this.account.getHistoryList()) {
            if (history.getResult().equals("Loss")) {
                textElements.add(history.printHistory());
            }
        }
        textList = new JList<>(textElements.toArray(new String[0]));
    }

    // MODIFIES: this
    // EFFECTS: doing 4 interaction with history with different buttons, and a quit button that quit to account panel
    //          1. by pressing "All", to show all the histories
    //          2. by pressing "Win", to show all win histories
    //          3. by pressing "Loss", to show all Loss histories
    //          4. by pressing "Clear", to delete all histories in the account.
    //          5. by pressing "Quit", update account, and create a new account panel, and dispose this panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            new AccountPanel(account);
            dispose();
        } else if (e.getActionCommand().equals("Win")) {
            getWinHistory();
            scrollPane.setViewportView(textList);
        } else if (e.getActionCommand().equals("All")) {
            getAllHistory();
            scrollPane.setViewportView(textList);
        } else if (e.getActionCommand().equals("Loss")) {
            getLossHistory();
            scrollPane.setViewportView(textList);
        } else if (e.getActionCommand().equals("Clear")) {
            this.textElements.clear();
            this.account.resetHistory();
            textList = new JList<>(textElements.toArray(new String[0]));
            scrollPane.setViewportView(textList);
        }
    }
}

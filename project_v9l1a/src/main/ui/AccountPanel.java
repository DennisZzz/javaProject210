package ui;

import model.Account;
import model.Event;
import model.EventLog;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

// Represent an account panel that allow you to interact with your account.
public class AccountPanel extends JFrame implements ActionListener {
    private final Account account;
    private final JsonWriter jsonWriter;
    private String jsonStore;

    // MODIFIES: this
    // EFFECTS: create a new account panel that allow you to have interaction with this account
    public AccountPanel(Account account) {
        super("Account Menu");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(450,350));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(50,100,50,100));
        setLayout(new FlowLayout());
        add();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        this.account = account;
        this.jsonStore = "./data/" + account.getName() + ".json";
        jsonWriter = new JsonWriter(jsonStore);
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
    // EFFECTS: add interaction buttons to the panel
    public void add() {
        addCheckBalance();
        addCheckHistory();
        addAddMoneyToAccount();
        addEntryCasino();
        addSaveYourAccount();
        addQuitToMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: add check balance button to the panel
    public void addCheckBalance() {
        JButton checkBalance = new JButton("Check Balance");
        checkBalance.setActionCommand("Balance");
        checkBalance.addActionListener(this);
        add(checkBalance);
    }

    // MODIFIES: this
    // EFFECTS: add check history button to the panel
    public void addCheckHistory() {
        JButton checkHistory = new JButton("Check History");
        checkHistory.setActionCommand("History");
        checkHistory.addActionListener(this);
        add(checkHistory);

    }

    // MODIFIES: this
    // EFFECTS: add "add money" button to the panel
    public void addAddMoneyToAccount() {
        JButton addMoneyToAccount = new JButton("Add Money to Account");
        addMoneyToAccount.setActionCommand("Add");
        addMoneyToAccount.addActionListener(this);
        add(addMoneyToAccount);
    }

    // MODIFIES: this
    // EFFECTS: add entry casino button to the panel
    public void addEntryCasino() {
        JButton entryCasino = new JButton("Entry Casino");
        entryCasino.setActionCommand("Entry");
        entryCasino.addActionListener(this);
        add(entryCasino);

    }

    // MODIFIES: this
    // EFFECTS: add save button to the panel
    public void addSaveYourAccount() {
        JButton saveYourAccount = new JButton("Save your account");
        saveYourAccount.setActionCommand("Save");
        saveYourAccount.addActionListener(this);
        add(saveYourAccount);

    }

    // MODIFIES: this
    // EFFECTS: add quit button to the panel
    public void addQuitToMainMenu() {
        JButton quitToMainMenu = new JButton("Quit to Main Menu");
        quitToMainMenu.setActionCommand("Quit");
        quitToMainMenu.addActionListener(this);
        add(quitToMainMenu);
    }

    // MODIFIES: this
    // EFFECTS: doing 6 interaction with this account by pressing different buttons
    //          1. by pressing "Balance", create a new balance panel and dispose this panel
    //          2. by pressing "History", create a new history panel and dispose this panel
    //          3. by pressing "Add", create a new addMoney panel and dispose this panel
    //          4. by pressing "Entry", create a new Casino panel and dispose this panel
    //          5. by pressing "Save", save the current state of your account to data
    //          6. by pressing "Quit", create a new Main Menu panel and dispose this panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Balance")) {
            new BalancePanel(account);
            dispose();
        } else if (e.getActionCommand().equals("History")) {
            new HistoryPanel(account);
            dispose();
        } else if (e.getActionCommand().equals("Add")) {
            new AddPanel(account);
            dispose();
        } else if (e.getActionCommand().equals("Entry")) {
            new CasinoPanel(account);
            dispose();
        } else if (e.getActionCommand().equals("Save")) {
            saveWorkRoom();
        } else if (e.getActionCommand().equals("Quit")) {
            new MainMenuPanel();
            dispose();
        }
    }

    // EFFECTS: saves the account to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(account);
            jsonWriter.close();
            // System.out.println(account.getName() + ", your account is saved to" + jsonStore);
        } catch (FileNotFoundException e) {
            // System.out.println("Can't write to file: " + jsonStore);
        }
    }
}

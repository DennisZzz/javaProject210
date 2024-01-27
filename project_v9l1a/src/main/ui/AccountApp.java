package ui;

import model.Account;
import model.History;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
Represents the user account application
 */
public class AccountApp {
    private Scanner input;
    private Account account;
    private JsonWriter jsonWriter;
    private String jsonStore;

    // EFFECTS: runs account application
    public AccountApp() throws FileNotFoundException {
        runAccountApp();
    }

    // MODIFIES: this
    // EFFECTS: runs account application until user quits, processes user input
    private void runAccountApp() {
        boolean keepRunning = true;
        String command;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initialize input setting
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: display the main function of menu
    private void displayMenu() {
        System.out.println("\nWhat you want to do next:");
        System.out.println("\tEnter c to create new personal account");
        System.out.println("\tEnter s to select exist personal account");
        System.out.println("\tEnter q to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes the input user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            doCreate();
        } else if (command.equals("s")) {
            doLoadAccount();
            doNext();
        } else {
            System.out.println("Your Selection is not valid, please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the account with the input name
    private void doLoadAccount() {
        System.out.println("What's your name?");
        String name = input.next();
        this.jsonStore = "./data/" + name + ".json";
        JsonReader jsonReader = new JsonReader(jsonStore);
        jsonWriter = new JsonWriter(jsonStore);
        try {
            this.account = jsonReader.read();
            System.out.println("Your account is selected");
        } catch (IOException e) {
            System.out.println("Can't read the file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: create an account
    private void doCreate() {
        System.out.println("Your name is:");
        String name = input.next();

        System.out.println("Your age is?");
        int age = input.nextInt();
        System.out.println("How much money do you wan to add to your account? (must more than 100$)");
        double amount = input.nextDouble();
        while (amount < 100.00) {
            System.out.println("Must add more than 100$, please enter again");
            amount = input.nextDouble();
        }

        this.account = new Account(name, age, amount);
        this.jsonStore = "./data/" + account.getName() + ".json";
        jsonWriter = new JsonWriter(jsonStore);
        System.out.println("Your account is created! What do you want to do next?");
        doNext();
    }


    // MODIFIES: this
    // EFFECTS: handle the command
    private void doNext() {
        doNextInstruction(); //instruction
        String selection = input.next(); //input

        while (!(doNextSelectionCorrect(selection))) {
            System.out.println("Please follow the instruction above and try to press again");
            selection = input.next();
        } // checker

        switch (selection) { //select
            case "c":
                doCheckBalance();
                break;
            case "h":
                doCheckHistory();
                break;
            case "a":
                doAddMoney();
                break;
            case "s":
                saveWorkRoom();
                break;
            case "e":
                doEnterCasino();
                break;
        }
    }

    // EFFECTS: display the menu of casino account
    private void doNextInstruction() {
        System.out.println("\tPress c to check your balance");
        System.out.println("\tPress h to check your game history");
        System.out.println("\tPress a to add money to your account");
        System.out.println("\tPress e to enter the Casino");
        System.out.println("\tPress s to save your account");
        System.out.println("\tPress q to quit your account");
    }

    // EFFECTS: saves the account to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(account);
            jsonWriter.close();
            System.out.println(account.getName() + ", your account is saved to" + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Can't write to file: " + jsonStore);
        }
    }

    // EFFECTS: return true when either expected command is pressed
    private boolean doNextSelectionCorrect(String s) {
        return s.equals("c") || s.equals("h") || s.equals("a") || s.equals("e") || s.equals("q") || s.equals("s");
    }

    // MODIFIES: this
    // EFFECTS: check balance and return the value
    private void doCheckBalance() {
        System.out.printf("Your Balance is: $%.2f.\n", this.account.getBalance());
        doNext();
    }

    // MODIFIES: this
    // EFFECTS: check game history and return all the history
    private void  doCheckHistory() {
        if (this.account.getHistoryList().isEmpty()) {
            System.out.println("There is no game history");
        } else {
            for (History history : this.account.getHistoryList()) {
                System.out.println(history.printHistory());
            }
        }
        doNext();
    }

    // MODIFIES: this
    // EFFECTS: add money to the current balance
    private void doAddMoney() {
        System.out.println("How much money do you want to add to your account? (must more than 100$)");
        double amount = input.nextDouble();
        while (amount < 100.00) {
            System.out.println("Must add more than 100$, please enter again");
            amount = input.nextDouble();
        }
        this.account.changeAmount(amount);
        doNext();
    }

    // MODIFIES: this
    // EFFECTS: enter Casino application with the existing account
    private void doEnterCasino() {
        new CasinoApp(this.account);
        doNext();
    }
}

package ui;

import model.Account;
import model.Game;
import model.History;

import java.util.Scanner;

/*
Casino application allow user to get a table and play game
 */
public class CasinoApp {
    private Scanner input;
    private final Account account;

    // MODIFIES: this, AccountApp
    // EFFECTS: instantiate a Casino application and run Casino game
    public CasinoApp(Account account) {
        this.account = account;
        runCasino();
    }

    // MODIFIES: this, AccountApp
    // EFFECTS: run Casino game until game over
    private void runCasino() {
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

    // MODIFIES: this, AccountApp
    // EFFECTS: initialize input setting
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: display the Menu of Casino
    private void displayMenu() {
        System.out.println("\nWhich table do you want to join:");
        System.out.println("\tEnter a to join the table with 100$ bet");
        System.out.println("\tEnter b to join the table with 150$ bet");
        System.out.println("\tEnter c to join the table with 200$ bet");
        System.out.println("\tEnter d to join the table with 500$ bet");
        System.out.println("\tEnter e to join the table with 1000$ bet");
        System.out.println("\tEnter i to check the game rules");
        System.out.println("\tEnter q to quit Casino, and back to your account");
    }

    // MODIFIES: this, AccountApp
    // EFFECTS: processes the input command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doTable(100);
                break;
            case "b":
                doTable(150);
                break;
            case "c":
                doTable(200);
                break;
            case "d":
                doTable(500);
                break;
            case "e":
                doTable(1000);
                break;
            case "i":
                doInstruction();
                break;
            default:
                System.out.println("Your Selection is not valid, please try again");
                break;
        }
    }

    // EFFECTS: provide game instruction of BlackJack.
    private void doInstruction() {
        System.out.println("This is a Casino with game BlackJack, and you can choose bet lower than your balance");
        System.out.println("The game BlackJack is a poker game with card A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K");
        System.out.println("A has value of 1; J, Q, and k has value of 10; others has its own value");
        System.out.println("Your total value of the card is the total value of the card you get, same to house");
        System.out.println("You will get a random card first, and house will get a random card next");
        System.out.println("Then, you can choose to hit or not, and house will hit after you only if house total value"
                + " is smaller than yours");
        System.out.println("Game will be over if anyone's total value exceed 21 or both you and house decide not hit");
        System.out.println("You will win if:");
        System.out.println("\t 1.You and House both exceed 21, and you total value is smaller than house");
        System.out.println("\t 2.House exceed 21, and you didn't");
        System.out.println("\t 3.You and House both not exceed 21, and you total value is larger than house");
    }

    // MODIFIES: this, AccountApp
    // EFFECTS: create table if user has enough money, and run game on that table
    private void doTable(int bet) {
        Game game = new Game();
        String output;
        boolean keepGaming = true;
        if (this.account.getBalance() < bet) {
            System.out.println("You don't have enough money to play in this table");

        } else {
            output = game.userHit();
            System.out.println(output);
            output = game.houseHit();
            System.out.println(output);
            while (keepGaming) {
                keepGaming = runGame(game) && game.gameNotOver();
            }
            updateAccount(game, bet);
        }
    }

    // MODIFIES: this, AccountApp
    // EFFECTS: update the balance and add history of account, and provide game final win/loss
    private void updateAccount(Game game, int bet) {
        System.out.println("\tYou total number is " + game.getUserNum());
        System.out.println("\tHouse's total number is " + game.getHouseNum());
        if (game.userWin()) {
            System.out.println("You win the game, " + bet + "$ is added to your account.");
            this.account.changeAmount(bet);
            History history = new History(bet, "Win");
            account.addHistory(history);
        } else {
            System.out.println("You loss the game, " + bet + "$ is deduct from your account.");
            this.account.changeAmount(- bet);
            History history = new History(bet, "Loss");
            account.addHistory(history);
        }
    }

    // MODIFIES: this
    // EFFECTS: return true game should run in next round; otherwise false
    private boolean runGame(Game game) {
        String output;
        System.out.println("\tYou total number is " + game.getUserNum());
        System.out.println("\tHouse's total number is " + game.getHouseNum());
        System.out.println("Do you want to hit? Enter y to hit, n to stop");
        String keepHit = input.next();
        if (keepHit.equals("y")) {
            output = game.userHit();
            System.out.println(output);
            while (game.getUserNum() > game.getHouseNum() && game.gameNotOver()) {
                output = game.houseHit();
                System.out.println(output);
            }
            return true;
        } else {
            return false;
        }
    }
}


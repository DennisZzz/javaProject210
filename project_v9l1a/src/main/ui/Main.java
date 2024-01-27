package ui;

import java.io.FileNotFoundException;

/*
main class of the casino app project
 */
public class Main {
    // EFFECTS: starting the account application
    public static void main(String[] args) {
        try {
            new AccountApp();
        } catch (FileNotFoundException e) {
            System.out.println("Can't Run Application: File Not Found");
        }
    }
}

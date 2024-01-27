package model;

import java.util.Random;

/*
Represents the game function and operation of Casino game
 */
public class Game {
    private int houseNum;
    private int userNum;
    private int newCard;

    // MODIFIES: this
    // EFFECTS: creates a new game
    public Game() {
        this.houseNum = 0;
        this.userNum = 0;
        this.newCard = 0;
    }

    public void setNewCard(int newCard) {
        this.newCard = newCard;
    } // Used for test

    public int getNewCard() {
        return newCard;
    } // Used for test

    // EFFECTS: gets a random number between 1 - 13
    public int getCard() {
        Random random = new Random();
        return random.nextInt(13) + 1;
    }

    // REQUIRES: 1 <= card <= 13
    // EFFECTS: return card value when it is less than 11; else return 10
    public int cardValue(int card) {
        if (card < 11) {
            return card;
        } else {
            return 10;
        }
    }

    // EFFECTS: gets the name of card
    public String getCardName() {
        if (this.newCard == 1) {
            return "A";
        } else if (this.newCard < 11) {
            return String.valueOf(this.newCard);
        } else if (this.newCard == 11) {
            return "J";
        } else if (this.newCard == 12) {
            return "Q";
        } else {
            return "K";
        }
    }

    // REQUIRES: userNum > 0, houseNum > 0
    // EFFECTS: return false if game over; otherwise true
    public boolean gameNotOver() {
        return this.userNum <= 21 && this.houseNum <= 21;
    }

    // EFFECTS: set the HouseNum to certain input
    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    } // Used for test

    // EFFECTS: set the UserNum to certain input
    public void setUserNum(int userNum) {
        this.userNum = userNum;
    } // Used for test

    // MODIFIES: this
    // EFFECTS: hit a card for user
    public String userHit() {
        this.newCard = getCard();
        this.userNum += cardValue(this.newCard);
        return "Your card is " + getCardName();
    }

    // MODIFIES: this
    // EFFECTS: hit a card for house
    public String houseHit() {
        this.newCard = getCard();
        this.houseNum += cardValue(this.newCard);
        return "House card is " + getCardName();
    }

    // EFFECTS: get the total number that user has right now
    public int getUserNum() {
        return userNum;
    }

    // EFFECTS: get the total number that house has right now
    public int getHouseNum() {
        return houseNum;
    }

    // REQUIRES: userNum > 0, houseNum > 0
    // EFFECTS: return true if user wins; otherwise false
    public boolean userWin() {
        if (this.userNum > 21 && this.houseNum > 21 && this.userNum < this.houseNum) {
            return true;
        } else if (this.userNum <= 21 && this.houseNum > 21) {
            return true;
        } else {
            return this.userNum <= 21 && this.houseNum < 21 && this.userNum > this.houseNum;
        }
    }
}

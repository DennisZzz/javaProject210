# My Personal Project

## Project Name: Vancouver Casino

## Phase 0

### Task 1: 

This project is to construct a game called Vancouver Casino that including a gambling game "blackjack". A user can 
create a personal account and putting money in it. The account include: name, age, and amount. After user create and 
choose a personal account, user can click "get into Casino" to get to a Casino interface. User then can choose different 
levels of blackjack, which win or lose more money in higher level. User can check the amount left and gaming history 
while in Casino interface. 

### Task 2: 

- What will application do?
  - This application is a game that monitory a Casino which has gambling game "blackjack". It provides a Casino and 
  let user play blackjack while track the user information in the same time. 
- Who will use it?
  - A person who want to gamble without losing money can use this monitory Casino application.
- Why this project interest to you?
  - This project is relax and fun, you can also get the idea that "gambling never win" without losing money.

### Task 3: 

- As a user, I want to create a new personal account with my name, age, amount of money in it.
- As a user, I want to be able to add money to my personal account.
- As a user, I want to be able to select one personal account and get into casino with it.
- As a user, I want to be able to choose different levels of blackjack I want in casino.
- As a user, I want to play blackjack game. 
- As a user, I want to add game history to my personal account after Casino game.
- As a user, I want to be able to check my personal account and gambling history in my account page. 

## Phase1:

### Task:

In this phase, my reference is: TellerApp from Edx Phase 1. I use the idea of how TUI work and the basic structure of 
TUI in my AccountApp and CasinoApp classes.

Finished flowing user story in TUI:

- As a user, I want to create a new personal account with my name, age, amount of money in it.
- As a user, I want to be able to add money to my personal account.
- As a user, I want to be able to choose different levels of blackjack I want in casino.
- As a user, I want to play blackjack game.
- As a user, I want to add game history to my personal account after Casino game.
- As a user, I want to be able to check my personal account and gambling history in my account page. 

## Phase2:

### Add New Task: 

In this phase, my reference is: JsonSerializtionDemo from Edx Phase 2.

- As a user, I want to have option to save my account or not before I quit from account menu.
- As a user, I want to have option to select (load) existing account before get into account menu.


## Phase3; 

In this phase, add GUI to my app, and add three user stories:

- As a user, I want to check the histories that I win.
- As a user, I want to check the histories that I lost.
- As a user, I want to have a button to add history to my account when the game is over.
- As a user, I want to be able to clear the history.
- As a user, I want to be able to load and save the state of the application

# Instructions for Grader: 

- You can generate the first required action related to adding Xs to a Y by finished a game
  and press "Add History" button.
- You can generate the second required action check win or loss history by press "History" in 
  Account menu and press "Show Win History" or "Show Loss History".
- You can generate the third required action clear all history by press "History" in
    Account menu and press "Clear History". (Note, it will clear history in your account)
- You can locate my visual component in data folder, and when you finished a game and press
  "Add History" button, an image that used as a note for adding history will pop out.
- You can save the state of my application by press "Save your account" button in Account Menu.
- You can reload the state of my application by press " Select your personal account" in main menu.


## Phase4; 

# Task 2:
Tue Aug 08 22:58:53 PDT 2023
Loss-200.0
Tue Aug 08 22:58:58 PDT 2023
Loss-1000.0
Tue Aug 08 22:59:08 PDT 2023
Loss-100.0
Tue Aug 08 22:59:13 PDT 2023
Loss-150.0
Tue Aug 08 22:59:19 PDT 2023
Loss-100.0
Tue Aug 08 22:59:27 PDT 2023
Win150.0

# Task 3:

For the improvement of my project, I Have to many different panel classes that has similar methods in them. I could 
an abstract class called MainPanel which contain similar methods, and other class panel could extend this MainPanel. 
MainPanel should extend JFrame, and it's subclass will also inherit JFrame methods. It will also inherit the field of 
MainPanel, so I don't have to pass account information over and over when change to a new panel.
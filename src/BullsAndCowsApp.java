import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BullsAndCowsApp {

    /**
     * BullsAndCowsApp does all greetings and settings before game start
     */

    private Game game;

    public static void main(String[] args) {
        BullsAndCowsApp app = new BullsAndCowsApp();
        app.start();
    }

    /**
     * Call printing methods and setting methods
     * Create new Game and call playGame() from Game
     */

    private void start() {
        int codeLength = 4;
        int maxTurns = 7;
        Level gameLevel;
        boolean isFileInput;
        String fileName = "";

        printWelcomeMessage();
        printLevelChoice();
        gameLevel = setLevel(getCommand(3));
        printInputSourceChoice();
        isFileInput = getCommand(2) == 2;
        if (isFileInput) {
            fileName = getFileName();
        }
        /**
         * User are only allowed to change configurations if easy level is selected
         */
        if (gameLevel == Level.Easy) {
            printConfigChoice();
            if (getCommand(2) == 1) {
                codeLength = setCodeLength();
                maxTurns = setMaxTurns();
            }
        }
        System.out.println("---");
        System.out.println("** START GAME **");
        game = new Game(codeLength, maxTurns, gameLevel, isFileInput,fileName);
        game.playGame();
        printHistoryChoice();
        if(getCommand(2) == 1) {
            game.saveHistory();
        }
        System.out.println("** END GAME **");
        printEndMessage();
    }

    /**
     * Print greeting messages and configuration menus
     */

    private void printWelcomeMessage() {
        System.out.println("Welcome to Bulls and Cows game!");
    }

    private void printLevelChoice() {
        System.out.println("== GAME MODE ==");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");
        System.out.println("---");
        System.out.println("Enter number to select game mode:");
    }

    private void printInputSourceChoice() {
        System.out.println("== INPUT SOURCE ==");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.println("---");
        System.out.println("Enter number to select input source:");
    }

    private void printConfigChoice() {
        System.out.println("== Default Configuration ==");
        System.out.println("1. Secret code Length: 4");
        System.out.println("2. Maximum number of turns: 7");
        System.out.println("---");
        System.out.println("Change configuration?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("Enter 1 or 2:");
    }

    private void printHistoryChoice() {
        System.out.println("Save history?");
        System.out.println("---");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("Enter 1 or 2:");
    }

    private void printEndMessage() {
        System.out.println("Thank you for playing. Goodbye!");
    }

    /**
     * Get and validate player inputs
     */

    private int getCommand(int maxCmdNum) {
        int cmd;
        while (true) {
            System.out.print(">> ");
            try {
                cmd = Integer.parseInt(Keyboard.readInput());
                if (cmd < 1 || cmd > maxCmdNum) {
                    throw new IllegalArgumentException();
                }
                return cmd;
            } catch (NumberFormatException e) {
                System.out.println("Enter number only!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command!");
            }
        }
    }

    private int getNumInput() {
        int num;
        while(true) {
            System.out.print(">>");
            try{
                num = Integer.parseInt(Keyboard.readInput());
                if(num < 1) {
                    throw new IllegalArgumentException();
                }
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Enter number only!");
            } catch(IllegalArgumentException e) {
                System.out.println("Must be at least 1!");
            }
        }
    }

    /**
     * Allow user guess based on pre-supplied guesses in file
     */

    private String getFileName() {
        System.out.println("Enter file name: ");
        while(true) {
            System.out.print(">>");
            String fileName = Keyboard.readInput();
            try(Scanner scanner = new Scanner(new File(fileName))) {
                scanner.hasNext();
                return fileName;
            } catch(FileNotFoundException e) {
                System.out.println("File not found!");
            } catch(NoSuchElementException e) {
                System.out.println("Blank file!");
            }
        }
    }

    /**
     * Allow user to choose game mode (difficulty level), input type (keyboard / file), secret code length and maximum number of turns
     */

    private Level setLevel(int cmd) {
        switch (cmd) {
            case 1:
                return Level.Easy;
            case 2:
                return Level.Medium;
            case 3:
                return Level.Hard;
        }
        return null;
    }

    private int setCodeLength() {
        int codeLength;
        System.out.println("Enter secret code length:");
        while(true) {
            codeLength = getNumInput();
            if(codeLength > 10) {
                System.out.println("---");
                System.out.println("Your secret code must not have repeated digits.");
                System.out.println("And there's only 10 digits in this world!");
                System.out.println("Enter again or play this game in other universe (maybe they have " + codeLength + " digits):");
            } else {
                return codeLength;
            }
        }
    }

    private int setMaxTurns() {
        System.out.println("Enter maximum number of turns:");
        return getNumInput();
    }
}

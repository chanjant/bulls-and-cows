import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Game {

    /**
     * Game runs the whole gaming process.
     * Create History to save record in each turn
     */
    private final int CODE_LENGTH, MAX_TURNS;
    private History history;
    private Player human;
    private Player computer;
    private Level gameLevel;
    private String fileName;
    private int turn;
    private boolean hasWinner, isFileInput;
    private Map<Integer, Integer> humanSecretCode, computerSecretCode;


    /**
     * Allow BullsAndCowsApp to pass in configurations to Game through constructor
     */
    public Game(int code_length, int max_turns, Level gameLevel, boolean isFileInput, String fileName) {
        history = new History();
        CODE_LENGTH = code_length;
        MAX_TURNS = max_turns;
        this.gameLevel = gameLevel;
        this.isFileInput = isFileInput;
        this.fileName = fileName;
        turn = 1;
        hasWinner = false;
    }

    /**
     * playGame() will be called in BullsAndCowsApp
     */

    public void playGame() {
        createPlayers();
        System.out.print("Please enter your secret code:");
        humanSecretCode = human.getSecretCode();
        computerSecretCode = computer.getSecretCode();
        System.out.println("---");
        while (!hasWinner && turn <= MAX_TURNS) {
            playTurn();
            turn++;
        }
        System.out.println("---");
        System.out.println(resultToString());
        System.out.println("---");
    }

    /**
     * Create players based on configuration (human - keyboard / file; computer - easy, medium / hard)
     */

    private void createPlayers() {
        human = isFileInput ? new HumanFilePlayer(CODE_LENGTH, fileName) : new HumanPlayer(CODE_LENGTH);
        switch (gameLevel) {
            case Easy:
                computer = new EasyAI(CODE_LENGTH);
                break;
            case Medium:
                computer = new MediumAI(history);
                break;
            case Hard:
                computer = new HardAI(history);
                break;
        }
    }

    /**
     * Check if there is winner already in each turn
     */

    private void playTurn() {
        boolean humanWins;
        boolean computerWins;
        System.out.println("++ Turn " + turn + " ++");
        humanWins = humanTurn();
        System.out.println("---");
        computerWins = computerTurn();
        if (humanWins || computerWins) {
            hasWinner = true;
            String winner = humanWins ? "human" : "computer";
            winner = humanWins && computerWins ? "draw" : winner;
            history.setWinner(winner);
        }
    }

    private boolean humanTurn() {
        boolean humanWin;
        System.out.print("Your guess:");
        Map<Integer, Integer> humanGuess = human.getGuess();
        Record humanRecord = new Record(humanGuess);
        history.addHumanRecords(humanRecord);
        humanWin = hasWon(humanRecord, humanGuess, computerSecretCode);
        System.out.println("Result: " + scoreToString(humanRecord));
        return humanWin;
    }

    private boolean computerTurn() {
        boolean computerWin;
        System.out.print("Computer guess: ");
        Map<Integer, Integer> computerGuess = computer.getGuess();
        System.out.println(convertToString(computerGuess));
        Record computerRecord = new Record(computerGuess);
        history.addComputerRecords(computerRecord);
        computerWin = hasWon(computerRecord, computerGuess, humanSecretCode);
        System.out.println("Result: " + scoreToString(computerRecord));
        return computerWin;
    }

    /**
     * Check score and winning status; save score to record
     * @param record
     * @param guess
     * @param secretCode
     * @return
     */
    private boolean hasWon(Record record, Map<Integer, Integer> guess, Map<Integer, Integer> secretCode) {
        Map<String, Integer> score = human.checkScore(guess, secretCode);
        record.setScore(score);
        return score.get("bulls") == CODE_LENGTH;
    }


    public void saveHistory() {
        String fileName = "";
        while(fileName.isEmpty()) {
            System.out.println("Enter file name:");
            System.out.print(">>");
            fileName = Keyboard.readInput();
        }
        try (PrintWriter file = new PrintWriter(new FileWriter(fileName))) {
            file.println("== Bulls & Cows game result ==");
            file.println("Your code: " + convertToString(humanSecretCode));
            file.println("Computer code: " + convertToString(computerSecretCode));
            for (int i = 0; i < (turn - 1); i++) {
                Record humanRecord = history.getHumanRecords().get(i);
                Record computerRecord = history.getComputerRecords().get(i);
                String humanGuess = convertToString(humanRecord.getGuess());
                String computerGuess = convertToString(computerRecord.getGuess());
                file.println("---");
                file.println("Turn " + (i + 1) + " :");
                file.println("You guessed " + humanGuess + ", scoring " + scoreToString(humanRecord));
                file.println("Computer guessed " + computerGuess + ", scoring " + scoreToString(computerRecord));
            }
            file.println(resultToString());
            System.out.println("Saved!");
        } catch (IOException e) {
            System.out.println("Download failed!");
        }
    }

    /**
     * convert computer generated guess in Map to String for printing
     * convert guesses retrieved from History to write file
     * @param map
     * @return
     */

    private String convertToString(Map<Integer, Integer> map) {
        String str = "";
        Iterator<Integer> itr = map.values().iterator();
        while (itr.hasNext()) {
            str += itr.next();
        }
        return str;
    }

    private String scoreToString(Record record) {
        Map<String, Integer> result = record.getScore();
        int bulls = result.get("bulls");
        int cows = result.get("cows");
        String bullsResult = bulls + (bulls > 1 ? " bulls" : " bull");
        String cowsResult = cows + (cows > 1 ? " cows" : " cow");
        return bullsResult + " and " + cowsResult;
    }

    private String resultToString() {
        String winner = history.getWinner();
        switch (winner) {
            case "human":
                return "You win! :)";
            case "computer":
                return "You lose! :(";
            case "draw":
                return "Draw! :/";
        }
        return null;
    }
}

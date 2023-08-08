import java.io.*;
import java.util.*;

public class HumanFilePlayer extends HumanPlayer {

    private Deque<Map<Integer, Integer>> guesses;
    private Deque<String> fileGuesses;

    public HumanFilePlayer(int codeLength, String fileName) {
        super(codeLength);
        readFile(fileName);
    }

    @Override
    public Map<Integer, Integer> getSecretCode() {
        return super.getGuess();
    }

    /**
     * Return file guesses if available; else, will prompt user to input guess
     *
     * @return
     */
    @Override
    public Map<Integer, Integer> getGuess() {
        return guesses.isEmpty() ? super.getGuess() : getFileGuess();
    }

    private Map<Integer, Integer> getFileGuess() {
        System.out.println(fileGuesses.pollFirst());
        return guesses.pollFirst();
    }

    /**
     * Read given file and store guesses from file to Deque guesses
     *
     * @param fileName
     */
    private void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            guesses = new ArrayDeque<>();
            fileGuesses = new ArrayDeque<>();
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileGuesses.addLast(line);
                Map<Integer, Integer> guess = super.convertToMap(line);
                guesses.addLast(guess);
            }
        } catch (IOException e) {
            System.out.println("Text error!");
        }
    }
}

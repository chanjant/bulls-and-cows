import java.util.Map;

public class Record {
    /**
     * Guesses and score are stored as Map for easier comparison such as by using .retainAll() to find number of bulls and cows
     * guess: key refers to the digit place and value is the digit
     * score: key refers to bulls / cows and value is the count of bulls / cows
     */
    private Map<Integer, Integer> guess;
    private Map<String, Integer>score;

    public Record(Map<Integer, Integer> guess){
        this.guess = guess;
    }

    public Map<Integer, Integer> getGuess() {return guess;}

    public Map<String, Integer> getScore() {return score;}

    public void setScore(Map<String, Integer>score) {
        this.score = score;
    }
}

import java.util.*;

public abstract class Player {

    protected int codeLength;

    public Player(int codeLength) {
        this.codeLength = codeLength;
    }

    public abstract Map<Integer, Integer> getSecretCode();
    public abstract Map<Integer,Integer> getGuess();

    /**
     * checkScore() is needed for HardAI to use this method to generate smarter guess
     * @param guess
     * @param secretCode
     * @return
     */
    public Map<String,Integer> checkScore(Map<Integer,Integer> guess, Map<Integer,Integer> secretCode) {
        Map<String, Integer> score = new HashMap<>();
        Map<Integer, Integer> cows = new HashMap<>(guess);
        Map<Integer, Integer> bulls = new HashMap<>(secretCode);
        cows.values().retainAll(bulls.values());
        bulls.entrySet().retainAll(cows.entrySet());
        score.put("bulls", bulls.size());
        score.put("cows", cows.size() - bulls.size());
        return score;
    }
}

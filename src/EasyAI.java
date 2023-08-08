import java.util.HashMap;
import java.util.Map;

public class EasyAI extends Player {

    /**
     * When against EasyAI, user are allowed to change code length
     * @param codeLength
     */
    public EasyAI(int codeLength) {
        super(codeLength);
    }

    @Override
    public Map<Integer, Integer> getSecretCode() {
        return generateGuess();
    }

    @Override
    public Map<Integer, Integer> getGuess() {
        return generateGuess();
    }

    /**
     * Generate random guess with no repeating digits
     * @return
     */
    public Map<Integer, Integer> generateGuess() {
        Map<Integer, Integer> newGuess = new HashMap<>();
        int num = (int) (Math.random() * 10);
        while (newGuess.size() < super.codeLength) {
            if (!newGuess.containsValue(num)) {
                newGuess.put(newGuess.size() + 1, num);
            }
            num = (int) (Math.random() * 10);
        }
        return newGuess;
    }

}

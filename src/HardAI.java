import java.util.*;

public class HardAI extends MediumAI {

    public HardAI(History history) {
        super(history);
    }

    @Override
    public Map<Integer, Integer> getSecretCode() {
        return super.getSecretCode();
    }

    /**
     * generateGuess() uses MediumAI's generateGuess() to generate new guess and compare new guess with past guesses
     */
    @Override
    public Map<Integer, Integer> generateGuess() {
        Map<Integer, Integer> newGuess = super.generateGuess();
        List<Record> computerRecords = history.getComputerRecords();
        int size = computerRecords.size();
        if (size == 0) {
            return newGuess;
        }
        boolean isMatch = false;
        while(!isMatch) {
            isMatch = true;
            for (Record pastRecord : computerRecords) {
                Map<Integer, Integer> pastGuess = pastRecord.getGuess();
                Map<String, Integer> pastScore = pastRecord.getScore();
                Map<String, Integer> newScore = super.checkScore(newGuess, pastGuess);
                if (!newScore.equals(pastScore)) {
                    isMatch = false;
                    newGuess = super.generateGuess();
                    break;
                }
            }
        }
        return newGuess;
    }
}

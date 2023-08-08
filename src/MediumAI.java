import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediumAI extends EasyAI {

    /**
     * History is needed to make sure MediumAI won't generate guesses that are guessed before
     */
    protected History history;

    /**
     * When against MediumAI, code length is fixed at default (4)
     * @param history
     */
    public MediumAI(History history) {
        super(4);
        this.history = history;
    }
    @Override
    public Map<Integer, Integer> getSecretCode() {
        return super.generateGuess();
    }
    /**
     * MediumAI retrieves past guess record from History and make new different guess
     * @return
     */
    @Override
    public Map<Integer, Integer> generateGuess() {
        Map<Integer, Integer> newGuess = new HashMap<>();
        List<Record> computerRecords = history.getComputerRecords();
        boolean isNotRepeating = false;
        while(!isNotRepeating) {
            newGuess = super.generateGuess();
            isNotRepeating = true;
            for(int i = 0; i < computerRecords.size(); i++){
                Map<Integer, Integer> pastRecord = computerRecords.get(i).getGuess();
                if(pastRecord.equals(newGuess)) {
                    isNotRepeating = false;
                    break;
                }
            }
        }
        return newGuess;
    }
}

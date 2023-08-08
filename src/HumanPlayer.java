import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HumanPlayer extends Player {

    public HumanPlayer(int codeLength) {
        super(codeLength);
    }

    @Override
    public Map<Integer,Integer> getSecretCode() {
        return getGuess();
    }

    /**
     * Keep prompting user for input until input is valid
     */
    @Override
    public Map<Integer,Integer> getGuess() {
        Map<Integer, Integer> inputMap = new HashMap<>();
        String input;
        boolean notValid = true;
        while(notValid) {
            System.out.print("\n>> ");
            input = Keyboard.readInput();
            if(isValidInput(input)) {
                inputMap = convertToMap(input);
                notValid = isRepeatingDigits(inputMap);
            }
        }
        return inputMap;
    }

    /**
     * Validate user input
     */

    private boolean isValidInput(String input) {
        try{
            Integer.parseInt(input);
            if(input.length() != codeLength){
                throw new IllegalArgumentException("Must be " + codeLength + " digits!");
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.print("Enter numbers only!");
        } catch(IllegalArgumentException e) {
            System.out.print(e.getMessage());
        }
        return false;
    }

    private boolean isRepeatingDigits(Map<Integer, Integer> inputMap) {
        Set checkSet = new HashSet(inputMap.values());
        if (inputMap.values().size() != checkSet.size()) {
            System.out.print("Repeated digits!");
            return true;
        }
        return false;
    }

    /**
     * convert user input in String to Map
     */

    public Map<Integer, Integer> convertToMap(String str) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < codeLength; i++) {
                int digit = Integer.parseInt(str.split("")[i]);
                int position = i + 1;
                map.put(position, digit);
        }
        return map;
    }
}

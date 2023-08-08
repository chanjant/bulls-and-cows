import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {
    private List<Record> humanRecords, computerRecords;
    private String winner;

    public History() {
        humanRecords = new ArrayList<>();
        computerRecords = new ArrayList<>();
        winner = "draw";
    }

    public void addHumanRecords(Record record) {humanRecords.add(record);}

    public void addComputerRecords(Record record) {computerRecords.add(record);}

    public List<Record> getComputerRecords() {return computerRecords;}

    public List<Record> getHumanRecords() {return humanRecords;}

    public void setWinner(String winner) {
        this.winner = winner;
    }
    public String getWinner(){return this.winner;}

}

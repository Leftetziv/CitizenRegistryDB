import java.util.HashSet;
import java.util.Set;

public class MitrooPoliton {

    private Set<Politis> MitrooPoliton = new HashSet<>();

    public MitrooPoliton() {
        DatabaseConnector.setMitrooPolitonState();
    }

    public boolean addPolitis(Politis politis) {
        return false;
    }

    public boolean removeRecord(String arithmosTautotitas) {
        return false;
    }

    public boolean updateRecord(Politis politis) {
        return false;
    }

    public Set<Politis> searchRecords(Politis politis) {
        return null;
    }

    public void printRecords() {

    }
}

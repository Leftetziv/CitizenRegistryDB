import java.util.HashSet;
import java.util.Set;

public class CitizenRegistry {

    private Set<Citizen> MitrooPoliton = new HashSet<>();

    public CitizenRegistry() {
        DatabaseConnector.setMitrooPolitonState();
    }

    public boolean addPolitis(Citizen citizen) {
        return false;
    }

    public boolean removeRecord(String arithmosTautotitas) {
        return false;
    }

    public boolean updateRecord(Citizen citizen) {
        return false;
    }

    public Set<Citizen> searchRecords(Citizen citizen) {
        return null;
    }

    public void printRecords() {

    }
}

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CitizenRegistry {

    public CitizenRegistry() {
        DatabaseConnector.setCitizenRegistryState();
    }

    public boolean addCitizen(Citizen citizen) {
        return false;
    }

    public boolean removeCitizen(String id) {
        return false;
    }

    public boolean updateCitizen(Citizen citizen) {
        return false;
    }

    public Optional<Set<Citizen>> searchCitizens(Citizen citizen) {
        return null;
    }

    public void printCitizens() {

    }
}

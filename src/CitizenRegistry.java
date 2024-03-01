import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CitizenRegistry {

    public CitizenRegistry() {
        DatabaseConnector.setCitizenRegistryState();
    }

    public boolean citizenExists(String id) {
        return DatabaseConnector.checkIfCitizenExists(id);
    }

    public boolean addCitizen(Citizen citizen) {
        return DatabaseConnector.createCitizen(citizen);
    }

    public boolean removeCitizen(String id) {
        return DatabaseConnector.deleteCitizen(id);
    }

    public boolean updateCitizen(Citizen citizen) {
        return DatabaseConnector.updateCitizen(citizen);
    }

    public Optional<Set<Citizen>> searchCitizens(Citizen citizen) {
        return null;
    }


    public void printCitizens() {

    }
}

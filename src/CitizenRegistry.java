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

    public Citizen getCitizen(String id) {
        return DatabaseConnector.getCitizen(id);
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

    public void searchAndPrintCitizens(Citizen citizen) {
       Set<Citizen> citizens = DatabaseConnector.searchCitizen(citizen);

       if (citizens.isEmpty()) {
           System.out.println("No citizens were found given the criteria");
           return;
       }

       for (Citizen c: citizens) {
           System.out.println(c);
       }
    }

    public void printCitizens() {
        Set<Citizen> citizens = DatabaseConnector.getAllCitizens();

        if (citizens.isEmpty()) {
            System.out.println("No citizens found");
            return;
        }

        for (Citizen c: citizens) {
            System.out.println(c);
        }

    }
}

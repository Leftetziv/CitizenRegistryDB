import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static void main(String[] args) {

        CitizenRegistry citizenRegistry = new CitizenRegistry();

        System.out.println("Menu for Citizen Registry");
        int choice = 0;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("\nPress 1 for new record");
            System.out.println("Press 2 to delete record");
            System.out.println("Press 3 to update record");
            System.out.println("Press 4 to search records");
            System.out.println("Press 5 for printing all records");

            try{
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERROR. Did not give an integer.");
            }

            switch (choice) {
                case 1: addCitizen(input); break;
                case 2: deleteCitizen(input); break;
                case 3: updateCitizen(input); break;
                case 4: searchCitizens(input); break;
                case 5: printCitizens(input); break;
            }
        } while (choice >= 1 && choice <=5);

        input.close();
        System.out.println("Exiting program");
    }

    private static String readId(Scanner input, String msg) throws Exception {
        String id = getStringInputRequired(input, msg);
        if (id.length() != 1 ) {
            throw new Exception("ERROR. ID must have exactly 8 digits");
        }

        return id;
    }

    private static String readGender(Scanner input, String msg) throws Exception {
        String gender = getStringInputRequired(input, msg);

        if (!gender.equals("m") && !gender.equals("f")) {
            throw new Exception("ERROR. Gender can only be entered as \'m\' or \'f\'");
        }

        return gender;
    }

    private static String readDob(Scanner input, String msg) throws Exception {
        String dob = getStringInputRequired(input, msg);

        // Define the regex pattern for the date format
        String regex = "^(0[1-9]|[12]\\d|3[01])-(0[1-9]|1[0-2])-(\\d{4})$"; // Matches DD-MM-YYYY format

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(dob);

        // Check if the input matches the regex pattern
        if (!matcher.matches()) {
            throw new Exception("ERROR. Invalid date format. Please enter in the format DD-MM-YYYY.");
        }

        return dob;
    }

    private static String readAfm(Scanner input, String msg) throws Exception {
        String afm = getStringInput(input, msg);

        if (afm.length() != 9 && afm.length() != 0) {
            throw new Exception("ERROR. AFM must have exactly 9 digits");
        }

        return afm;
    }

    private static void addCitizen(Scanner input) {
        try {
            String id = readId(input, "Please enter ID");
            String firstName = getStringInputRequired(input, "Please enter first name");
            String lastName = getStringInputRequired(input, "Please enter last name");
            String gender = readGender(input, "Please enter gender (m/f)");
            String dob = readDob(input, "Please enter date of birth");
            String afm = readAfm(input, "Please enter AFM");
            String address = getStringInput(input, "Please enter address");
            //todo make own class

            Citizen c = new Citizen(
                    id,
                    firstName,
                    lastName,
                    gender,
                    dob,
                    afm,
                    address
            );

            System.out.println(dob);

            //citizenRegistry.addCitizen(c)
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Citizen record was not saved.");
        }
    }

    private static void deleteCitizen(Scanner input) {
        try {
            String id = readId(input, "Please enter ID for deletion");

            System.out.println(id);

            //mitrooPoliton.addRecord(politis)
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Citizen record deletion failed.");
        }
    }

    private static void updateCitizen(Scanner input) {
        try {
            String id = readId(input, "Please enter ID to update record");

            //anazitisi sto an uparxei o arithmosTautotitas. an den yparxei prepei na skaei exception

            String firstName = getStringInputRequired(input, "Please enter new first name (old value: ");
            String lastName = getStringInputRequired(input, "Please enter new last name (old value: ");
            String gender = readGender(input, "Please enter new gender (m/f) (old value: ");
            String dob = readDob(input, "Please enter new date of birth (old value: ");
            String afm = readAfm(input, "Please enter new AFM (old value: ");
            String address = getStringInput(input, "Please enter new address (old value: ");
            //todo make own class

            Citizen c = new Citizen(
                    id,
                    firstName,
                    lastName,
                    gender,
                    dob,
                    afm,
                    address
            );


            //mitrooPoliton.addRecord(politis)
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Citizen record was not updated.");
        }
    }

    private static void searchCitizens(Scanner input) {
        //read data from user

        //mitrooPoliton.addRecord
    }

    private static void printCitizens(Scanner input) {
        //read data from user

        //mitrooPoliton.addRecord
    }

//    private static int getIntInput(Scanner input, String msg) {
//        System.out.println(msg);
//
//        int value;
//
//        try{
//            value = input.nextInt();
//        } catch (InputMismatchException e) {
//            System.out.println("ERROR. Did not provide an integer.");
//            input.next();
//            throw e;
//        }
//
//        return value;
//    }

    private static String getStringInputRequired(Scanner input, String msg) throws Exception {
        System.out.println(msg);

        String value = input.nextLine();

        if (value.isEmpty()) {
            throw new Exception("ERROR. Field is mandatory.");
        }

        return value;
    }

    private static String getStringInput(Scanner input, String msg) {
        System.out.println(msg);

        String value = input.nextLine();

        if (value.isEmpty()) {
            return "";
        }

        return value;
    }

}
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    public static void main(String[] args) {

        MitrooPoliton mitrooPoliton = new MitrooPoliton();

        // todo new method
        boolean exists = DatabaseConnector.existsDatabase();
        System.out.println("does db exists?: " + exists);
        if (!exists) DatabaseConnector.createDatabaseTables();
        //

        System.out.println("Menu for Mitroo Politon");
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
                case 1: addRecord(input); break;
                case 2: deleteRecord(input); break;
                case 3: updateRecord(input); break;
                case 4: searchRecords(input); break;
                case 5: printRecords(input); break;
            }
        } while (choice >= 1 && choice <=5);

        input.close();
        System.out.println("Exiting program");
    }

    private static String readArithmosTautotitas(Scanner input, String msg) throws Exception {
        String arithmosTautotitas = getStringInputRequired(input, msg);
        if (arithmosTautotitas.length() != 1 ) {
            throw new Exception("ERROR. Arithmos Tautotitas must have exactly 8 digits");
        }

        return arithmosTautotitas;
    }

    private static String readFylo(Scanner input, String msg) throws Exception {
        String fylo = getStringInputRequired(input, msg);

        if (!fylo.equals("m") && !fylo.equals("f")) {
            throw new Exception("ERROR. Fylo can only be entered as \'m\' or \'f\'");
        }

        return fylo;
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

    private static void addRecord(Scanner input) {
        try {
            String arithmosTautotitas = readArithmosTautotitas(input, "Please enter Arithmo Tautotitas");
            String onoma = getStringInputRequired(input, "Please enter Onoma");
            String eponymo = getStringInputRequired(input, "Please enter Eponymo");
            String fylo = readFylo(input, "Please enter Fylo (m/f)");
            String dob = readDob(input, "Please enter Date of Birth");
            String afm = readAfm(input, "Please enter AFM");
            String address = getStringInput(input, "Please enter Address");
            //todo make own class

            Politis p = new Politis(
                    arithmosTautotitas,
                    onoma,
                    eponymo,
                    fylo,
                    dob,
                    afm,
                    address
            );

            System.out.println(dob);

            //mitrooPoliton.addRecord(politis)
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Record was not saved.");
        }
    }

    private static void deleteRecord(Scanner input) {
        try {
            String arithmosTautotitas = readArithmosTautotitas(input, "Please enter Arithmo Tautotitas for deletion");

            System.out.println(arithmosTautotitas);

            //mitrooPoliton.addRecord(politis)
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Record deletion failed.");
        }
    }

    private static void updateRecord(Scanner input) {
        try {
            String arithmosTautotitas = readArithmosTautotitas(input, "Please enter Arithmo Tautotitas to update record");

            //anazitisi sto an uparxei o arithmosTautotitas. an den yparxei prepei na skaei exception

            String onoma = getStringInputRequired(input, "Please enter new Onoma (old value: ");
            String eponymo = getStringInputRequired(input, "Please enter new Eponymo (old value: ");
            String fylo = readFylo(input, "Please enter new Fylo (m/f) (old value: ");
            String dob = readDob(input, "Please enter new Date of Birth (old value: ");
            String afm = readAfm(input, "Please enter new AFM (old value: ");
            String address = getStringInput(input, "Please enter new Address (old value: ");
            //todo make own class

//            Politis p = new Politis(
//                    arithmosTautotitas,
//                    onoma,
//                    eponymo,
//                    fylo,
//                    dob,
//                    afm,
//                    address
//            );


            //mitrooPoliton.addRecord(politis)
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Record was not updated.");
        }
    }

    private static void searchRecords(Scanner input) {
        //read data from user

        //mitrooPoliton.addRecord
    }

    private static void printRecords(Scanner input) {
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
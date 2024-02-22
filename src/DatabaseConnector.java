import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";
    private static final String dbName = "mitroo_politon";

    private static String getFileContent(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Could not read content from file: " + path);
        }

        return null;
    }

    private static Connection getConnection(String dbName) {
        String URL = url;
        if (dbName != null) {
            URL += dbName;
        }

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            System.out.println("Could not establish connection with DB HHHHH");
            System.out.println(e.getMessage());
            System.out.println(e);
        }

        return con;
    }

    private static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Could not close connection with DB correctly");
            System.out.println(e.getMessage());
        }
    }

    public static boolean existsDatabase() {
        boolean found = false;
        Connection con = getConnection(null);
        if (con != null) {
            try {
                ResultSet rs = con.getMetaData().getCatalogs();
                while (rs.next()) {
                    String catalog = rs.getString(1);
                    if (catalog.equals(dbName)) {
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Could not recieve metadata from DBMS");
                System.out.println(e.getMessage());
            } finally {
                closeConnection(con);
            }
        }

        return found;
    }

    public static boolean createDatabaseTables() {
        boolean created = false;
        Connection con = getConnection(null);

        if (con != null) {
            try {
                String stmt = "create database " + dbName + ";";
                Statement st = con.createStatement();
                st.execute(stmt);
                created = true;
            } catch (Exception e) {
                System.out.println("Could not create the database");
                System.out.println(e.getMessage());
            } finally {
                closeConnection(con);
            }
        }

        if (created) {
            return createTables();
        }

        return created;
    }

    private static boolean createTables () {
        boolean created = false;
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                con.setAutoCommit(false);
                Statement st = con.createStatement();

                String stmt = getFileContent("sql/create_table_politis.sql");
                st.execute(stmt);

                con.commit();
                created = true;
            } catch (Exception e) {
                System.out.println("Could not create the tables");
                System.out.println(e.getMessage());
                rollBack(con);
            } finally {
                closeConnection(con);
            }
        }

        return created;
    }


    private static void rollBack(Connection con) {
        try {
            con.rollback();
        } catch (Exception e) {
            System.out.println("something went wrong while trying to rollback the connection");
        }
    }

    public static boolean insertPolitis(Politis politis) {
        return false;
    }



}

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DatabaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";
    private static final String dbName = "citizen_registry";

    private static String getFileContent(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Could not read content from file: " + path);
        }

        return null;
    }

    public static Connection getConnection(String dbName) {
        String URL = url;
        if (dbName != null) {
            URL += dbName;
        }

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            System.out.println("Could not establish connection with DB");
            System.out.println(e.getMessage());
            System.out.println(e);
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Could not close connection with DB correctly");
            System.out.println(e.getMessage());
        }
    }

    public static void setCitizenRegistryState() {
        boolean exists = existsDatabase();
        System.out.println("Does db exists? " + exists);
        if (!exists) createDatabaseTables();
    }

    private static boolean existsDatabase() {
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

    private static boolean createDatabaseTables() {
        boolean created = false;
        Connection con = getConnection(null);

        if (con != null) {
            try {
                String stmt = "create database " + dbName + ";";
                Statement st = con.createStatement();
                st.execute(stmt);
                created = true;
                System.out.println("Created DB");
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

                String stmt = getFileContent("sql/create_table_citizen.sql");
                st.execute(stmt);

                con.commit();
                created = true;
                System.out.println("Created tables");
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


    public static void rollBack(Connection con) {
        try {
            con.rollback();
        } catch (Exception e) {
            System.out.println("something went wrong while trying to rollback the connection");
        }
    }

    public static boolean checkIfCitizenExists(String id){
        boolean exists = false;
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                String stmt = getFileContent("sql/check_if_citizen_exists.sql");
                PreparedStatement st = con.prepareStatement(stmt);
                st.setString(1,id);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    exists = true;
                }
            } catch (Exception e) {
                System.out.println("Could not add new citizen to registry");
                System.out.println(e.getMessage());
            } finally {
                closeConnection(con);
            }
        }

        return exists;
    }

    public static boolean createCitizen(Citizen citizen) {
        boolean created = false;
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                con.setAutoCommit(false);
                String stmt = getFileContent("sql/insert_citizen.sql");
                PreparedStatement st = con.prepareStatement(stmt);

                st.setString(1,citizen.getId());
                st.setString(2,citizen.getFirstName());
                st.setString(3,citizen.getLastName());
                st.setString(4,citizen.getGender());
                st.setString(5,citizen.getDob());
                st.setString(6,citizen.getAfm());
                st.setString(7,citizen.getAddress());

                st.execute();
                con.commit();

                created = true;
            } catch (Exception e) {
                System.out.println("Could not add new citizen to registry");
                System.out.println(e.getMessage());
                rollBack(con);
            } finally {
                closeConnection(con);
            }
        }

        return created;
    }

    public static boolean deleteCitizen(String id) {
        boolean deleted = false;
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                con.setAutoCommit(false);
                String stmt = getFileContent("sql/delete_citizen.sql");
                PreparedStatement st = con.prepareStatement(stmt);

                st.setString(1, id);

                st.execute();
                con.commit();

                deleted = true;
            } catch (Exception e) {
                System.out.println("Could not add new citizen to registry");
                System.out.println(e.getMessage());
                rollBack(con);
            } finally {
                closeConnection(con);
            }
        }

        return deleted;
    }

    public static boolean updateCitizen(Citizen citizen) {
        boolean updated = false;
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                con.setAutoCommit(false);
                String stmt = getFileContent("sql/update_citizen.sql");
                PreparedStatement st = con.prepareStatement(stmt);

                st.setString(1,citizen.getFirstName());
                st.setString(2,citizen.getLastName());
                st.setString(3,citizen.getGender());
                st.setString(4,citizen.getDob());
                st.setString(5,citizen.getAfm());
                st.setString(6,citizen.getAddress());
                st.setString(7,citizen.getId());

                st.execute();
                con.commit();

                updated = true;
            } catch (Exception e) {
                System.out.println("Could not update citizen to registry");
                System.out.println(e.getMessage());
                rollBack(con);
            } finally {
                closeConnection(con);
            }
        }

        return updated;
    }

    public static Set<Citizen> searchCitizen(Citizen citizen) {
        Set<Citizen> citizens = new HashSet<>();
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                String stmt = getFileContent("sql/search_citizen.sql");
                PreparedStatement st = con.prepareStatement(stmt);

                st.setString(1, citizen.getId());
                st.setString(2, citizen.getFirstName());
                st.setString(3, citizen.getLastName());
                st.setString(4, citizen.getGender());
                st.setString(5, citizen.getDob());
                st.setString(6, citizen.getAfm());
                st.setString(7, citizen.getAddress());

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    Citizen c = new Citizen();
                    c.setId(rs.getString(1));
                    c.setFirstName(rs.getString(2));
                    c.setLastName(rs.getString(3));
                    c.setGender(rs.getString(4));
                    c.setDob(rs.getString(5));
                    c.setAfm(rs.getString(6));
                    c.setAddress(rs.getString(7));

                    citizens.add(c);
                }
            } catch (Exception e) {
                System.out.println("Could not search citizens registry");
                System.out.println(e.getMessage());
            } finally {
                closeConnection(con);
            }
        }

        return citizens;
    }

    public static Set<Citizen> getAllCitizens() {
        Set<Citizen> citizens = new HashSet<>();
        Connection con = getConnection(dbName);

        if (con != null) {
            try {
                String stmt = getFileContent("sql/find_all_citizen.sql");
                PreparedStatement st = con.prepareStatement(stmt);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    Citizen c = new Citizen();
                    c.setId(rs.getString(1));
                    c.setFirstName(rs.getString(2));
                    c.setLastName(rs.getString(3));
                    c.setGender(rs.getString(4));
                    c.setDob(rs.getString(5));
                    c.setAfm(rs.getString(6));
                    c.setAddress(rs.getString(7));

                    citizens.add(c);
                }
            } catch (Exception e) {
                System.out.println("Could not fetch citizens registry");
                System.out.println(e.getMessage());
            } finally {
                closeConnection(con);
            }
        }

        return citizens;

    }
}

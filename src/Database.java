import java.sql.*;

public class Database {
    //This method executes a query and returns the number of albums for the artist with name artistName
    private static Connection con;
    public int getTitles(String artistName) {
        int titleNum = 0;
        //Implement this method
        //Prevent SQL injections!
        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {
        //Implement this method
        try {
            // Initialise Driver
            Class.forName("org.postgresql.Driver");
            // Open connection
            con = DriverManager.getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            return con.isValid(5); //5 sec timeout
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
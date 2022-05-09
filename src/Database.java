import java.sql.*;

public class Database {
    //This method executes a query and returns the number of albums for the artist with name artistName
    private static Connection con;
    public int getTitles(String artistName) {
        try {
            int titleNum = 0;
            //Implement this method
            String sql = "SELECT title FROM album " +
                    "INNER JOIN artist ON album.artistid = artist.artistid " +
                    "WHERE artist.name = ?";
            //Prevent SQL injections!
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "'"+artistName+"'");

            //Clean up environment
            pstmt.close();
            con.close();

            return titleNum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
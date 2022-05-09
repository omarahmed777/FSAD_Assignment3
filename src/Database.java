import java.sql.*;

public class Database {
    private static Connection con;

    //This method executes a query and returns the number of albums for the artist with name artistName
    public int getTitles(String artistName) {
        int titleNum = 0;
        try {
            String sql = "SELECT title FROM album " +
                    "INNER JOIN artist ON album.artistid = artist.artistid " +
                    "WHERE artist.name = ?";
            // Prevent SQL injections!
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, artistName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
                titleNum++;
            // Clean up environment
            rs.close();
            pstmt.close();
            return titleNum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titleNum;
    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {
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
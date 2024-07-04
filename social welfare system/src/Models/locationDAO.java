package Models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Resources.locationDTO;

public class locationDAO extends Connect {
    private static locationDAO instance;

    // Private constructor to ensure a single instance
    private locationDAO() throws SQLException {
    }

    public static locationDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new locationDAO();
        }

        return instance;
    }

    public int insertLocation(locationDTO location) throws SQLException {
        try {
            String query = "INSERT INTO location(location,location_code) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, location.getLocation());
            preparedStatement.setString(2,location.getLocation_code());
           
            preparedStatement.executeUpdate();

            // Get the generated location ID
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            int locationId = -1;
            if (generatedKeys.next()) {
                locationId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve location ID.");
            }

            return locationId;
        } finally {
            // Close resources if needed
            // Handle exceptions if needed
        }
    }
   

}

package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Resources.rescue_systemDTO;

public class rescue_systemDAO extends Connect{
   
    private static rescue_systemDAO instance;

    public static int rescue(String rescueName, int rescueAge, String rescueGender, String healthStatus, Date currDate,
            int user_id) throws SQLException {
        try {
            // Create an instance of usersDAO
            usersDAO userDao = usersDAO.getInstance();

            // Fetch the orphanage details based on the location
            String userLocation = getLocationForUser(user_id); // Implement this method to get the location of the user

            // Get the orphanage Register_num and location_id based on the location
            int orphanageRegisterNum = userDao.getOrphanageRegisterNum(userLocation);
            int orphanageLocationId = userDao.getLocationId(userLocation);

            // Insert into the rescue_system table
            String rescueQuery = "INSERT INTO rescue_system(R_name, R_age, R_gender, Health_status, Joining_date, Register_num, Location_id, User_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement rescueStatement = con.prepareStatement(rescueQuery);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            rescueStatement.setString(1, rescueName);
            rescueStatement.setInt(2, rescueAge);
            rescueStatement.setString(3, rescueGender);
            rescueStatement.setString(4, healthStatus);
            rescueStatement.setString(5, format.format(currDate)); // Convert java.util.Date to java.sql.Date
            rescueStatement.setInt(6, orphanageRegisterNum);
            rescueStatement.setInt(7, orphanageLocationId);
            rescueStatement.setInt(8, user_id);

            return rescueStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("hi "+e);
        } 
        return 0;
    }
    public static String getLocationForUser(int user_id) throws SQLException {
        // String query = "SELECT Location FROM users WHERE User_id = ?";
        String query = "select location from users where user_id = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Location");
            } else {
                throw new SQLException("Failed to retrieve location for user with ID " + user_id);
            }
        }
    }
    public static rescue_systemDAO getInstance() throws SQLException{
        if(instance==null){
            instance=new rescue_systemDAO();
        }
        return instance;
    }
    public List<rescue_systemDTO> getRescuedIndividuals() throws SQLException {
        List<rescue_systemDTO> rescuedIndividuals = new ArrayList<>();
    
        try {
            String query = "SELECT R_id, R_name, R_age, R_gender, Health_status, Joining_date, User_id, Register_num, Location_id FROM rescue_system";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                int rescueId = resultSet.getInt("R_id");
                String rescueName = resultSet.getString("R_name");
                int rescueAge = resultSet.getInt("R_age");
                String rescueGender = resultSet.getString("R_gender");
                String healthStatus = resultSet.getString("Health_status");
                Date joiningDate = resultSet.getDate("Joining_date");
                int userId = resultSet.getInt("User_id");
                int registerNum = resultSet.getInt("Register_num");
                int locationId = resultSet.getInt("Location_id");
    
                System.out.println("Rescue ID: " + rescueId);
                System.out.println("Rescue Name: " + rescueName);
                System.out.println("Rescue Age: " + rescueAge);
                System.out.println("Rescue Gender: " + rescueGender);
                System.out.println("Health Status: " + healthStatus);
                System.out.println("Joining Date: " + joiningDate);
                System.out.println("User ID: " + userId);
                System.out.println("Register Num: " + registerNum);
                System.out.println("Location ID: " + locationId);
                System.out.println("------------------------------");
    
                rescue_systemDTO rescue = new rescue_systemDTO(rescueId, rescueName, rescueAge, rescueGender, healthStatus, joiningDate, userId, registerNum, locationId);
                rescuedIndividuals.add(rescue);
            }
        } finally {
            // Close resources if needed
            // Handle exceptions if needed
        }
    
        return rescuedIndividuals;
    }
    private void increaseOrphanageTotalStrength(int locationId) throws SQLException {
        // Check if there is any orphanage with the same location
        String orphanageQuery = "SELECT Orphanage_id, Total_Strength FROM orphanage WHERE Location_id = ?";
        try (PreparedStatement orphanageStatement = con.prepareStatement(orphanageQuery)) {
            orphanageStatement.setInt(1, locationId);
            ResultSet orphanageResultSet = orphanageStatement.executeQuery();
    
            if (orphanageResultSet.next()) {
                int orphanageId = orphanageResultSet.getInt("Orphanage_id");
                int totalStrength = orphanageResultSet.getInt("Total_Strength");
    
                // Increase the total strength of the orphanage
                int updatedTotalStrength = totalStrength + 1;
                String updateOrphanageQuery = "UPDATE orphanage SET Total_Strength = ? WHERE Orphanage_id = ?";
                try (PreparedStatement updateOrphanageStatement = con.prepareStatement(updateOrphanageQuery)) {
                    updateOrphanageStatement.setInt(1, updatedTotalStrength);
                    updateOrphanageStatement.setInt(2, orphanageId);
                    updateOrphanageStatement.executeUpdate();
                }
            }
        }
    

}
}
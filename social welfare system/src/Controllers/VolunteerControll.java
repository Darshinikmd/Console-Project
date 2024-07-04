package Controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import Models.donationDAO;
import Models.rescue_systemDAO;
import Models.usersDAO;

public class VolunteerControll {
     public int donate(int user_id, String item_name, int quantity, Date date) throws SQLException {
            // Assuming that the 'donate' method is present in your 'UserControll' class.
            return addDonation(user_id, item_name, quantity, date);
        }
        public int addDonation(int user_id, String item_name, int quantity, Date date) throws SQLException {
            try {
                // Create an instance of usersDAO
                usersDAO userDao = usersDAO.getInstance();
        
                // Call the addDonation method in your usersDAO
                return donationDAO.addDonation(user_id,item_name, quantity, date);
            } catch (SQLException e) {
                
                e.printStackTrace();
                throw e; 
            }
           
        }
        public int rescue(String rescueName, int rescueAge, String rescueGender, String healthStatus,Date currDate, int user_id) throws SQLException, ParseException {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Date parsedDate = dateFormat.parse(currDate);
                // Create an instance of usersDAO
                rescue_systemDAO userDao = rescue_systemDAO.getInstance();
        
                // Call the rescue method in your usersDAO
                return rescue_systemDAO.rescue(rescueName, rescueAge, rescueGender, healthStatus,currDate, user_id);
            } catch (SQLException e) {
                // Handle exceptions appropriately
                e.printStackTrace();
                throw e; // Re-throw the exception if needed
            }
        }
        
}

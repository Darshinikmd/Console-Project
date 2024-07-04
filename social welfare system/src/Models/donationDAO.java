package Models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class donationDAO extends Connect {

   
 public static int addDonation(int user_id, String item_name, int quantity, Date date) throws SQLException {
        String donationQuery = "INSERT INTO donation(User_id, Item_name,quantity,Date) VALUES (?, ?, ?, ?)";
        PreparedStatement donationStatement = con.prepareStatement(donationQuery);

        donationStatement.setInt(1, user_id);
        donationStatement.setString(2, item_name);

        donationStatement.setInt(3, quantity);
        donationStatement.setDate(4, (java.sql.Date) date);
        int result = donationStatement.executeUpdate();

        return result;
    }
   
}
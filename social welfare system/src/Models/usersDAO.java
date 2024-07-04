package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Resources.locationDTO;
import Resources.loginDTO;
import Resources.usersDTO;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

public class usersDAO extends Connect {
    private static usersDAO instance;
    PreparedStatement getsignUp, getlogSign;
   
    
    public usersDAO() throws SQLException {
        super();
        getsignUp = con.prepareStatement("INSERT INTO users(User_name,Email,Contact_no,Role,Location)Values(?,?,?,?,?)");
        getlogSign = con.prepareStatement("INSERT INTO login(Email,Password,User_id)Values(?,?,?)");
        isId = con.prepareStatement("select User_id from Users where Contact_no=?");
        
    }
    //usersDAO usd=new usersDAO();
        static PreparedStatement isId;

    public static usersDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new usersDAO();
        }
        return instance;
    }

    

    public static int getId(String contact) throws SQLException {
        isId.setString(1, contact);
        ResultSet rs = isId.executeQuery();
        int D = 0;
        if (rs.next()) {
            D = rs.getInt(1);
        }
        return D;

    }

    public int getsignUp2(loginDTO tummy, int j) throws SQLException {
        getlogSign.setString(1, tummy.getEmail());
        getlogSign.setString(2, tummy.getPassword());
        getlogSign.setInt(3, j);
        int ps = getlogSign.executeUpdate();
        return j;

    }

    public int getIdByContact(String contact) throws SQLException {
        try {
            String query = "SELECT User_id FROM users WHERE Contact_no=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, contact);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("User_id");
            }

            return -1; // Return -1 if user ID is not found
        } finally {
            // Close resources if needed
            // Handle exceptions if needed
        }
    }

    public int getsignUp3(usersDTO user) throws SQLException {
        try {

            // Insert into location table
            locationDAO locationDao = locationDAO.getInstance();
            locationDTO locat = new locationDTO();
            locat.setLocation(user.getLocation());
            int locationId = locationDao.insertLocation(locat);

            // Insert into users table with the obtained location ID
            String userQuery = "INSERT INTO users(User_name, Email, Password, Location_id, Contact_no, Role, Register_num) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement userStatement = con.prepareStatement(userQuery);
            userStatement.setString(1, user.getUser_name());
            userStatement.setString(2, user.getEmail());
            userStatement.setString(3, user.getPassword());
            userStatement.setInt(4, locationId);
            userStatement.setString(5, user.getContact_no());
            userStatement.setString(6, user.getRole());
            userStatement.setInt(7, user.getRegister_num());

            return userStatement.executeUpdate();
        } finally {
            // Close resources if needed
            // Handle exceptions if needed
        }
    }

    
   
    
    private String getLocationForUser(int user_id) throws SQLException {
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

    

    

    public int getOrphanageRegisterNum(String location) throws SQLException {
        try {
            String query = "SELECT O.Register_num FROM orphanage O JOIN location L USING(location_id) WHERE L.location=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, location);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Register_num");
            }

            return -1;
        } finally {

        }

    }

    public List<usersDTO> getAllUsers() throws SQLException {
        List<usersDTO> users = new ArrayList<>();
    
        try {
            System.out.println("Entering getAllUsers method");
            String query = "SELECT User_id, User_name, Email, Contact_no, Location, Role FROM users";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
    
            while (resultSet.next()) {
                int userId = resultSet.getInt("User_id");
                String userName = resultSet.getString("User_name");
                String email = resultSet.getString("Email");
                String contactNo = resultSet.getString("Contact_no");
                String location = resultSet.getString("Location");
                String role = resultSet.getString("Role");
    
                System.out.println("User ID: " + userId);
                System.out.println("User Name: " + userName);
                System.out.println("Email: " + email);
                System.out.println("Contact Number: " + contactNo);
                System.out.println("Location: " + location);
                System.out.println("Role: " + role);
                System.out.println("------------------------------");
    
                usersDTO user = new usersDTO(userId, userName, contactNo, email, location, role);
                users.add(user);
            }
        } finally {
            // Close resources if needed
            // Handle exceptions if needed
        }
    
        return users;
    }
    
    public int getLocationId(String location) throws SQLException {
        String query = "SELECT Location_id FROM location WHERE Location = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, location);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Location_id");
            } else {
                throw new SQLException("Failed to retrieve location ID for location: " + location);
            }
        }
    }

}

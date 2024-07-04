package Controllers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.Connect;
import Models.locationDAO;
import Models.loginDAO;
import Models.orphanageDAO;
import Models.usersDAO;
import Resources.locationDTO;
import Resources.loginDTO;
import Resources.usersDTO;
import Resources.orphanageDTO;
import util.Cookie;
public class UserControll extends Connect{
    
    public int login(String Email,String Password)throws Exception{
        loginDAO login=loginDAO.getInstance();
        loginDTO user=login.getLoginData(Email);
        if(user==null)
        {
            throw new Exception("Invalid user");
        }
        if(!user .getPassword().equals(Password))
        {
            throw new Exception("Invalid password");
        }
        Cookie.User_id=user.getUser_id();
        if(user.getRole().equals("manager"))
        {
            return 1;
        }
        return 2;
    }
   public int signUp(String User_name,String Email,String Password,String Location,String contact,String Role) throws SQLException
    {
    
        usersDAO sign=usersDAO.getInstance();
       orphanageDAO s=orphanageDAO.getInstance();
        
        usersDTO dummy=new usersDTO();
        
        dummy.setLocation(Location);
        dummy.setEmail(Email);
        dummy.setContact_no(contact);
        dummy.setRole(Role);
        dummy.setUser_name(User_name);
        
       int user1=s.getsignUp(dummy);
       int j= sign.getId(contact);
       
       loginDTO tummy=new loginDTO();
       tummy.setEmail(Email);
       tummy.setPassword(Password);
       
       int go=sign.getsignUp2(tummy,j);
       
    
       if(user1>0)
       {
        return 1;
       }
       else{
        return -1;
       }
        
       

    }
    
        public int signUpOrphanage(String User_name, String Email, String Password, String Location, String contact_no, String Role, String Orphanage_name, int Register_num, int Total_Strength, int location_id) throws Exception {
          
            orphanageDAO sign = orphanageDAO.getInstance();
        usersDAO userDAO = usersDAO.getInstance();
        usersDAO usersDAO = Models.usersDAO.getInstance();
        
            
            orphanageDTO dummy = new orphanageDTO();
        dummy.setOrphanage_name(Orphanage_name);
        dummy.setRegister_num(Register_num);
        dummy.setTotal_Strength(Total_Strength);
        dummy.setLocation_id(location_id);
        
        return orphanageDAO.getsignUp(dummy);
        

        } 
       
        public int getUserIdByEmail(String userEmail) {
           
            throw new UnsupportedOperationException("Unimplemented method 'getUserIdByEmail'");
        }
    
        public boolean isAdmin(int userId) throws SQLException {
            String userRole = getUserRole(userId);
            return "Admin".equals(userRole);
        }
    
        private String getUserRole(int userId) {
          
            throw new UnsupportedOperationException("Unimplemented method 'getUserRole'");
        }
        public void viewOrphanages(int userId) throws SQLException {
            if (isAdmin(userId)) {
                orphanageDAO orphanageDao = orphanageDAO.getInstance();
                // Implement logic to fetch and display the list of orphanages
                try {
                    // Example: Fetching and displaying orphanages
                    System.out.println("List of Orphanages:");
                    orphanageDao.getAllOrphanages().forEach(orphanage -> {
                        System.out.println("Orphanage Name: " + orphanage.getOrphanage_name());
                        System.out.println("Register Number: " + orphanage.getRegister_num());
                        System.out.println("Total Strength: " + orphanage.getTotal_Strength());
                        System.out.println("Location ID: " + orphanage.getLocation_id());
                        System.out.println("--------------------------");
                    });
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            } else {
                System.out.println("You do not have permission to view orphanages.");
            }
        }
        public void viewAllUsers(int userId) throws SQLException {
            if (isAdmin(userId)) {
                usersDAO userDao = usersDAO.getInstance();
                // Implement logic to fetch and display the list of all users
                try {
                    System.out.println("List of All Users:");
                    userDao.getAllUsers().forEach(user -> {
                        System.out.println("User ID: " + user.getUser_id());
                        System.out.println("User Name: " + user.getUser_name());
                        System.out.println("Contact Number: " + user.getContact_no());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println("Location: " + user.getLocation());
                        System.out.println("Role: " + user.getRole());
                        System.out.println("--------------------------");
                    });
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            } else {
                System.out.println("You do not have permission to view all users.");
            }
        }
        public int signUpAndGetUserId(String name, String userEmail, String password, String location, String contact,
                String role) throws SQLException {
            locationDAO locationDao = locationDAO.getInstance();
        locationDTO locationDto = new locationDTO();
        locationDto.setLocation(location);
        int locationId = locationDao.insertLocation(locationDto);

        // Insert into users table with the obtained location ID
        String userQuery = "INSERT INTO users(User_name, Email, Password, Location_id, Contact_no, Role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement userStatement = con.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {
            userStatement.setString(1, name);
            userStatement.setString(2, userEmail);
            userStatement.setString(3, password);
            userStatement.setInt(4, locationId);
            userStatement.setString(5, contact);
            userStatement.setString(6, role);

            int result = userStatement.executeUpdate();

            if (result == 1) {
                // Get the generated user ID
                ResultSet generatedKeys = userStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve user ID.");
                }
            } else {
                throw new SQLException("Failed to insert user.");
            }
        }
        }
        public static boolean addOrphanage(String orphanageName, int registerNumber, int totalStrength, int locationId)
        throws SQLException {
    String query = "INSERT INTO orphanage (Orphanage_name, Register_num, Total_Strength, location_id) VALUES (?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
        preparedStatement.setString(1, orphanageName);
        preparedStatement.setInt(2, registerNumber);
        preparedStatement.setInt(3, totalStrength);
        preparedStatement.setInt(4, locationId);

        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }
        
        
    }
}
 
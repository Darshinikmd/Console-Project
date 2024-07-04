package Models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Resources.loginDTO; 
import Resources.orphanageDTO;
import Resources.usersDTO;

public class orphanageDAO  extends Connect{
    private PreparedStatement getAllOrphanagesStatement;
    private static orphanageDAO instance;
    static PreparedStatement getsignUp;
    PreparedStatement getlogSign;
    PreparedStatement isId;


   public orphanageDAO() throws SQLException
   {

    getsignUp=con.prepareStatement("INSERT INTO orphanage(Register_num,Orphanage_name,Total_Strength,location_id) Values(?,?,?,?)");
    getlogSign=con.prepareStatement("INSERT INTO login(Email,Password,User_id) Values(?,?,?)");
    isId=con.prepareStatement("select User_id from users where email=?");

  
}


public static orphanageDAO getInstance() throws SQLException{
    if(instance==null){
        instance=new orphanageDAO();
    }
    return instance;
}
public static int getsignUp(orphanageDTO dummy1) throws Exception
{
    getsignUp.setInt(1, dummy1.getRegister_num());
    getsignUp.setString(2,dummy1.getOrphanage_name());
    getsignUp.setInt(3,dummy1.getTotal_Strength());
    getsignUp.setInt(4,dummy1.getLocation_id());
   
    int rs=getsignUp.executeUpdate();

    return rs;


}
public String getId(String Email) throws SQLException{
    isId.setString(1,Email);
    ResultSet rs=isId.executeQuery();
    String D="";
    if(rs.next())
    {
        D=rs.getString(1);
    }
    return D;

}
public int getsignUp2(loginDTO tummy1,String j) throws SQLException{
    getlogSign.setString(1, tummy1.getEmail());
    getlogSign.setString(2, tummy1.getPassword());
    getlogSign.setString(3, j);
    int ps = getlogSign.executeUpdate();
    return ps;
}
public List<orphanageDTO> getAllOrphanages() throws SQLException {
    List<orphanageDTO> orphanages = new ArrayList<>();

    try {
        System.out.println("Entering getAllOrphanages method");
        String query = "SELECT Register_num, Orphanage_name, Total_Strength, location_id FROM orphanage";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int registerNum = resultSet.getInt("Register_num");
            String orphanageName = resultSet.getString("Orphanage_name");
            int totalStrength = resultSet.getInt("Total_Strength");
            int locationId = resultSet.getInt("location_id");

            System.out.println("Register Number: " + registerNum);
            System.out.println("Orphanage Name: " + orphanageName);
            System.out.println("Total Strength: " + totalStrength);
            System.out.println("Location ID: " + locationId);
            System.out.println("------------------------------");

            orphanageDTO orphanage = new orphanageDTO(registerNum, totalStrength, locationId, orphanageName);
            orphanages.add(orphanage);
        }
    } finally {
        // Close resources if needed
        // Handle exceptions if needed
    }

    return orphanages;
}

    public int getsignUp(usersDTO dummy) throws SQLException {
        String query = "INSERT INTO orphanage(Orphanage_name, Register_num, Total_Strength, Location_id) VALUES (?, ?, ?, ?)";
        // PreparedStatement preparedStatement = con.prepareStatement(query);

        getsignUp.setString(1, orphanageDTO.getOrphanage_name());
        getsignUp.setInt(2,orphanageDTO.getRegister_num());
        getsignUp.setInt(3,orphanageDTO. getTotal_Strength());
        getsignUp.setInt(4,orphanageDTO.getLocation_id());
        //getsignUp.setString(5, orphanageDTO);

        int rs = getsignUp.executeUpdate();
        return rs;
    }
    public void addOrphanage()
    {
         
    }

}


 
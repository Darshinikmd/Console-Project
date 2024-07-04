package Models;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Resources.loginDTO;

public class loginDAO extends Connect{
    private static loginDAO instance;
    PreparedStatement getLogin;
   private loginDAO() throws SQLException{
    getLogin=con.prepareStatement("SELECT L.EMAIL,L.Password,L.User_id,U.role FROM login L JOIN users U USING(user_id) WHERE L.email=?");
   
}
public static loginDAO getInstance() throws SQLException{
    if(instance==null){
        instance=new loginDAO();
    }
    return instance;
}
public  loginDTO getLoginData(String email) throws SQLException{
    getLogin.setString(1, email);
    ResultSet res=getLogin.executeQuery();
    if(res.next()){
        return new loginDTO(res.getInt(3), res.getString(1), res.getString(2), res.getString(4));
    }
    return null;
   }
    
}

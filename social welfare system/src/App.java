
import java.sql.SQLException;
import Models.Connect;
import Views.UserView;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Connect.getConnection();
            System.out.println("connected");
        } catch (SQLException err) {
            System.out.println("Database Connection error");
            System.out.println(err);
            return;
        }
        UserView H = new UserView();
        H.toSignUpLogin();
    }
}

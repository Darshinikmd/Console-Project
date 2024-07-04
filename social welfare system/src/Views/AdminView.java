package Views;
import java.sql.SQLException;

import Controllers.UserControll;
import Models.orphanageDAO;
import Models.rescue_systemDAO;
import Models.usersDAO;
import Resources.orphanageDTO;
import Resources.rescue_systemDTO;
import util.Input;

public class AdminView extends Input {
   public void  adminLogin() throws SQLException
   {
        System.out.println("Enter Mail: ");
        String admail=sc.next();
        System.out.println("Enter password: ");
        String pw=sc.next();
        usersDAO usd=new usersDAO();
        orphanageDAO orp=new orphanageDAO();
        orphanageDTO ort=new orphanageDTO();
        rescue_systemDAO rs=new rescue_systemDAO();

        

        boolean loggedIn = true;
        if(admail.equals("admin123@gmail.com") && pw.equals("admin456"))
        {
            while(loggedIn)
            {
            System.out.println("Login Successfull...");
            System.out.println("1. View All Users");
            System.out.println("2. View All Orphanages");
            System.out.println("3. Manage Orphanages");
            System.out.println("4. View Rescued Individuals");
           
            System.out.println("5. Logout ");

            System.out.println("Enter your choice: ");
            int option=sc.nextInt();

            if(option==1)
            {
                usd.getAllUsers() ;

            }
            else if(option==2)
            {
                orp.getAllOrphanages();
            }
            else if(option==3)
            {
                System.out.println("Orphanage Name:");
        String orphanageName = sc.nextLine();
                sc.nextLine();
        System.out.println("Register Number:");
        int registerNumber = sc.nextInt();

        System.out.println("Total Strength:");
        int totalStrength = sc.nextInt();

        System.out.println("Location ID:");
        int locationId = sc.nextInt();

        try {
            boolean success = UserControll.addOrphanage(orphanageName, registerNumber, totalStrength, locationId);
            if (success) {
                System.out.println("Orphanage added successfully!");
            } else {
                System.out.println("Failed to add orphanage. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the orphanage: " + e.getMessage());
        }
            }
            else if(option==4)
            {
rs.getRescuedIndividuals();
            }
            else if(option==5)
            {
                return;
            }
            else
            {
                System.out.println("Invalid option...");
            }
        
        System.out.println("Do you want to perform another operation? (yes/no)");
        String continueChoice = sc.next().toLowerCase();
    
        if (continueChoice.equals("no") || option == 5) {
            // If the user chooses to logout or says no, break out of the loop
            System.out.println("Returning to the main login screen.");
            loggedIn = false;
        }
   }}}
    
}

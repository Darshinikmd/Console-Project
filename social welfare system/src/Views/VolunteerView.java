package Views;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import util.Input;
import Controllers.UserControll;
import util.Cookie;
import Controllers.VolunteerControll;

public class VolunteerView {
    Scanner sc = new Scanner(System.in);
    VolunteerControll vc = new VolunteerControll();

    public void donate() throws SQLException {
        System.out.println("Enter the details of the donating item: ");
        System.out.println("Item Name: ");
        String itemName = sc.next();

        System.out.println("Item Quantity: ");
        int itemQuantity = sc.nextInt();
        System.out.println("Enter the date: ");
        String dateString = sc.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = null;

        try {
            currentDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            return; // Abort the donation process if the date is not valid
        }

        UserControll us = new UserControll();
        int userId = getUserId();

        int check = vc.addDonation(userId, itemName, itemQuantity, java.sql.Date.valueOf(dateString));
        if (check == 1) {
            System.out.println("Donation successful...");
        } else {
            System.out.println("Donation failed...");
        }

    }

    private int getUserId() {
        return Cookie.User_id;

    }

    private int getUserIdFromUser(UserControll userController) throws SQLException {
        System.out.println("Enter your Email: ");
        String userEmail = sc.next();

        // Check if the user exists in the users table
        int existingUserId = userController.getUserIdByEmail(userEmail);
        if (existingUserId != -1) {
            System.out.println("User not found. Creating a new user...");
            return existingUserId;
        }

        // If the user doesn't exist, auto-increment user_id and insert a new user

        // Get other user details (name, contact, etc.) as needed for the signup
        // ...

        // Create a new user
        // int newUserId = userController.signUpAndGetUserId(name, userEmail, password,
        // location, contact, role);

        System.out.println("New user created with ID: " + existingUserId);

        return existingUserId;
    }

    public void rescue() throws SQLException {
        System.out.println("Enter the details of the rescue operation: ");
        System.out.println("Rescue Name: ");
        String rescueName = sc.next();
        System.out.println("Rescue Age: ");
        int rescueAge = sc.nextInt();
        System.out.println("Rescue Gender: ");
        String rescueGender = sc.next();
        System.out.println("Health Status: ");
        String healthStatus = sc.next();
        System.out.println("Enter the date: ");
        String joiningdate = sc.next();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date currDate = dateFormat.parse(joiningdate);

            // Abort the donation process if the date is not valid

            UserControll us = new UserControll();
            int userId = getUserId(); // Use a valid way to get the user ID

            int check = vc.rescue(rescueName, rescueAge, rescueGender, healthStatus, currDate, userId);

            if (check == 1) {
                System.out.println("Rescue operation successful...");
            } else {
                System.out.println("Rescue operation failed...");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
        }

    }

    public void showVolunteerOptions() throws SQLException {
        System.out.println("1. Donate");
        System.out.println("2. Rescue");
        System.out.println("Enter your choice: ");
        int choice = sc.nextInt();
        if (choice == 1) {
            donate();
        } else if (choice == 2) {
            rescue();
        } else {
            System.out.println("Invalid choice");
        }
    }
}

package Views;

import Controllers.UserControll;

import util.Input;

public class UserView extends Input {
    AdminView ad=new AdminView();

    public void toSignUpLogin() throws Exception {
        System.out.println("Welcome to Social Welfare System");
        System.out.println();
        System.out.println("1.Admin Login");
        System.out.println("2.User Login");
        System.out.println("3.SignUp");
        System.out.println("4.Exit");
        System.out.println("Enter your choice: ");
        int ch = sc.nextInt();
        if(ch==1)
        {
           ad.adminLogin();
        }
        else if (ch == 2) {
            login();
        } else if (ch == 3) {
            signUp();
        } else {
            return;
        }

    }

    public void login() throws Exception {
        System.out.println("Enter Email: ");
        String email = sc.next();
        System.out.println("Enter Password: ");
        String password = sc.next();
        UserControll us = new UserControll();
        int check = us.login(email, password);
        VolunteerView vv = new VolunteerView();
        if (check == 1) {
            System.out.println("Manager");

        } else {
            System.out.println("Volunteer");
            if (check == 2) {
                vv.showVolunteerOptions();
            }
        }
    }

    public void signUp() throws Exception {
        UserControll us = new UserControll();

        System.out.println("Enter name: ");
        String name = sc.next();

        System.out.println("Enter Email: ");
        String email = sc.next();
        System.out.println("Enter password: ");
        String password = sc.next();
        System.out.println("Enter Location: ");
        String location = sc.next();

        System.out.println("Enter Contact number: ");
        String contact = sc.next();
        System.out.println("Enter your role:(Admin/Manger/Volunteer) ");
        String role = sc.next();
        int newUserId = us.signUpAndGetUserId(name, email, password, location, contact, role);
        VolunteerView vv = new VolunteerView();
        if (role.equals("Manager")) {

            System.out.println("Enter Orphanage Name: ");
            String orphanageName = sc.next();
            System.out.println("Enter Register Number: ");
            int regNum = 0;

            while (true) {
                try {
                    System.out.println("Enter Register Number: ");
                    regNum = sc.nextInt();
                    break; // Exit the loop if input is successful
                } catch (java.util.InputMismatchException e) {

                    sc.next();
                }
            }
            System.out.println("Enter Total Strength: ");
            int totalStrength = sc.nextInt();
            System.out.println("Enter location pincode");
            int locationId = sc.nextInt();
            int check = us.signUpOrphanage(name, email, password, location, contact, role, orphanageName, regNum,
                    totalStrength, locationId);
            if (check == 1) {
                System.out.println("SignUp successful...");
            } else {
                System.out.println("SignUp failed...");
            }
        } else {

            int check = us.signUp(name, email, password, location, contact, role);
            if (check == 1) {
                System.out.println("SignUp successful...");
                vv.showVolunteerOptions();
            } else {
                System.out.println("SignUp failed...");
            }
        }
        // vv.showVolunteerOptions(newUserId);
        // int newUserId = us.signUpAndGetUserId(name, email, password, location,
        // contact, role);
    }
}
//VolunteerView vv=new VolunteerView();

// Inside UserView class

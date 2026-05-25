package Assignment_1;

import java.util.Scanner;

public class jay {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // REGISTRATION
        System.out.println("========== REGISTRATION ==========");

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Phone Number (+27): ");
        String phone = input.nextLine();

        // Login object
        Login login = new Login();

        // Validation
        boolean validUsername = login.checkUserName(username);
        boolean validPassword = login.checkPasswordComplexity(password);
        boolean validPhone = login.checkCellPhoneNumber(phone);

        // Username
        if (validUsername) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username incorrectly formatted.");
        }

        // Password
        if (validPassword) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password incorrectly formatted.");
        }

        // Phone
        if (validPhone) {
            System.out.println("Phone number successfully captured.");
        } else {
            System.out.println("Phone number incorrectly formatted.");
        }

        // Registration success
        if (validUsername && validPassword && validPhone) {
            System.out.println("Registration successful.");
            // LOGIN
            System.out.println("\n========== LOGIN ==========");
            System.out.print("Enter Username: ");
            String enteredUsername = input.nextLine();
            System.out.print("Enter Password: ");
            String enteredPassword = input.nextLine();
            
            boolean loginStatus = login.loginUser(username, password,enteredUsername,enteredPassword);

            System.out.println( login.returnLoginStatus( loginStatus,firstName, lastName) );

            // QUICKCHAT
            if (loginStatus) {
                System.out.println("\nWelcome to QuickChat");
                System.out.println("""   
                        Choose an option:
                        
                        1) Send Messages
                        2) Show recently sent messages - Coming soon
                        3) Quit
                        """);

                System.out.print("Select option: ");
                int option = Integer.parseInt(input.nextLine());

                switch (option) {

                    case 1:
                        System.out.print( "How many messages would you like to send? ");

                        int total = Integer.parseInt(input.nextLine());

                        for (int i = 1; i <= total; i++) {

                            System.out.println("\n========== MESSAGE "+ i+ " ==========" );

                            System.out.print("Enter recipient number: ");

                            String recipient =input.nextLine();

                            System.out.print( "Enter message: ");

                            String messageText =input.nextLine();

                            // Length check
                            if (messageText.length() > 250) {
                                System.out.println( "Please enter a message of less than 250 characters.");
                                i--;
                                continue;
                            }

                            // Create Message object
                            Message msg = new Message( i,recipient, messageText);

                            // ID validation
                            if (msg.checkMessageID()) {

                                System.out.println( "Message ID successfully created.");
                            }

                            // Recipient validation
                            System.out.println( msg.checkRecipientCell());

                            // Message hash
                            System.out.println( "Message Hash: "  + msg.createMessageHash() );

                            // Send/store/disregard
                            System.out.println( msg.sentMessage() );

                            // Print details
                            System.out.println( msg.printMessages() );
                        }

                        // Total messages
                        System.out.println( "\nTotal messages sent: "+ Message.returnTotalMessages());

                        break;

                    case 2:

                        System.out.println( "Coming Soon." );

                        break;

                    case 3:

                        System.out.println( "Goodbye.");

                        System.exit(0);

                        break;

                    default:

                        System.out.println("Invalid option selected.");
                }
            }

        } else {

            System.out.println( "Registration failed." );
        }

        input.close();
    }
}

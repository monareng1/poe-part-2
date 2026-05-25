
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Assignment_1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Message {

    private static int totalMessages = 0;

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    // Constructor
    public Message( int messageNumber,String recipient, String message) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;

        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generate random ID
    private String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L+ (long) ( random.nextDouble() * 9000000000L );

        return String.valueOf(number);
    }

    // Check message ID
    public boolean checkMessageID() {

        return messageID.length() <= 10;
    }

    // Check recipient
    public String checkRecipientCell() {

        if (recipient.length() <= 12
                && recipient.startsWith("+27")) {
            return "Cell phone number successfully captured.";

        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }

    // Create hash
    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return ( messageID.substring(0, 2) + ":" + messageNumber+ ":" + firstWord + lastWord ).toUpperCase();
    }

    // Send message
    public String sentMessage() {

        Scanner input = new Scanner(System.in);

        System.out.println("""
                
                Choose an option:
                
                1) Send Message
                2) Disregard Message
                3) Store Message
                """);

        System.out.print("Enter choice: ");

        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {

            case 1:

                totalMessages++;

                return "Message successfully sent.";

            case 2:

                return "Message disregarded.";

            case 3:

                storeMessage();

                return "Message successfully stored.";

            default:

                return "No valid option selected.";
        }
    }

    // Print message
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + message;
    }

    // Return total messages
    public static int returnTotalMessages() {

        return totalMessages;
    }

    // Store JSON
public void storeMessage() {

    try {

        FileWriter writer = new FileWriter( "storedMessages.json",true);

        writer.write("{\n" +
                "\"MessageID\":\"" + messageID + "\",\n" +
                "\"MessageHash\":\"" + messageHash + "\",\n" +
                "\"Recipient\":\"" + recipient + "\",\n" +
                "\"Message\":\"" + message + "\"\n" +
                "}\n\n"
        );

        writer.close();

        System.out.println("Message stored successfully.");

    } catch (IOException e) {

        System.out.println( "Error storing message.");
    }
}
}


package appclasses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmailAccount {
    private static final int MAX_USERS = 100;
    private static int numOfUsers = 0;
    
    private final String firstName;
    private final String lastName;
    private final String userName;
    private String password;
    private final String companyName;
    private int department;
    private String emailAddress;
    private int mailboxCurrent = 0;           // in mb
    private final int mailboxCapacity = 500;  // in mb
    private final List<String> inbox;
    private final List<String> sent;

    public enum Department {
        SALES(1),
        DEVELOPMENT(2),
        ACCOUNTING(3),
        NONE(0);

        private int code;
        Department(int value) {
            code = value;
        }
        public int getCode() { return code; }
    }

    private EmailAccount() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter new user's first name: ");
        firstName = s.nextLine();
        System.out.print("Enter user's last name: ");
        lastName = s.nextLine();
        userName = generateUserName();
        password = generatePassword(8);
        System.out.print("Enter company name: ");
        companyName = s.nextLine();
        department = setDepartment();
        emailAddress = generateAddress();
        inbox = new ArrayList<>();
        sent = new ArrayList<>();
        numOfUsers++;
    }

    public static EmailAccount create() {
        if(numOfUsers < MAX_USERS)
            return new EmailAccount();
        else
            throw new IllegalStateException("Organization's servers maxed out. " +
                    "Please contact administration.");
    }

    public static int getMaxUsers()   { return MAX_USERS;  }
    public static int getNumOfUsers() { return numOfUsers; }

    public String getFirstName()      { return firstName;    }
    public String getLastName()       { return lastName;     }
    public String getUserName()       { return userName;     }
    public String getPassword()       { return password;     }
    public String getEmail()          { return emailAddress; }

    public String getDepartment() {
        for(Department d : Department.values()) {
            if(department == d.code)
                return d.toString();
        }
        return "Department code invalid. Please update value.";
    }

    public int getMailboxSpace() {
        return mailboxCapacity - mailboxCurrent;
    }

    private int setDepartment(){
        System.out.print("Enter department:\n1 for Sales\n2 for Development" +
                "\n3 for Accounting\n0 for None\n");
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private String generateUserName() {
        return this.firstName.charAt(0) + this.lastName;
    }

    private String generateAddress() {
        return userName + "@" + companyName.toLowerCase() + ".com";
    }

    private String generatePassword(int length){
        String alph = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%&";
        char[] password = new char[length];
        for(int i = 0; i < password.length; i++){
            int rand = (int) (Math.random() * alph.length());
            password[i] = alph.charAt(rand);
        }
        return String.valueOf(password);
    }

    public void send(EmailAccount recipient, String message) {
        recipient.receive(message);
        if(getMailboxSpace() >= 2){
            sent.add(message);
            mailboxCurrent += 2;
        }
        else
            System.out.println("Mailbox full. Please clear out space in 'sent' folder.");
    }

    public void receive(String message) {
        if(getMailboxSpace() >= 2) {
            inbox.add(message);
            mailboxCurrent += 2;
        }
        else
            System.out.println("Mailbox full. Recipient needs to clear space in inbox.");
    }

    @Override
    public String toString(){
        return "EMPLOYEE: " + firstName.toUpperCase() + " " + lastName.toUpperCase() +
                "\nEMAIL: " + emailAddress.toUpperCase() +
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb" +
                "\nMAILBOX SPACE: " + (mailboxCapacity - mailboxCurrent) + "mb";
    }
}
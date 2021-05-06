package user;

import main.java.EmailAccount;
import java.util.*;

// Simple tool utilizes EmailAccount.java to create email accounts based on user input
public class EmailApp {
    public static void main( String[] args ) {
        Scanner s = new Scanner(System.in);
        System.out.print("How many users would you like to sign up? ");

        int users = s.nextInt();
        List<EmailAccount> accounts = new ArrayList<>();

        for(int i = 0; i < users; i++) {
            accounts.add(EmailAccount.create());
        }

        System.out.println("Users added: \n");


        for(EmailAccount ac : accounts) {
            System.out.println(ac.toString() + "\n");
        }

        System.out.println( "Thank you for using. Please restart the program when you wish to add more users." );
    }
}

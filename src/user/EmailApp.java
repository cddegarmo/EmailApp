package user;

import appclasses.EmailAccount;

import java.util.*;

public class EmailApp {
    public static void main( String[] args ) {
        Scanner s = new Scanner(System.in);
        System.out.print("How many users would you like to sign up? ");

        int users = s.nextInt();
        List<EmailAccount> accounts = new ArrayList<>();

        for(int i = 0; i < users; i++) {
            accounts.add(EmailAccount.create());
        }

        System.out.println("Users added: ");
        System.out.println();

        for(EmailAccount ac : accounts) {
            System.out.println(ac.toString());
            System.out.println();
        }

        System.out.println( "Thank you for using. Please restart the program when you wish to add more users." );
    }
}

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailApp {
    public static void main( String[] args ) {
        System.out.print( "How many users would you like to sign up?");
        Scanner in = new Scanner( System.in );
        int numUsers = in.nextInt();
        List<EmailAccount> accounts = new ArrayList<>();

        for( int i = 0; i < numUsers; i++ ){
            accounts.add( EmailAccount.createEmail());
        }

        for( EmailAccount account : accounts ){
            System.out.println( account.toString());
        }

        System.out.println( "Number of new users added: " + EmailAccount.getNumOfUsers());
        System.out.println( "Thank you for using. Please restart the program when you wish to add more users." );
    }
}

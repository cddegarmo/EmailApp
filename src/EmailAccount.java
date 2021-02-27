import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmailAccount {
    private static final int MAX_USERS = 2500;
    private static int numOfUsers = 0;
    private static final String COMPANY_ADDRESS = "traxus.com";
    
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity = 500;

    private EmailAccount(){
        Scanner in = new Scanner( System.in );
        System.out.print( "Enter user's first name: " );
        firstName = in.nextLine();
        System.out.print( "Enter user's last name: ");
        lastName = in.nextLine();
        department = setDepartment();
        System.out.println( "EMAIL CREATED for: " + firstName + " " + lastName );
        System.out.println( "DEPARTMENT: " + department );
        userName = generateUserName().toLowerCase();
        System.out.println( "USER NAME: " + userName );
        password = generatePassword( 11 );
        if( department.equals( "" ))
            email = userName + "@" + COMPANY_ADDRESS;
        else
            email = userName + "@" + department.toLowerCase() + "." + COMPANY_ADDRESS;
        System.out.println( "EMAIL ADDRESS: " + email );
        numOfUsers++;
    }

    public static EmailAccount createEmail() {
        if( numOfUsers < MAX_USERS )
            return new EmailAccount();
        else
            throw new IllegalStateException( "Organization's servers maxed out. Please contact Administration." );
    }

    private String setDepartment(){
        System.out.print( "Enter department:\n1 for Sales\n2 for Development\n3 for Accounting\n0 for None\n" );
        Scanner in = new Scanner( System.in );
        int departmentChoice = in.nextInt();
        if( departmentChoice == 1 )
            return "Sales";
        else if( departmentChoice == 2 )
            return "Development";
        else if( departmentChoice == 3 )
            return "Accounting";
        else
            return "";
    }

    private String generateUserName() {
        return this.firstName.charAt( 0 ) + this.lastName;
    }

    private String generatePassword( int length ){
        String alph = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%&";
        char[] password = new char[ length ];
        for( int i = 0; i < password.length; i++ ){
            int rand = ( int ) ( Math.random() * alph.length());
            password[ i ] = alph.charAt( rand );
        }
        return String.valueOf( password );
    }

    public static int getMaxUsers()          { return MAX_USERS;       }
    public static int getNumOfUsers()        { return numOfUsers;      }
    public static String getCompanyAddress() { return COMPANY_ADDRESS; }

    public String getFirstName()             { return firstName;       }
    public String getLastName()              { return lastName;        }
    public String getUserName()              { return userName;        }
    public String getPassword()              { return password;        }
    public String getDepartment()            { return department;      }
    public String getEmail()                 { return email;           }
    public int getMailboxCapacity()          { return mailboxCapacity; }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof EmailAccount))
            return false;
        EmailAccount em = (EmailAccount) o;
        return em.firstName.equals(firstName) && em.lastName.equals(lastName) &&
                em.password.equals(password);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "EMPLOYEE: " + firstName + " " + lastName +
                "\nEMAIL: " + email +
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb";
    }
}

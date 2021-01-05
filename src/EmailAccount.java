import java.util.Scanner;

public class EmailAccount {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity = 500;
    private static final int MAX_USERS = 2500;
    private static int numOfUsers = 0;
    private static final String COMPANY_ADDRESS = "traxus.com";

    private EmailAccount( String firstName, String lastName ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = setDepartment();
        System.out.println( "EMAIL CREATED for: " + firstName + " " + lastName );
        System.out.println( "DEPARTMENT: " + this.department );
        this.userName = generateUserName().toLowerCase();
        System.out.println( "USER NAME: " + this.userName );
        this.password = generatePassword( 11 );
        this.email = userName + "@" + department.toLowerCase() + "." + COMPANY_ADDRESS;
        System.out.println( "EMAIL ADDRESS: " + email );
        numOfUsers++;
    }

    public static EmailAccount createEmail( String firstName, String lastName ) {
        if( numOfUsers < MAX_USERS )
            return new EmailAccount( firstName, lastName );
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

    @Override
    public String toString(){
        return "EMPLOYEE: " + firstName + " " + lastName +
                "\nEMAIL: " + email +
                "\nMAILBOX CAPACITY: " + mailboxCapacity + "mb";
    }


    private void setFirstName( String firstName ){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getDepartment(){
        return this.department;
    }

    public void changeDepartment( String department ){
        this.department = department;
    }

    public int getMailboxCapacity(){
        return this.mailboxCapacity;
    }

    public String getPassword(){
        return this.password;
    }

    public void changePassword( String password ){
        this.password = password;
    }

    public void changeEmailAddress( String email ){
        this.email = email;
    }

    public void changeCapacity( int capacity ){
        this.mailboxCapacity = capacity;
    }
}

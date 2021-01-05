public class EmailApp {
    public static void main( String[] args ) {
        EmailAccount degarmo = EmailAccount.createEmail( "Dillon", "DeGarmo" );
        System.out.println( degarmo.toString());
    }
}

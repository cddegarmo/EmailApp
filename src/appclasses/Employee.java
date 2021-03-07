package appclasses;

public class Employee {
    private static final int MAX_EMPLOYEES = 2500;
    private static int numOfEmployees = 0;

    private String firstName;
    private String lastName;
    private EmailAccount email;
    private int departmentCode;

    private Employee() {
    }

    private String formalize(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof Employee))
            return false;
        Employee em = (Employee) o;
        return em.firstName.equals(firstName) && em.lastName.equals(lastName) &&
                em.departmentCode == departmentCode;
    }

    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + Integer.hashCode(departmentCode);
        return result;
    }

}

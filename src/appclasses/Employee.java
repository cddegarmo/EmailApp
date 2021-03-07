package appclasses;

public class Employee {

    private String firstName;
    private String lastName;
    // private EmailAccount email;
    private Company.Department department;
    private int salary;

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
        return em.firstName.equals(firstName) && em.lastName.equals(lastName);
    }

    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    public EmailAccount getEmail() { return email; }
    public String getName() { return lastName + ", " + firstName; }
    public int getSalary() { return salary; }
    public Company.Department getDepartment() { return department; }

}

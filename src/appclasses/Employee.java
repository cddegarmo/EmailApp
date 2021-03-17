package appclasses;

import static appclasses.Company.*;

import java.util.*;

public class Employee {

    public enum Sex {
        MALE, FEMALE
    }

    private static class EmailAccount {
        private String username;
        private String address;
        private String password;
        private int mailboxCapacity = 500;
        private int mailboxSize = 0;
        private List<String> inbox;
        private List<String> sent;

        private EmailAccount(String firstName, String lastName,
                             String company) {
            generateUsername(firstName, lastName);
            generateAddress(company);
            generatePassword();
            inbox = new ArrayList<>();
            sent = new ArrayList<>();
        }

        public static EmailAccount create(String firstName, String lastName,
                                          String company) {
            if (firstName.equals(""))
                throw new IllegalArgumentException("Email must have first name for record.");
            else
                return new EmailAccount(firstName, lastName, company);
        }

        private void generateUsername(String firstName, String lastName) {
            username = firstName.substring(0, 1).toLowerCase() +
                    lastName.toLowerCase();
        }

        private void generateAddress(String company) {
            address = username + "@" +
                    company.toLowerCase() + "corp.com";
        }

        private void generatePassword() {
            String alph = "abcdefghijklmnopqrstuvwxyz!@#$%";
            char[] password = new char[8];
            for (int i = 0; i < password.length; i++) {
                int rand = (int) (Math.random() * alph.length());
                password[i] = alph.charAt(rand);
            }
            this.password = String.valueOf(password);
        }
    }

    private String firstName;
    private String lastName;
    private Sex gender;
    private String company;
    private EmailAccount email;
    private Department department = null;
    private int salary;

    private Employee(String companyName, int salary) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter new employee's first name: ");
        firstName = s.nextLine();
        System.out.print("Enter employee's last name: ");
        lastName = s.nextLine();
        System.out.print("Enter employee's gender (1 for male, 2 for female): ");
        gender = Sex.values()[s.nextInt() - 1];
        company = companyName;
        System.out.println("Enter department code (1 for Sales, 2 for Development, " +
                "3 for Accounting, 4 for None)");
        int dep = s.nextInt();
        for (Department d : Department.values()) {
            if (d.getCode() == dep)
                department = d;
        }
        this.salary = salary;
        email = new Employee.EmailAccount(firstName, lastName, company);
    }

    public static Employee instance(String company, int salary) {
        return new Employee(company, salary);
    }

    public String getName()           { return lastName + ", " + firstName; }
    public int getSalary()            { return salary;                      }
    public Department getDepartment() { return department;                  }
    public Sex getGender()            { return gender;                      }
    public EmailAccount getEmail()    { return email;                       }
    public String getUsername()       { return email.username;              }
    public String getAddress()        { return email.address;               }

    private String formalize(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}

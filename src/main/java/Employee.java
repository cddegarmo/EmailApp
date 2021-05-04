package main.java;
import main.java.Company.*;

import java.nio.file.Path;
import java.text.MessageFormat;
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

        private static EmailAccount create(String firstName, String lastName,
                                          String company) {
            if (firstName.equals(""))
                throw new IllegalArgumentException("Email must have first name for record.");
            else
                return new EmailAccount(firstName, lastName, company);
        }

        private void receive(String message) {
            if (mailboxSize < mailboxCapacity) {
                inbox.add(message);
                mailboxSize++;
            } else
                throw new IllegalStateException("Recipient mailbox full. Needs to clear space.");
        }

        private void send(EmailAccount recipient, String message) {
            if (mailboxSize < mailboxCapacity) {
                recipient.receive(message);
                sent.add(message);
                mailboxSize++;
            } else
                throw new IllegalStateException("Mailbox full. Please clear space.");
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

        private void alterPassword(String password) {
            if (password.length() > 7 && password.length() < 17)
                this.password = password;
            else
                System.out.println("Password must be 8-16 characters in length.");
        }

        public String toString() {
            return String.format("%s%n%s%n%d%n", username, address, mailboxCapacity);
        }
    }

    private String firstName;
    private String lastName;
    private Sex gender;
    private String company;
    private EmailAccount       email;
    private Company.Department department = null;
    private int                salary;

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
        email = EmailAccount.create(firstName, lastName, company);
    }

    private Employee(String firstName, String lastName, Sex gender, int dep, int salary) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.gender    = gender;
        for (Department d : Department.values()) {
            if (d.getCode() == dep)
                department = d;
        }
        this.salary    = salary;
        email = EmailAccount.create(firstName, lastName, "Apache");
    }

    public static Employee getInstance(String company, int salary) {
        return new Employee(company, salary);
    }

    public static Employee getInstance(String firstName, String lastName,
                                       Sex gender, int department, int salary) {
        return new Employee(firstName, lastName, gender, department, salary);
    }

    public String getFirstName()      { return firstName;                   }
    public String getLastName()       { return lastName;                    }
    public int getSalary()            { return salary;                      }
    public Department getDepartment() { return department;                  }
    public String getDepName()        { return department.getName();        }
    public Sex getGender()            { return gender;                      }
    public String getEmail()          { return email.toString();            }
    public String getUsername()       { return email.username;              }
    public String getAddress()        { return email.address;               }

    public void setCompany(String s) {
        company = s;
    }

    public List<String> getInbox() {
        List<String> inbox = new ArrayList<>(email.inbox);
        return inbox;
    }

    public List<String> getSent() {
        List<String> sent = new ArrayList<>(email.sent);
        return sent;
    }

    public void receive(String message) {
        email.receive(message);
    }

    public void send(Employee recipient, String message) {
        email.send(recipient.email, message);
    }

    public void changePassword(String password) {
        email.alterPassword(password);
    }
    
    private String formalize(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee em = (Employee) o;
        return em.firstName.equals(firstName) &&
             em.lastName.equals(lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", lastName, firstName);
    }
}

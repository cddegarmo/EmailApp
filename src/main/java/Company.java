package main.java;

import java.io.*;
import java.nio.file.Path;
import java.text.*;
import java.util.*;
import static java.util.stream.Collectors.*;
import static main.java.Employee.*;

public class Company implements Organization {
    private static final Company INSTANCE = new Company("Apache", 1956, new ArrayList<>());

    public enum Department {
        SALES(1, "Sales"),
        DEVELOPMENT(2, "Development"),
        ACCOUNTING(3, "Accounting"),
        NONE(4, "N/A");

        private int code;
        private String name;
        Department(int value, String text) {
            code = value;
            name = text;
        }
        public int getCode()    { return code; }
        public String getName() { return name; }
    }

    private static class EmployeeFormatter {
        private final ResourceBundle resource;
        private final ResourceBundle config;
        private final MessageFormat employees;
        private final Path dataFolder;

        private EmployeeFormatter() {
            resource = ResourceBundle.getBundle("main.resources.employees");
            config   = ResourceBundle.getBundle("main.resources.config");
            employees = new MessageFormat(config.getString("employee.data"));
            Path current = Path.of("").toAbsolutePath();
            dataFolder = current.resolve(config.getString("data.folder"));
        }

        private String format(Employee e) {
            return MessageFormat.format(resource.getString("employee.format"),
                                        e.getLastName(),
                                        e.getFirstName(),
                                        e.getDepartment(),
                                        e.getSalary());
        }
    }

    private String name;
    private int yearFounded;
    private List<Employee> employees;
    private int numOfEmployees;
    private EmployeeFormatter employeeFormatter = new EmployeeFormatter();

    private Company(String name, int year, List<Employee> founders) {
        this.name = name;
        yearFounded = year;
        employees = founders;
        numOfEmployees = founders.size();
    }

    public static Company found() {
        return INSTANCE;
    }

    private Employee parseEmployee(String text) {
        Employee employee = null;
        try {
            Object[] values = employeeFormatter.employees.parse(text);
            String firstName = (String) values[0];
            String lastName = (String) values[1];
            int gender = Integer.parseInt((String) values[2]);
            int department = Integer.parseInt((String) values[3]);
            int salary = Integer.parseInt((String) values[4]);
            employee = Employee.getInstance(firstName, lastName, gender, department, salary);
        } catch (ParseException e) {
            e.getMessage();
        }
        return employee;
    }

    public void loadEmployees() {
        Path file = employeeFormatter.dataFolder.resolve(
             employeeFormatter.config.getString("employees.folder"));
        try (BufferedReader br = new BufferedReader(
             new FileReader(String.valueOf(file)))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                Employee e = parseEmployee(line);
                hire(e);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String formalize(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    public String getName()              { return name;           }
    public int getYearFounded()          { return yearFounded;    }
    public List<Employee> getEmployees() { return employees;      }
    public int getNumOfEmployees()       { return numOfEmployees; }
    public boolean bonuses()             { return true;           }

    public EmployeeFormatter getEmployeeFormatter() {
        return employeeFormatter;
    }

    public void printEmployees() {
        for (Employee e : employees)
            System.out.println(getEmployeeFormatter().format(e));
    }

    public void hire(Employee e) {
        employees.add(e);
        numOfEmployees++;
    }

    public void fire(Employee e) {
        employees.remove(e);
        numOfEmployees--;
    }

    public void fire(List<Employee> employees) {
        this.employees.removeAll(employees);
        numOfEmployees -= employees.size();
    }

    public void communicate(String message) {
        for (Employee e : employees)
            e.receive(message);
    }

    public String companyToString() {
        return String.format("%s\nFounded in %d", name, yearFounded);
    }

    public Map<String, List<Employee>> employeesByDepartment() {
        List<Employee> current = getEmployees();
        return current
             .stream()
             .collect(groupingBy(Employee::getDepName));
    }

    public Map<String, Long> countByDepartment() {
        List<Employee> current = getEmployees();
        return current
             .stream()
             .collect(groupingBy(
                  Employee::getDepName,
                  counting()));
    }

    public Map<String, Integer> salaryByDepartment() {
        List<Employee> current = getEmployees();
        return current
             .stream()
             .collect(groupingBy(
                  Employee::getDepName,
                  reducing(
                       0,
                       Employee::getSalary,
                       Integer::sum)));
    }

    private Map<String, Integer> salaryByGender() {
        List<Employee> current = getEmployees();
        return current
             .stream()
             .collect(groupingBy(
                  Employee::getGenderLabel,
                  summingInt(Employee::getSalary)));
    }

    private boolean payDisparity() {
        Map<String, Integer> salaries = salaryByGender();
        int maleSalary   = salaries.get("Male");
        int femaleSalary = salaries.get("Female");
        if (Math.abs(maleSalary - femaleSalary) > 10_000)
            return true;
        return false;
    }

    private Map<String, Long> countByGender() {
        List<Employee> current = getEmployees();
        return current
             .stream()
             .collect(groupingBy(
                  Employee::getGenderLabel,
                  counting()));
    }

    public void adjustSalaries() {
        Map<String, Integer> salaries = salaryByGender();
        Map<String, Long>    counts   = countByGender();
        int maleSalary = salaries.get("Male");
        int femaleSalary = salaries.get("Female");
        if (payDisparity()) {
            if (maleSalary >= femaleSalary)
                employees.stream()
                         .filter(employee -> employee.getGender() == Sex.MALE)
                         .forEach(male -> {
                             int difference = maleSalary - femaleSalary;
                             male.reduceSalary((int) (difference / counts.get("Male")));
                         });
            else
                employees.stream()
                         .filter(employee -> employee.getGender() == Sex.FEMALE)
                         .forEach(female -> {
                             int difference = femaleSalary - maleSalary;
                             female.reduceSalary((int) (difference / counts.get("Female")));
                         });
        }
        System.out.println("Salaries adjusted to rectify pay disparity between genders.");
    }
}

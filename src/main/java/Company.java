package main.java;

import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.*;
import static java.util.stream.Collectors.*;

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

    public String formalize(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    public String getName()              { return name;           }
    public int getYearFounded()          { return yearFounded;    }
    public List<Employee> getEmployees() { return employees;      }
    public int getNumOfEmployees()       { return numOfEmployees; }

    public EmployeeFormatter getEmployeeFormatter() {
        return employeeFormatter;
    }

    public void printEmployees() {
        for (Employee e : employees)
            System.out.println(employeeFormatter.format(e));
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
        List<Employee> current = employees;
        return current
                .stream()
                .collect(groupingBy(Employee::getDepName));
    }

    public Map<String, Long> countByDepartment() {
        List<Employee> current = employees;
        return current
                .stream()
                .collect(groupingBy(
                                Employee::getDepName,
                                counting()));
    }

    public Map<String, Integer> salaryByDepartment() {
        List<Employee> current = employees;
        return current
                .stream()
                .collect(groupingBy(
                                Employee::getDepName,
                                reducing(
                                        0,
                                        Employee::getSalary,
                                        Integer::sum)));
    }

    public boolean bonuses() { return true; }
}

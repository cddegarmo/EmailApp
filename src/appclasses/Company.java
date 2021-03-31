package appclasses;

import java.util.*;
import java.util.stream.Collectors;

public class Company implements Organization {
    private static final Company INSTANCE = new Company("Apache", 1956, new ArrayList<>());

    public enum Department {
        SALES(1, "Sales"),
        DEVELOPMENT(2, "Development"),
        ACCOUNTING(3, "Accounting"),
        NONE(4, "");

        private int code;
        private String name;
        Department(int value, String text) {
            code = value;
            name = text;
        }
        public int getCode()    { return code; }
        public String getName() { return name; }
    }

    private String name;
    private int yearFounded;
    private List<Employee> employees;
    private int numOfEmployees;

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

    }

    public String companyToString() {
        return String.format("%s\nFounded in %d", name, yearFounded);
    }

    public Map<Department, List<Employee>> employeesByDepartment() {
        List<Employee> current = employees;
        return current
                .stream()
                .collect(
                        Collectors.groupingBy(Employee::getDepartment));
    }

    public Map<Department, Long> countByDepartment() {
        List<Employee> current = employees;
        return current
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()));
    }

    public Map<Department, Integer> salaryByDepartment() {
        List<Employee> current = employees;
        return current
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.reducing(
                                        0,
                                        Employee::getSalary,
                                        Integer::sum)));
    }

    public boolean bonuses() { return false; }
}

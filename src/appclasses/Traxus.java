package appclasses;

import java.util.*;
import java.util.stream.Collectors;

public class Traxus implements Company {
    private static final Traxus INSTANCE = new Traxus();
    private List<Employee> employees;

    public enum Department {
        SALES(1),
        DEVELOPMENT(2),
        ACCOUNTING(3),
        EXECUTIVE(4),
        NONE(5);

        private int code;
        Department(int value) {
            code = value;
        }
        public int getCode() { return code; }
    }

    public static Traxus getInstance() { return INSTANCE; }

    public void hire(Employee e) {
        employees.add(e);
    }

    public void fire(Employee e) {
        employees.remove(e);
    }

    public void fire(List<Employee> employees) {
        employees.removeAll(employees);
    }

    public void communicate(String message) {
        for(Employee e : employees)
            e.getEmail().receive(message);
    }

    public String companyToString() {
        return "Traxus Corporation\nFounded 1956 in Minneapolis, Minnesota";
    }

    public Map<Employee.Department, Long> numByDepartment() {
        List<Employee> current = employees;
        return current
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        )
                );
    }

    public Map<Employee.Department, Integer> salaryByDepartment() {
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

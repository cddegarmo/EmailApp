package main.java;

import java.util.*;

public interface Organization {

    void hire(Employee e);
    void fire(Employee e);
    void fire(List<Employee> employees);
    void communicate(String message);
    String companyToString();
    Map<String, List<Employee>> employeesByDepartment();
    Map<String, Long> countByDepartment();
    Map<String, Integer> salaryByDepartment();
    boolean bonuses();
}

package appclasses;

import java.util.*;

public interface Organization {

    void hire(Employee e);
    void fire(Employee e);
    void fire(List<Employee> employees);
    void communicate(String message);
    String companyToString();
    Map<Company.Department, List<Employee>> employeesByDepartment();
    Map<Company.Department, Long> countByDepartment();
    Map<Company.Department, Integer> salaryByDepartment();
    boolean bonuses();
}

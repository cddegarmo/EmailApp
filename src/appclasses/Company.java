package appclasses;

import java.util.*;

public interface Company {

    void hire(Employee e);
    void fire(Employee e);
    void fire(List<Employee> employees);
    void communicate(String message);
    String companyToString();
    Map<Employee.Department, Long> numByDepartment();
    Map<Employee.Department, Integer> salaryByDepartment();
    boolean bonuses();
}

package appclasses;

import static appclasses.Company.*;
import java.util.*;

public interface Organization {

    void hire(Employee e);
    void fire(Employee e);
    void fire(List<Employee> employees);
    void communicate(String message);
    String companyToString();
    Map<Department, List<Employee>> employeesByDepartment();
    Map<Department, Long> countByDepartment();
    Map<Department, Integer> salaryByDepartment();
    boolean bonuses();
}

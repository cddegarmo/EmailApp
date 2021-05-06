package main.java;
import main.java.*;
import main.java.Employee.*;

import java.nio.file.Path;

import static main.java.Employee.Sex.*;

public class Register {
    public static void main(String[] args) {
        Company apache = Company.found();
        apache.loadEmployees();
        apache.printEmployees();
        System.out.println(apache.employeesByDepartment().toString());
        System.out.println(apache.salaryByDepartment().toString());
        System.out.println(apache.countByDepartment().toString());
    }
}

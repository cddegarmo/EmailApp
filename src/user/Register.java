package user;
import main.java.*;
import main.java.Employee.*;

import java.nio.file.Path;

import static main.java.Employee.Sex.*;

public class Register {
    public static void main(String[] args) {
        Company apache = Company.found();
        Employee one = Employee.getInstance("William", "Hix",
                                            MALE, 2, 120000);
        Employee two = Employee.getInstance("Dillon", "DeGarmo",
                                            MALE, 1, 111000);
        Employee three = Employee.getInstance("Adele", "Christensen",
                                              FEMALE, 3, 98000);
        Employee four = Employee.getInstance("Roger", "Mayfield",
                                             MALE, 4, 175000);
        apache.hire(one);
        apache.hire(two);
        apache.hire(three);
        apache.hire(four);
        apache.printEmployees();
        System.out.println(apache.employeesByDepartment().toString());
        System.out.println(apache.salaryByDepartment().toString());
        System.out.println(apache.countByDepartment().toString());
    }
}

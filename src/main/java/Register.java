package main.java;

public class Register {
    public static void main(String[] args) {
        Company apache = Company.found();
        apache.loadEmployees();
        apache.printEmployees();
        apache.adjustSalaries();
        apache.printEmployees();
//        System.out.println(apache.employeesByDepartment().toString());
//        System.out.println(apache.salaryByDepartment().toString());
//        System.out.println(apache.countByDepartment().toString());
    }
}

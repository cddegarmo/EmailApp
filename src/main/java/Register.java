package main.java;

public class Register {
    public static void main(String[] args) {
        Company apache = Company.found();
        apache.loadEmployees();
        apache.printEmployees();
        apache.adjustSalaries();
        apache.printEmployees();
    }
}

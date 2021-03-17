package user;
import appclasses.*;
import java.util.*;

public class Register {
    public static void main(String[] args) {
        Employee one = Employee.instance("Apache", 128000);
        System.out.println(one.getName());
        System.out.println(one.getDepartment());
        System.out.println(one.getUsername());
        System.out.println(one.getAddress());
    }
}

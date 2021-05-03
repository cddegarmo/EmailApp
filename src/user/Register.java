package user;
import appclasses.*;
import java.util.*;

public class Register {
    public static void main(String[] args) {
        Employee one = Employee.getInstance("Apache", 128000);
        Employee two = Employee.getInstance("Apache", 111000);
        one.send(two, "Hello, one, my name is two.");
        two.send(one, "I really don't like you.");
        System.out.println(one.getSent().toString());
        System.out.println(two.getInbox().toString());
        System.out.println(one.getInbox().toString());
        System.out.println(two.getSent().toString());
    }
}

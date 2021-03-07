package appclasses;

import java.util.*;

public interface Company {
    void hire();
    void fire();
    void communicate(String message);
    String companyToString();
    Map<String, Integer> numPerDepartment();
    Map<Integer, Integer> salaryPerDepartment();
    boolean bonuses();
}

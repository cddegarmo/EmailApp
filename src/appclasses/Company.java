package appclasses;

import java.util.*;

public interface Company {
    void hire();
    void fire();
    Map<String, Integer> numPerDepartment();
    Map<Integer, Integer> salaryPerDepartment();
    boolean bonuses();
}

package logic;

import data.Employee;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static BigInteger avgSalary(List<Employee> employees) {
        BigInteger sumSalary = BigInteger.valueOf(0);
        for (Employee employee : employees) {
            sumSalary = sumSalary.add(employee.getSalary());
        }
        return sumSalary.divide(BigInteger.valueOf(employees.size()));
    }

    public static List<List<Employee>> combination(int n, List<List<Employee>> result, List<Employee> employees) {
        if (n == 0) {
            result.add(new ArrayList<Employee>());
            result.get(result.size() - 1).add(employees.get(n));
            return result;
        } else {
            List<List<Employee>> previous = combination(n - 1, result, employees);
            int l = previous.size();
            for (int i = 0; i < l; i++) {
                List<Employee> data = new ArrayList<Employee>(previous.get(i));
                data.add(employees.get(n));
                previous.add(data);
            }
            previous.add(new ArrayList<Employee>());
            previous.get(previous.size() - 1).add(employees.get(n));
            return previous;
        }

    }

    public static boolean comparisonSalary(BigInteger fromAvgSalary, BigInteger toTheAvgSalary, List<Employee> list) {

        return ((fromAvgSalary.compareTo(avgSalary(list)) > 0) & (toTheAvgSalary.compareTo(avgSalary(list)) < 0));

    }
}

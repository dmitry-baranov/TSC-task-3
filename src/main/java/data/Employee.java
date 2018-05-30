package data;

import java.math.BigInteger;

public class Employee {

    private String name;
    private String surname;
    private BigInteger salary;

    public Employee(String name, String surname, BigInteger salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setSalary(BigInteger salary) {
        this.salary = salary;
    }

}

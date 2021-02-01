package by.senla.task3.ex2;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final int MAX_STAFF;
    private final List<Employee> employees = new ArrayList<>();
    private String companyName;

    public Company(String companyName, int quantity) {
        this.MAX_STAFF = quantity;
        this.companyName = companyName;
    }


    @Override
    public String toString() {
        return "company: " + companyName +
                "\nemployees " + employees +
                '\n';
    }

    public int getMAX_STAFF() {
        return MAX_STAFF;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

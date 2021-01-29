package by.senla.task3.ex2;

import java.util.List;

public class CompanyManagement {
    private final Company company;

    public CompanyManagement(Company company) {
        this.company = company;
    }

    public void staffing() {
        double interest;
        int jobVacancy;
        int maxStaff = company.getMAX_STAFF();
        int currentStaff = company.getEmployees().size();

        jobVacancy = maxStaff - currentStaff;
        interest = (currentStaff * 100) / (double) maxStaff;
        interest = (int) Math.round(interest * 100) / 100.0;
        System.out.println("The staff of the company is staffed with " + currentStaff + " employees.");
        System.out.println("Vacancies " + jobVacancy + ". Staffing " + interest + "%");
    }

    public void addEmployees(Employee employee) {
        if (company.getMAX_STAFF() > company.getEmployees().size() && employee != null) {
            company.getEmployees().add(employee);
        } else {
            System.out.println("Warning: staff is full or NULL.");
        }
    }

    public void delEmployees(Employee employee) {
        int staffBefore = company.getEmployees().size();
        company.getEmployees().remove(employee);
        if (staffBefore != company.getEmployees().size()) {
            System.out.print("Employee deleted. ");
        } else {
            System.out.print("This employee does not exist. ");
        }
        System.out.println("The company has " + company.getEmployees().size() + " employees.");
    }

    public void calculateMonthlySalary(){
        List<Employee> employees= company.getEmployees();
        int totalSalary = 0;
        for (Employee e: employees ) {
            totalSalary += e.getSalary();
        }
        System.out.println("Monthly salary of all employees of the company " + totalSalary + ".");
    }
}

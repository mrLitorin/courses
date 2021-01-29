package by.senla.task3.ex2;

import by.senla.task3.ex2.staff.*;

public class Runner {
    public static void main(String[] args) {
        Employee president = new President(10000);
        Employee directorOfSoftware = new DirectorOfSoftware(8000);
        Employee qualityAssurance = new QualityAssurance(3000);
        Employee contactSoftwareEngineer = new ContactSoftwareEngineer(3500);
        Employee seniorSoftwareArchitect = new SeniorSoftwareArchitect(7500);
        Employee softwareEngineer1 = new SoftwareEngineer(2500);
        Employee softwareEngineer2 = new SoftwareEngineer(2700);
        Employee softwareEngineer3 = new SoftwareEngineer(2200);
        Employee softwareEngineer4 = new SoftwareEngineer(2200);
        Employee supportSoftwareEngineer1 = new SupportSoftwareEngineer(1700);
        Employee supportSoftwareEngineer2 = new SupportSoftwareEngineer(2000);

        Company softLine = new Company("SOFT LINE", 14);

        CompanyManagement management = new CompanyManagement(softLine);
        management.addEmployees(president);
        management.addEmployees(directorOfSoftware);
        management.addEmployees(qualityAssurance);
        management.addEmployees(contactSoftwareEngineer);
        management.addEmployees(seniorSoftwareArchitect);
        management.addEmployees(softwareEngineer1);
        management.addEmployees(softwareEngineer2);
        management.addEmployees(softwareEngineer3);
        management.addEmployees(softwareEngineer4);
        management.addEmployees(supportSoftwareEngineer1);
        management.addEmployees(supportSoftwareEngineer2);

        management.delEmployees(softwareEngineer2);
        management.delEmployees(new SupportSoftwareEngineer(1233));

        System.out.println(softLine);

        management.staffing();
        management.calculateMonthlySalary();

    }


}

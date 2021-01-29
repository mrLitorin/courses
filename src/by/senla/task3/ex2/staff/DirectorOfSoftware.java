package by.senla.task3.ex2.staff;

import by.senla.task3.ex2.Employee;

public class DirectorOfSoftware extends President {

    public DirectorOfSoftware(int salary) {
        super(salary);
        this.setPosition("Director of software development");
    }
}

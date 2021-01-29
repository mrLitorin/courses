package by.senla.task3.ex2.staff;

import by.senla.task3.ex2.Employee;

public class President extends Employee {

    public President(int salary) {
        super(salary);
        this.setPosition("President");
    }
}

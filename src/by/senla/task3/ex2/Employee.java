package by.senla.task3.ex2;

public abstract class Employee {
    private final int ID;
    private String position;
    private int salary;

    @Override
    public String toString() {
        return "\nID >> " + getID() + " position: \"" + position + '\"' +
                ", salary = " + salary + "$";
    }

    public Employee(int salary) {
        this.salary = salary;
        ID = (int) (Math.random() * 100000);
    }

    public int getID() {
        return ID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}

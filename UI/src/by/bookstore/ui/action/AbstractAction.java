package by.bookstore.ui.action;

import by.bookstore.ui.Facade;

import java.util.Scanner;

public class AbstractAction {
    protected Facade facade = Facade.getInstance();
    protected Scanner sc = new Scanner(System.in);
}

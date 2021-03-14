package by.bookstore.ui;

import by.bookstore.ui.menu.MenuController;
import by.senla.bookstore.util.InitBD;

public class ProgConsole {
    public static void main(String[] args) {
        InitBD BD = new InitBD();
        BD.initBD();
        MenuController.getInstance().run();
    }
}

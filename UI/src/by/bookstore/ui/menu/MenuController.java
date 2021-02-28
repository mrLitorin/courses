package by.bookstore.ui.menu;

import java.util.Objects;
import java.util.Scanner;

public class MenuController {
    private static MenuController instance;
    private final Builder builder;
    private final Navigator navigator;
    private final Scanner sc = new Scanner(System.in);
    private int index;

    private MenuController() {
        builder = Builder.getInstance();
        navigator = Navigator.getInstance();
        builder.buildMenu();
    }

    public static MenuController getInstance() {
        return Objects.requireNonNullElse(instance, new MenuController());
    }

    public void run() {
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        input();
        while (index != 0) {
            navigator.navigate(index);
            navigator.printMenu();
            input();
        }
    }

    void input() {
        System.out.print("\tInput number of menu >> ");
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("\tInvalid input. Retry >> ");
        }
        index = Math.abs(sc.nextInt());
        if (index >= navigator.getCurrentMenu().getMenuItem().size()) {
            index = 0;
        }
    }
}

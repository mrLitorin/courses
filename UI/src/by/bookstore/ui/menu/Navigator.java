package by.bookstore.ui.menu;

import java.util.Objects;

public class Navigator {
    private static Navigator instance;
    private Menu currentMenu;

    private Navigator() {
    }

    public static Navigator getInstance() {
        return Objects.requireNonNullElse(instance, new Navigator());
    }

    public void printMenu() {
        String spl = " ********************* ";
        System.out.println(spl + currentMenu.getName() + spl);
        currentMenu.getMenuItem()
                .forEach(menuItem -> System.out.println(menuItem.getTitle()));
    }

    public void navigate(int index) {
        if (currentMenu != null && index < currentMenu.getMenuItem().size()) {
            MenuItem menuItem = currentMenu.getMenuItem().get(index);
            menuItem.doAction();
            currentMenu = menuItem.getNextMenu();
        } else {
            System.out.println("Error input.");
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}

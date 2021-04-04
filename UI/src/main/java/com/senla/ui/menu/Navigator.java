package com.senla.ui.menu;

import org.apache.log4j.Logger;

import java.util.Objects;

public class Navigator {
    private static final Logger LOGGER = Logger.getLogger(Navigator.class.getSimpleName());
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
        try {
            MenuItem menuItem = currentMenu.getMenuItem().get(index);
            menuItem.doAction();
            currentMenu = menuItem.getNextMenu();
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Out of range.", e);
        }

    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}

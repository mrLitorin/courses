package com.senla.ui.menu;

import com.senla.ui.action.DeserializableDB;
import com.senla.ui.action.SerializableDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Scanner;

public class MenuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class.getSimpleName());
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
        try {
            DeserializableDB.getInstance().execute();
        }catch (Exception e){
            LOGGER.error("Deserialization failed.");
        }
        LOGGER.info("Deserialization OK!");
        try {
            navigator.setCurrentMenu(builder.getRootMenu());
            navigator.printMenu();
            input();
            while (index != 0) {
                navigator.navigate(index);
                navigator.printMenu();
                input();
            }
            SerializableDB.getInstance().execute();
            LOGGER.info("Serialization OK!");
        } catch (Exception e) {
            LOGGER.error("End of programme.", e);
        }
    }

    void input() {
        System.out.print("\tInput number of menu >> ");
        while (!sc.hasNextInt()) {
            LOGGER.warn("Invalid input.");
            System.out.print("\tRetry >> ");
            sc.nextLine();
        }
        index = Math.abs(sc.nextInt());
        if (index >= navigator.getCurrentMenu().getMenuItem().size()) {
            LOGGER.warn("Invalid input.");
        }
    }
}

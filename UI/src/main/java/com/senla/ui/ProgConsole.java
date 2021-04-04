package com.senla.ui;

import com.senla.store.util.Imitation;
import com.senla.ui.menu.MenuController;
import org.apache.log4j.Logger;


public class ProgConsole {
    private static final Logger LOGGER = Logger.getLogger(ProgConsole.class.getSimpleName());

    public static void main(String[] args) {
        //Imitation runner = Imitation.getInstance();
        LOGGER.info("Start programme.");
        MenuController.getInstance().run();
    }
}

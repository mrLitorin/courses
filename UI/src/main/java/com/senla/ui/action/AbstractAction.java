package com.senla.ui.action;

import com.senla.store.Facade;
import org.apache.log4j.Logger;

import java.util.Scanner;

abstract class AbstractAction {
    protected static final Logger LOGGER = Logger.getLogger(AbstractAction.class.getSimpleName());
    protected Facade facade = Facade.getInstance();
    protected Scanner sc = new Scanner(System.in);
}

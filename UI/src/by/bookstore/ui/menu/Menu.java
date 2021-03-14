package by.bookstore.ui.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    private String name;
    private List<MenuItem> menuItem = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
        menuItem.add(new MenuItem("0 - EXIT", () -> {
            System.out.println("Program exit.");
        }, this));
    }

    public void addMenuItem(MenuItem item) {
        menuItem.add(item);
        item.setTitle(menuItem.indexOf(item) + " - " + item.getTitle());
    }

    public void addMenuItem(MenuItem... items) {
        Arrays.stream(items)
                .forEach(item -> {
                    menuItem.add(item);
                    item.setTitle(menuItem.indexOf(item) + " - " + item.getTitle());
                });

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItem() {
        if (menuItem == null) {
            menuItem = new ArrayList<>();
        }
        return menuItem;
    }
}

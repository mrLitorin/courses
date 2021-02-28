package by.bookstore.ui.menu;

import by.bookstore.ui.Facade;
import by.bookstore.ui.action.*;

import java.util.Objects;

public class Builder {
    private static Builder instance;
    private final Menu rootMenu;

    private Builder() {
        rootMenu = new Menu();
    }

    public static Builder getInstance() {
        return Objects.requireNonNullElse(instance, new Builder());
    }

    public void buildMenu() {
//        MenuFactory menuFactory = new BookMenuFactory();
//        Menu bookMenu = menuFactory.getMenu();
        Menu bookMenu = createBookMenu();
        Menu orderMenu = createOrderMenu();
        Menu requestMenu = createRequestMenu();
        rootMenu.setName("MAIN MENU");
        rootMenu.addMenuItem(new MenuItem("Book menu", () -> {
        }, bookMenu));
        rootMenu.addMenuItem(new MenuItem("Order menu", () -> {
        }, orderMenu));
        rootMenu.addMenuItem(new MenuItem("Request menu", () -> {
        }, requestMenu));
    }

    private Menu createBookMenu() {
        Menu bookMenu = new Menu();
        Menu bookSortingMenu = createBookSortingMenu();
        bookMenu.setName("BOOKS MENU");
        bookMenu.addMenuItem(new MenuItem("Print all book",
                () -> Facade.getInstance().printAllBook(), bookMenu));
        bookMenu.addMenuItem(new MenuItem("Show book description", ShowBookDescription.getInstance(), bookMenu));
        bookMenu.addMenuItem(new MenuItem("Write off book", WriteOffBook.getInstance(), bookMenu));
        bookMenu.addMenuItem(new MenuItem("Add book on stock", AddBookOnStock.getInstance(), bookMenu));
        bookMenu.addMenuItem(new MenuItem("Sorting books", () -> {
        }, bookSortingMenu));
        bookMenu.addMenuItem(new MenuItem("Unpopular books", UnpopularBooks.getInstance(), bookSortingMenu));
        bookMenu.addMenuItem(new MenuItem("Back to main menu", () -> {
        }, rootMenu));
        return bookMenu;
    }

    private Menu createBookSortingMenu() {
        Menu bookSortingMenu = new Menu();
        bookSortingMenu.setName("Sorting book menu");
        bookSortingMenu.addMenuItem(new MenuItem("Sort all by ...", SortAllBook.getInstance(), bookSortingMenu));
        bookSortingMenu.addMenuItem(new MenuItem("Back to main menu", () -> {
        }, rootMenu));
        return bookSortingMenu;
    }


    private Menu createOrderMenu() {
        Menu orderMenu = new Menu();
        Menu orderSortingMenu = createOrderSortingMenu();
        orderMenu.setName("ORDERS MENU");
        orderMenu.addMenuItem(new MenuItem("Print all orders",
                () -> Facade.getInstance().printAllOrder(), orderMenu));
        orderMenu.addMenuItem(new MenuItem("Create order", CreateOrder.getInstance(), orderMenu));
        orderMenu.addMenuItem(new MenuItem("Show order detail", ShowDetails.getInstance(), orderMenu));
        orderMenu.addMenuItem(new MenuItem("Cancel order", CancelOrder.getInstance(), orderMenu));
        orderMenu.addMenuItem(new MenuItem("Change order status", ChangeOrderStatus.getInstance(), orderMenu));
        orderMenu.addMenuItem(new MenuItem("Sorting orders", () -> {
        }, orderSortingMenu));
        orderMenu.addMenuItem(new MenuItem("The amount of money", AmountOfIncome.getInstance(), orderMenu));
        orderMenu.addMenuItem(new MenuItem("Back to main menu", () -> {
        }, rootMenu));
        return orderMenu;
    }

    private Menu createOrderSortingMenu() {
        Menu orderSortingMenu = new Menu();
        orderSortingMenu.setName("Sorting order menu");
        orderSortingMenu.addMenuItem(new MenuItem("Sort all by...", SortAllOrder.getInstance(), orderSortingMenu));
        orderSortingMenu.addMenuItem(new MenuItem("Back to main menu", () -> {
        }, rootMenu));
        return orderSortingMenu;
    }


    private Menu createRequestMenu() {
        Menu requestMenu = new Menu();
        Menu requestSortingMenu = createRequestSortingMenu();
        requestMenu.setName("REQUESTS  MENU");
        requestMenu.addMenuItem(new MenuItem("Print all request",
                () -> Facade.getInstance().printAllRequest(), requestMenu));
        requestMenu.addMenuItem(new MenuItem("Sent request", SentRequest.getInstance(), requestMenu));
        requestMenu.addMenuItem(new MenuItem("Sorting requests", () -> {
        }, requestSortingMenu));
        requestMenu.addMenuItem(new MenuItem("Back to main menu", () -> {
        }, rootMenu));
        return requestMenu;
    }

    private Menu createRequestSortingMenu() {
        Menu requestSortingMenu = new Menu();
        requestSortingMenu.setName("Sorting request menu");
        requestSortingMenu.addMenuItem(new MenuItem("Sort all by ...", SortAllRequest.getInstance(), requestSortingMenu));
        requestSortingMenu.addMenuItem(new MenuItem("Back to main menu", () -> {
        }, rootMenu));
        return requestSortingMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}

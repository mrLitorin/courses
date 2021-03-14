package by.bookstore.ui.menu;

import by.bookstore.ui.action.*;

import java.util.Objects;

public class Builder {
    private static Builder instance;
    private Menu rootMenu;

    private Builder() {
    }

    public static Builder getInstance() {
        return Objects.requireNonNullElse(instance, new Builder());
    }

    public void buildMenu() {
        rootMenu = new Menu("MAIN MENU");
        Menu bookMenu = createBookMenu();
        Menu orderMenu = createOrderMenu();
        Menu requestMenu = createRequestMenu();
        rootMenu.addMenuItem(
                new MenuItem("NEXT to Book menu", DoNothing.getInstance(), bookMenu),
                new MenuItem("NEXT to Order menu", DoNothing.getInstance(), orderMenu),
                new MenuItem("NEXT to Request menu", DoNothing.getInstance(), requestMenu));
    }

    private Menu createBookMenu() {
        Menu bookMenu = new Menu("BOOKS MENU");
        Menu bookSortingMenu = createBookSortingMenu();
        bookMenu.addMenuItem(
                new MenuItem("Show all book", ShowBooks.getInstance(), bookMenu),
                new MenuItem("Show book description", ShowBookDescription.getInstance(), bookMenu),
                new MenuItem("Show unpopular books", ShowUnpopularBooks.getInstance(), bookMenu),
                new MenuItem("Add book on stock", AddBookOnStock.getInstance(), bookMenu),
                new MenuItem("NEXT to sorting menu", DoNothing.getInstance(), bookSortingMenu),
                new MenuItem("BACK to main menu", DoNothing.getInstance(), rootMenu));
        return bookMenu;
    }

    private Menu createBookSortingMenu() {
        Menu bookSortingMenu = new Menu("Sorting book menu");
        bookSortingMenu.addMenuItem(
                new MenuItem("NEXT to sorting menu", SortAllBook.getInstance(), bookSortingMenu),
                new MenuItem("BACK to main menu", DoNothing.getInstance(), rootMenu));
        return bookSortingMenu;
    }

    private Menu createOrderMenu() {
        Menu orderMenu = new Menu("ORDERS MENU");
        Menu orderSortingMenu = createOrderSortingMenu();
        orderMenu.addMenuItem(
                new MenuItem("Show all orders", ShowOrder.getInstance(), orderMenu),
                new MenuItem("Show order detail", ShowOrderDetails.getInstance(), orderMenu),
                new MenuItem("Create order", CreateOrder.getInstance(), orderMenu),
                new MenuItem("Cancel order", CancelOrder.getInstance(), orderMenu),
                new MenuItem("Change order status", ChangeOrderStatus.getInstance(), orderMenu),
                new MenuItem("The amount of money", AmountOfIncome.getInstance(), orderMenu),
                new MenuItem("NEXT to sorting menu", DoNothing.getInstance(), orderSortingMenu),
                new MenuItem("BACK to main menu", DoNothing.getInstance(), rootMenu));
        return orderMenu;
    }

    private Menu createOrderSortingMenu() {
        Menu orderSortingMenu = new Menu("Sorting order menu");
        orderSortingMenu.addMenuItem(
                new MenuItem("Sort all by...", SortAllOrder.getInstance(), orderSortingMenu),
                new MenuItem("BACK to main menu", DoNothing.getInstance(), rootMenu));
        return orderSortingMenu;
    }


    private Menu createRequestMenu() {
        Menu requestMenu = new Menu("REQUESTS  MENU");
        Menu requestSortingMenu = createRequestSortingMenu();
        requestMenu.addMenuItem(
                new MenuItem("Show all request", ShowRequest.getInstance(), requestMenu),
                new MenuItem("Sent request", SentRequest.getInstance(), requestMenu),
                new MenuItem("Write off book", WriteOffBook.getInstance(), requestMenu),
                new MenuItem("NEXT to sorting menu", DoNothing.getInstance(), requestSortingMenu),
                new MenuItem("BACK to main menu", DoNothing.getInstance(), rootMenu));
        return requestMenu;
    }

    private Menu createRequestSortingMenu() {
        Menu requestSortingMenu = new Menu("Sorting request menu");
        requestSortingMenu.addMenuItem(
                new MenuItem("Sort all by ...", SortAllRequest.getInstance(), requestSortingMenu),
                new MenuItem("BACK to main menu", DoNothing.getInstance(), rootMenu));
        return requestSortingMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}

package by.bookstore.ui.action;

import java.util.Objects;

public class CreateOrder extends AbstractAction implements IAction {
    private static CreateOrder instance;

    private CreateOrder() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new CreateOrder());
    }

    @Override
    public void execute() {
        long idBook;
        int quantity;
        facade.showBooks();
        System.out.print("Choose a book for order >> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        idBook = sc.nextLong();
        System.out.print("Input quantity >> ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        quantity = sc.nextInt();
        facade.createOrder(idBook, quantity);
    }
}

package by.bookstore.ui.action;

import java.util.Objects;

public class AddBookOnStock extends AbstractAction implements IAction {
    private static AddBookOnStock instance;

    private AddBookOnStock() {
    }

    public static AddBookOnStock getInstance() {
        return Objects.requireNonNullElse(instance, new AddBookOnStock());
    }

    @Override
    public void execute() {
        facade.printAllBook();
        System.out.print("Input ID book >> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid num. Retry >> ");
        }
        long idBook = sc.nextLong();
        facade.addBookOnStock(idBook);
    }
}

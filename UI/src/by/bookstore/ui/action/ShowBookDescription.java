package by.bookstore.ui.action;

import java.util.Objects;

public class ShowBookDescription extends AbstractAction implements IAction {
    private static ShowBookDescription instance;

    private ShowBookDescription() {
    }

    public static ShowBookDescription getInstance() {
        return Objects.requireNonNullElse(instance, new ShowBookDescription());
    }

    @Override
    public void execute() {
        long idBook = inputIdBook();
        facade.showBookDescription(idBook);
    }


    private long inputIdBook() {
        facade.printAllBook();
        System.out.print("Choose a book by ID or 0 for exit >> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid ID. ");
            System.out.print("Input ID book >> ");
        }
        return sc.nextLong();
    }
}

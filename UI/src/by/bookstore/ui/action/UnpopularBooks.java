package by.bookstore.ui.action;

import java.util.Objects;

public class UnpopularBooks extends AbstractAction implements IAction {
    private static UnpopularBooks instance;

    private UnpopularBooks() {
    }

    public static UnpopularBooks getInstance() {
        return Objects.requireNonNullElse(instance, new UnpopularBooks());
    }

    @Override
    public void execute() {
        facade.unpopularBooks();
    }
}

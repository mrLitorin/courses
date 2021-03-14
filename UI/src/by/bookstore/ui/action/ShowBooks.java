package by.bookstore.ui.action;

import java.util.Objects;

public class ShowBooks extends AbstractAction implements IAction {
    private static ShowBooks instance;

    private ShowBooks() {
    }

    public static ShowBooks getInstance() {
        return Objects.requireNonNullElse(instance, new ShowBooks());
    }

    @Override
    public void execute() {
        facade.showBooks();
    }
}

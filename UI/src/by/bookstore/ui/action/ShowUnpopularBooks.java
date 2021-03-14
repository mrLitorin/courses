package by.bookstore.ui.action;

import java.util.Objects;

public class ShowUnpopularBooks extends AbstractAction implements IAction {
    private static ShowUnpopularBooks instance;

    private ShowUnpopularBooks() {
    }

    public static ShowUnpopularBooks getInstance() {
        return Objects.requireNonNullElse(instance, new ShowUnpopularBooks());
    }

    @Override
    public void execute() {
        facade.showUnpopularBooks();
    }
}

package by.bookstore.ui.action;

import java.util.Objects;

public class ShowRequest extends AbstractAction implements IAction {
    private static ShowRequest instance;

    private ShowRequest() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowRequest());
    }

    @Override
    public void execute() {
        facade.showRequests();
    }
}

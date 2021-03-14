package by.bookstore.ui.action;

import java.util.Objects;

public class ShowOrder extends AbstractAction implements IAction {
    private static ShowOrder instance;

    private ShowOrder() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowOrder());
    }

    @Override
    public void execute() {
        facade.showOrders();
    }
}

package by.bookstore.ui.action;

import java.util.Objects;

public class ShowDetails extends AbstractAction implements IAction {
    private static ShowDetails instance;

    private ShowDetails() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowDetails());
    }

    @Override
    public void execute() {
        long idOrder;
        facade.printAllOrder();
        System.out.print("Select order to cancel (ID)>> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        idOrder = sc.nextLong();
        facade.showOrderDetails(idOrder);
    }
}

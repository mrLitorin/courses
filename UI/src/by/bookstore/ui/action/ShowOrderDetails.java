package by.bookstore.ui.action;

import java.util.Objects;

public class ShowOrderDetails extends AbstractAction implements IAction {
    private static ShowOrderDetails instance;

    private ShowOrderDetails() {
    }

    public static IAction getInstance() {
        return Objects.requireNonNullElse(instance, new ShowOrderDetails());
    }

    @Override
    public void execute() {
        long idOrder;
        facade.showOrders();
        System.out.print("Select order to cancel (ID)>> ");
        while (!sc.hasNextLong()) {
            sc.next();
            System.out.print("Invalid input. Retry >> ");
        }
        idOrder = sc.nextLong();
        facade.showOrderDetails(idOrder);
    }
}

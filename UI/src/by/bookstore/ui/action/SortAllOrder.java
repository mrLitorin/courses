package by.bookstore.ui.action;

import java.util.Objects;

public class SortAllOrder extends AbstractAction implements IAction {
    private static SortAllOrder instance;

    private SortAllOrder() {
    }

    public static SortAllOrder getInstance() {
        return Objects.requireNonNullElse(instance, new SortAllOrder());
    }

    @Override
    public void execute() {
        facade.sortAllOrder(inputSortBy());
    }

    private int inputSortBy() {
        int type;
        System.out.println("1 - date");
        System.out.println("2 - price");
        System.out.println("3 - status");
        System.out.print("Select type (1 - 3) >> ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Retry (1 - 3) >> ");
            sc.nextLine();
        }
        type = sc.nextInt();
        if (type < 1 || type > 3) {
            inputSortBy();
        }
        return type;
    }
}

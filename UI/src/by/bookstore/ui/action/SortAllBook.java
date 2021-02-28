package by.bookstore.ui.action;

import java.util.Objects;

public class SortAllBook extends AbstractAction implements IAction {
    private static SortAllBook instance;

    private SortAllBook() {
    }

    public static SortAllBook getInstance() {
        return Objects.requireNonNullElse(instance, new SortAllBook());
    }

    @Override
    public void execute() {
        facade.sortAllBook(inputSortBy());
    }

    private int inputSortBy() {
        int type;
        System.out.println("1 - title");
        System.out.println("2 - year");
        System.out.println("3 - price");
        System.out.println("4 - status");
        System.out.print("Select1 type (1 - 4) >> ");
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input. Retry (1 - 4)>> ");
            sc.nextLine();
        }
        type = sc.nextInt();
        if (type < 1 || type > 4) {
            inputSortBy();
        }
        return type;
    }
}

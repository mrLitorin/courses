package by.bookstore.ui.action;

public class PrintAllBook extends AbstractAction implements IAction {
    @Override
    public void execute() {
        facade.printAllBook();
    }
}

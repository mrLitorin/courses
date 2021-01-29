package by.senla.task3.ex3;

public class MakerThirdLine implements  ILineStep{
    @Override
    public IProductPart buildProductPart() {
        IProductPart monitor = new LaptopMonitor(17, "TN+Film");
        System.out.println("Monitor created. " + monitor);
        return monitor;
    }
}

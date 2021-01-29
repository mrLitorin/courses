package by.senla.task3.ex3;

public class MakerSecondLine implements  ILineStep{
    @Override
    public IProductPart buildProductPart() {
       IProductPart motherboard = new LaptopMotherboard("MSI-117", "DDR3");
        System.out.println("Motherboard created. " + motherboard);
       return motherboard;
    }
}

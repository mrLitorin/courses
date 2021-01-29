package by.senla.task3.ex3;

public class MakerFirstLine implements  ILineStep{
    @Override
    public IProductPart buildProductPart() {
        IProductPart body = new LaptopBody("Black", "Plastic");
        System.out.println("Body created. " + body);
        return body;
    }
}

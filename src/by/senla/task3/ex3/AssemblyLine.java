package by.senla.task3.ex3;

public class AssemblyLine implements IAssemblyLine {
    ILineStep step1;
    ILineStep step2;
    ILineStep step3;

    @Override
    public IProduct assemblyProduct(IProduct product) {
        if (product == null) product = new Laptop();
        step1 = new MakerFirstLine();
        step2 = new MakerSecondLine();
        step3 = new MakerThirdLine();

        product.installFirstPart(step1.buildProductPart());
        product.installSecondPart(step2.buildProductPart());
        product.installThirdPart(step3.buildProductPart());
        System.out.println("Passed the assembly line");
        return product;
    }
}

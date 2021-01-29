package by.senla.task3.ex3;

public class Runner {
    public static void main(String[] args) {
        IAssemblyLine assemblyLine = new AssemblyLine();
        IProduct laptopMSI = new Laptop();

        System.out.println(laptopMSI);

        assemblyLine.assemblyProduct(laptopMSI);

        System.out.println(laptopMSI);

    }
}

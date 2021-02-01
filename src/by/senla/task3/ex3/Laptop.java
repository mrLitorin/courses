package by.senla.task3.ex3;

public class Laptop implements IProduct {
    IProductPart body;
    IProductPart motherboard;
    IProductPart monitor;

    @Override
    public String toString() {
        return "\n\n>>>>>>>>LAPTOP<<<<<<<<<<" + "\n" + body + "\n" +
                motherboard + "\n" +
                monitor + "\n" +
                ">>>>>>>><<<<<<<<<<";
    }

    @Override
    public void installFirstPart(IProductPart body) {
        this.body = body;
        System.out.println(">>> Installed body.");
    }

    @Override
    public void installSecondPart(IProductPart motherboard) {
        this.motherboard = motherboard;
        System.out.println(">>> Installed motherboard");
    }

    @Override
    public void installThirdPart(IProductPart monitor) {
        this.monitor = motherboard;
        System.out.println(">>> Installed monitor");
    }
}

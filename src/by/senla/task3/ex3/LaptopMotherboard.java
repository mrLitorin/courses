package by.senla.task3.ex3;

public class LaptopMotherboard implements IProductPart {
    private String model;
    private String typeMemory;

    public LaptopMotherboard(String model, String typeMemory) {
        this.model = model;
        this.typeMemory = typeMemory;
    }

    @Override
    public String toString() {
        return "Motherboard model: " + model + ';' +
                " memory: " + typeMemory + ' ';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTypeMemory() {
        return typeMemory;
    }

    public void setTypeMemory(String typeMemory) {
        this.typeMemory = typeMemory;
    }
}

package by.senla.task3.ex3;

public class LaptopBody implements IProductPart{
    private String color;
    private String material;

    public LaptopBody(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String toString() {
        return "Body color: " + color +
                "; material: " + material + ' ';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}

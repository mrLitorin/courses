package by.senla.task3.ex3;

public class LaptopMonitor implements IProductPart {
    private int diagonal;
    private String typeOfMatrix;

    public LaptopMonitor(int diagonal, String typeOfMatrix) {
        this.diagonal = diagonal;
        this.typeOfMatrix = typeOfMatrix;
    }

    @Override
    public String toString() {
        return "Monitor: " + diagonal + '\"' +
                "; type of matrix: " + typeOfMatrix + ' ';
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    public String getTypeOfMatrix() {
        return typeOfMatrix;
    }

    public void setTypeOfMatrix(String typeOfMatrix) {
        this.typeOfMatrix = typeOfMatrix;
    }
}

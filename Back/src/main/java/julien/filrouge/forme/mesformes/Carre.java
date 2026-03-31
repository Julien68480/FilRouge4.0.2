package julien.filrouge.forme.mesformes;

import jakarta.persistence.Entity;
import julien.filrouge.dto.ShapeDto;

@Entity
public class Carre extends Shape {

    private double side;

    public Carre() {}

    public Carre(String color, double x, double y, double side) {

        super(color, x, y);
        this.side = side;

    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculAire(){

        return side * side;

    }
    @Override
    public ShapeDto toDto() {
        ShapeDto dto = super.toDto();
        dto.setSide(this.side);
        return dto;
    }


    @Override
    public double calculPerimetre() {
        return 4 * side;
    }
}


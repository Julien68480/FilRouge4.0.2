package julien.filrouge.forme.mesformes;

import jakarta.persistence.Entity;
import julien.filrouge.dto.ShapeDto;

@Entity
public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle() {}

    public Rectangle(String color, double x, double y, double length, double width) {

        super(color, x, y);
        this.length = length;
        this.width = width;

    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double calculAire(){

        return length * width;

    }

    @Override
    public double calculPerimetre() {

        return 2 * ( length + width );

    }
    @Override
    public ShapeDto toDto() {
        ShapeDto dto = super.toDto();
        dto.setLength(this.length);
        dto.setWidth(this.width);
        return dto;
    }
}


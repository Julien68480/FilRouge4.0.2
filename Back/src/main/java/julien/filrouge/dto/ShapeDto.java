package julien.filrouge.dto;

public class ShapeDto {

    private String type;
    private Long id;
    private String color;
    private double x;
    private double y;
    private double width;
    private double length;
    private double radius;
    private double side;

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getRadius() {
        return radius;
    }

    public double getSide() {
        return side;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setSide(double side) {
        this.side = side;
    }
}

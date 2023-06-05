package Entities;

import java.awt.*;

public class Food {
    private double x;
    private double y;
    private double radius;
    private Color color;
    public int health;
    public boolean eaten;

    public Food(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = new Color(188, 226, 158);
        this.health = 30;
        this.eaten = false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

}

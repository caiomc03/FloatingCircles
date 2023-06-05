package Entities;

import src.helper;
import src.FloatingCircles;
import java.awt.*;
import java.util.Random;

public class Circle {

    private double x;
    private double y;
    private double radius;

    private double max_velocity;
    private double velocityX;
    private double velocityY;

    private Color color;
    public int health;
    public double health_goal;
    public boolean alive;
    public boolean can_eat;

    public double foodX;
    public double foodY;

    public double vision_radius;

    public int gen;

    public String state;

    private static final int MAX_HEALTH = 255;

    // construtor para a primeira geracao de Bolas
    public Circle() {
        Random random = new Random();

        // gerando uma posicao aleatória dentro dos limites da janela
        this.x = random.nextDouble(
                Double.parseDouble(String.valueOf(FloatingCircles.WIDTH)) - FloatingCircles.CIRCLE_RADIUS * 2);
        this.y = random.nextDouble(
                Double.parseDouble(String.valueOf(FloatingCircles.HEIGHT)) - FloatingCircles.CIRCLE_RADIUS * 2);

        this.radius = FloatingCircles.CIRCLE_RADIUS;

        this.max_velocity = random.nextDouble(FloatingCircles.MAX_VELOCITY * 2) - FloatingCircles.MAX_VELOCITY;
        this.velocityX = this.max_velocity / 2;
        this.velocityY = this.max_velocity / 2;

        this.health = MAX_HEALTH;
        this.health_goal = FloatingCircles.HEALTH_GOAL;
        this.color = new Color(this.health, 0, 0);
        this.alive = true;
        this.can_eat = false;
        this.gen = 0;

        this.vision_radius = 100;

        // [0] looking for food "10" TRUE looking, FALSE going to food
        // [1] going to food "01" TRUE going to food, FALSE looking
        this.state = "10"; // default setting for looking for food
        this.foodX = 0;
        this.foodY = 0;

    }

    // construtor para as gerações filhas de Bolas
    public Circle(Circle father) {

        this.x = father.getX();
        this.y = father.getY();
        this.radius = helper.performMutation(father.getRadius());
        this.velocityX = helper.performMutation(father.getVelocityX());
        this.velocityY = helper.performMutation(father.getVelocityY());
        this.health = father.getHealth();
        this.health_goal = helper.performMutation(father.getHealthGoal());
        this.color = new Color(this.health, 0, 0);
        this.alive = true;
        this.can_eat = false;
        this.gen = father.getGen() + 1;

        this.vision_radius = father.getVision_radius();

        // [0] for looking for food
        // [1] for going to food
        this.state = "10"; // default setting for looking for food
        this.foodX = 0;
        this.foodY = 0;

    }

    public double getHealthGoal() {
        return this.health_goal;
    }

    public double getFoodX() {
        return this.foodX;
    }

    public void setFoodX(double x) {
        this.foodX = x;
    }

    public double getFoodY() {
        return this.foodY;
    }

    public void setFoodY(double y) {
        this.foodY = y;
    }

    public void move() {

        x += velocityX;
        y += velocityY;
    }

    public void age() {
        this.health -= 1;

        if (this.health <= 0) {
            this.alive = false;
        }
    }

    public void eat(int health_value) {

        // System.out.println("comi");

        if ((this.health + health_value) >= MAX_HEALTH) {
            this.setHealth(MAX_HEALTH);
        } else {
            this.setHealth(this.getHealth() + health_value);
        }
    }

    // funcao retorna de 0 a 1 a probabilidade desse individuo se reproduzir
    public double urgeToReproduce() {
        return helper.calculateProbability(this.health, health_goal, 5);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        this.color = new Color(this.health, 0, 0);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isCan_eat() {
        return can_eat;
    }

    public void setCan_eat(boolean can_eat) {
        this.can_eat = can_eat;
    }

    public static int getMaxHealth() {
        return MAX_HEALTH;
    }

    public int getGen() {
        return this.gen;
    }

    public double getVision_radius() {
        return vision_radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public Color getColor() {
        return color;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getFullSpeed() {
        return Math.sqrt(Math.pow(getVelocityX(), 2) + Math.pow(getVelocityY(), 2));
    }
}

// getfullspeed, gethealthgoal, getradius, size array circles circles.size()
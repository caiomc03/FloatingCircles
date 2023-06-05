package src;
import java.util.List;

import Entities.Circle;
import Entities.Food;

public class crowdActions {

    //Metodos chamados em FloatingCircles.java para medir a ultima geração de organismos
    public static int newGen = 0;
    public static int getGen(int original, int contender) {
        if (contender >= original) {
            return contender;
        } else {
            return original;}}
    public static String getGenString() {
        return String.valueOf(newGen);}


    //Função chamada em FloatingCircles.updateCircles() para checar se o organismo nao vai sair dos limites de pixel da janela
    public static void checkBounds(Circle circle) {
        if (circle.getX() - circle.getRadius() <= 0
                || circle.getX() - circle.getRadius() >= FloatingCircles.WIDTH - circle.getRadius() * 2) {
            circle.setVelocityX(-circle.getVelocityX());
        }
        if (circle.getY() - circle.getRadius() <= 0
                || circle.getY() - circle.getRadius() >= FloatingCircles.HEIGHT - circle.getRadius() * 2) {
            circle.setVelocityY(-circle.getVelocityY());
        }
    }

    //Função checando se a comida está dentro dos limites do círculo, se estiver, será registrado que a comida foi utilizada,
    // a comida será removida da lista de comida depois
    public static void checkFood(Circle circle, Food food) {
        boolean isInside = helper.isPointInsideCircle(circle.getX(), circle.getY(), circle.getRadius(), food.getX(),
                food.getY());
        if (isInside) {
            circle.eat(food.getHealth());
            food.setEaten(true);
        }
    }

    //Função que irá computar a probabilidade do organismo se reproduzir de acordo com seu health_goal,
    // se conseguir reproduzir, um novo circulo será registrado em baby_circles
    public static void tryReproduce(Circle circle, List<Circle> baby_circles) {
        if (helper.oddIfStatement(circle.urgeToReproduce())) {
            circle.setHealth(Integer.parseInt(String.valueOf(circle.health / 2)));
            baby_circles.add(new Circle(circle));
        }
    }
}

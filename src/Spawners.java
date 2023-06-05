package src;


import java.util.List;
import java.util.Random;

import Entities.Circle;
import Entities.Food;

public class Spawners {
    
    //Função que irá adicionar circulos na lista de circulos vivos,
    // de acordo com o valor FloatingCircles.NUM_CIRCLES
    public static void initializeCircles(List<Circle> circles) {
        
        for (int i = 0; i < FloatingCircles.NUM_CIRCLES; i++) {
            Circle circle = new Circle();
            circles.add(circle);
        }

    }


    //Função que tem chance de adicionar comida na simulação, sera verdadeiro para por comida de acorco com a 
    // probabilidade em FloatingCircles.FOOD_RATE
    public static void spawnFood(List<Food> foods){
        if(helper.oddIfStatement(FloatingCircles.FOOD_RATE)){

            for(int buff = 1; buff>0; buff--){
                Random random = new Random();

                int x = random.nextInt(FloatingCircles.WIDTH - FloatingCircles.CIRCLE_RADIUS * 2);
                int y = random.nextInt(FloatingCircles.HEIGHT - FloatingCircles.CIRCLE_RADIUS * 2);

                Food food = new Food(x, y, FloatingCircles.FOOD_RADIUS);

                foods.add(food);
            }
            
        }
    }

    //Função para adicionar um circulo apenas na simulação
    public static void spawnCircle(List<Circle> circles) {
        Circle circle = new Circle();
        circles.add(circle);
    }


}

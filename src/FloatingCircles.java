package src;


import javax.swing.*;

import Animation.Calvetti;
import Database.ConexaoDB;
import Database.UtilsDB;
import Entities.Circle;
import Entities.Food;
import src.App.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FloatingCircles extends JFrame {
    DecimalFormat formato = new DecimalFormat("#,####");
    
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    public static int NUM_CIRCLES = 50;
    public static int CIRCLE_RADIUS = 15;
    public static double MAX_VELOCITY = 10;
    public static int HEALTH_GOAL = 200;
    public static double FOOD_RATE = 0.5;
    public static final int FOOD_RADIUS = 5;

    public  List<Circle> circles;
    public static List<Circle> circles_cpy;
    public List<Circle> baby_circles;
    public List<Food> foods;

    public int aliveCircles = 0;

    private BufferedImage buffer;
    private Graphics bufferGraphics;

    public static final int FPS = 1;
    
    private Timer timer;
    public static boolean isPaused;

    public static boolean toReset;
    public static boolean spawnUnit;
    public static boolean toBake;

    public List<Double> circlesRadius;
    public List<Double> circlesHealthGoal;
    public List<Double> circlesSpeed;

    public static double meanRadius;
    public static double meanHealthGoal;
    public static double meanSpeed;

    public static final Calvetti ROBSON = new Calvetti();
    public int spriteID;
    public static boolean switchCalvetti = false;

    public static Connection conn = null;
    public long startTime;
    public int counterDB=0;
    public int counterST=0;
    public long now = 0;
    public double secondsElapsed = 0;
    

    //Construtor
    public FloatingCircles() {
        
        timer = new Timer();
        isPaused = false;
        
        toReset = false;
        toBake = false;

        spawnUnit = false;

        circles = new ArrayList<>(); //!!!!
        baby_circles = new ArrayList<>();
        foods = new ArrayList<>();

        //preparing data for quick statistics
        circlesRadius = new ArrayList<>();
        circlesHealthGoal = new ArrayList<>();
        circlesSpeed = new ArrayList<>();

        //creating calvetti sprite sheet
        spriteID = Integer.parseInt(String.valueOf(ROBSON.getSpriteSheet().size()));

        //initializigng default initial circles
        Spawners.initializeCircles(circles);

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        bufferGraphics = buffer.getGraphics();

    }
    

    //Função chamada para resetar os círculos e comida.
    // Chamda em Screen.java no botão de reset
    public void resetCanvas(){
        
        circles.clear();
        baby_circles.clear();
        foods.clear();

        crowdActions.newGen = 0;
        startTime = System.nanoTime();
    }

    
    //Função chamada para resetar a simulação e mudar os 
    //      parâmetros dela de a cordo com os sliders.
    // Chamda em Screen.java no botão de reset
    public void bake(){

        resetCanvas();
        Spawners.initializeCircles(circles);

    }

    
    //Função que vai realizar todas ações que envolvam posição, movimentação
    // dos círculos, assim como a sua capacidade de alimentar e se reproduzir.
    public void updateCircles() {
         
        //limpando as Arrays contendo os valores de raio, velocidade
        // dos circulos usados para calcular estatísticas em tempo real
        circlesRadius.clear();
        circlesHealthGoal.clear();
        circlesSpeed.clear();

        //Executando tarefas que envolvam os círculos individualmente,
        // checar movimentação, checar comida, e envelhecer.
        for (Circle circle : circles) {

            //Chamando as funções de movimentação, e envelhecimento
            circle.move();
            crowdActions.checkBounds(circle);
            circle.age();


            //Agora um circulo irá iterar sob todas comidas existentes
            // se estiver dentro da área do circulo, o círculo recebe vida
            // e a comida é removida
            for (Food food : foods){
                crowdActions.checkFood(circle, food);}
            foods.removeIf(t -> t.isEaten() == true);


            // tryReproduce, tentará reproduzir o indivíduo circle, caso nascer
            // a lista babycircles irá receber o novo organismo
            crowdActions.tryReproduce(circle, baby_circles);

            //Obtaining the oldest alive circle
            crowdActions.newGen = crowdActions.getGen(crowdActions.newGen, circle.getGen());

            //adding the radius value for each circle
            circlesRadius.add(circle.getRadius());
            //adding the health_goal value for each circle
            circlesHealthGoal.add(circle.getHealthGoal());
            //adding the total speed value for each circle
            circlesSpeed.add(circle.getFullSpeed());
            
        }
        //Removendo todos circulos que possuem valor de .alive como falso
        // .alive sempre será true enquanto .health não chegar a 0
        circles.removeIf(t -> t.alive == false);

        //Adicionando os novos circulos à lista de circulos, e limpando a lista de baby_circles
        for (Circle baby_circle : baby_circles){
            circles.add(baby_circle);}
        baby_circles.clear();



        now = System.nanoTime();
        secondsElapsed = (now - startTime) / 1_000_000_000.0;
    
        if(counterDB==200){
        UtilsDB.insertTable(conn, secondsElapsed ,helper.calculateMean(circlesSpeed) , helper.calculateMean(circlesRadius),helper.calculateMean(circlesHealthGoal) , Integer.parseInt(countAlive()));
        counterDB = 0;
        }
        else{counterDB = counterDB+1;}
    }




    // Funções para Estatísticas calculadas em tempo real.

    //Função para obter uma cópia da lista de circulos, porém armazenado em uma variável estática
    public void setStaticVariable(List<Circle> circles) {
        circles_cpy = circles;
    }
    //Retorna valor de círculos vivos em string
    public static String countAlive(){
        try{
        return String.valueOf(circles_cpy.size());}
        catch(Exception e){
            return "0";}}

    //Registra nas variáveis a média atual de raio, velocidade e healthGoal dos círculos
    public void calculateStatistics(){ 
        meanRadius = helper.calculateMean(circlesRadius);
        meanHealthGoal = helper.calculateMean(circlesHealthGoal);
        meanSpeed = helper.calculateMean(circlesSpeed);
    }





    
    public void initializeWindow() {
        setTitle("Floating Circles");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }





    
    @Override
    public void paint(Graphics g) {
        Color BACKGROUND_COLOR = new Color(248, 232, 238);
        bufferGraphics.setColor(BACKGROUND_COLOR);
        bufferGraphics.fillRect(0, 0, WIDTH, HEIGHT);
    
        for (Circle circle : circles) {

            bufferGraphics.setColor(circle.getColor());
            bufferGraphics.fillOval((int) (circle.getX()-circle.getRadius()),
                                    (int) (circle.getY()-circle.getRadius()), 
                                    (int) circle.getRadius() * 2, 
                                    (int) circle.getRadius() * 2);
            bufferGraphics.setColor(new Color(0,0,0));

        }

        for (Food food : foods) {

            try{
                bufferGraphics.setColor(food.getColor());
                bufferGraphics.fillOval((int) food.getX(),
                                        (int) food.getY(),
                                        (int) food.getRadius() * 2,
                                        (int) food.getRadius() * 2);
            }
            catch(Exception e){}
        }
        if(switchCalvetti){
        bufferGraphics.drawImage(ROBSON.getSpriteSheet().get(spriteID), WIDTH - ROBSON.getWidth(), HEIGHT - ROBSON.getHeight(), null);
        }

        g.drawImage(buffer, 0, 0, null);
    }


    public void startTimer(Timer timer,Connection Conn){
        
        timer.scheduleAtFixedRate(new TimerTask() {
            int calvettiMeter = 0;
            @Override
            public void run(){ 
                
                if(switchCalvetti){
                if(calvettiMeter%(FPS) == 0){
                    spriteID = (spriteID + 1)%4;
                }
                calvettiMeter += 1;
                }

                if(!isPaused){
                    updateCircles();
                    repaint();
                    Spawners.spawnFood(foods);
                    setStaticVariable(circles);
                    calculateStatistics();
                }
                if(toReset){
                    startTime = System.nanoTime();
                    resetCanvas();
                    UtilsDB.clearTable(conn);
                    startTime=0;
                    secondsElapsed=0;
                    now=System.nanoTime();
                    toReset = false;
                }
                if(spawnUnit){
                    Spawners.spawnCircle(circles);
                    spawnUnit = false;
                }
                if(toBake){
                    bake();
                    toBake = false;
                }
            }


        }, 0, FPS);
    }


    public void run(){
        if(Screen.end==false){
        initializeWindow();
        
        try{ConexaoDB bd = new ConexaoDB();
        conn = bd.conectar();
        }
        catch (SQLException e1){
            System.out.println(e1.getStackTrace());
        }
        if (conn == null) {
            throw new RuntimeException("Falha ao conectar com o banco de dados.");
                        }
        System.out.println("Conexao feita!");

        UtilsDB.createTableIfNotExists(conn);

        UtilsDB.clearTable(conn);

        startTimer(timer,conn);

        startTime = System.nanoTime();

        UtilsDB.createTableIfNotExists(conn);

        UtilsDB.clearTable(conn);
        }
        
    }

}

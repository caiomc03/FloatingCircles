package src.App;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import src.FloatingCircles;
import src.Stat;
import src.crowdActions;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen {

    public static boolean end = false;

    public void run() {

        JFrame frame = new JFrame("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700, 400));

        // Create panelLeft to hold the left side components
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(new BorderLayout());
        panelLeft.setBackground(new Color(180, 200, 255)); // Set light faded blue color

        JPanel panelLabel = new JPanel();

        panelLabel.setLayout(new GridLayout(2, 1));
        panelLabel.setBackground(new Color(211, 227, 237));

        JLabel labelAlive = new JLabel();
        JLabel labelGen = new JLabel();

        panelLabel.add(labelAlive);
        panelLabel.add(labelGen);

        // Then the buttons we will add, start/stop and the reset
        JPanel panelButtons = new JPanel();

        panelButtons.setLayout(new FlowLayout());
        panelLabel.setPreferredSize(new Dimension(600, 100));
        panelButtons.setBackground(new Color(180, 200, 255));

        JButton playPauseButton = new JButton("|▷");
        JButton resetButton = new JButton("Reset");
        JButton spawnButton = new JButton("Spawn Circle");
        JButton calvettiButton = new JButton("Spawn Calvetti");

        panelButtons.add(playPauseButton);
        panelButtons.add(resetButton);
        panelButtons.add(spawnButton);
        panelButtons.add(calvettiButton);

        JButton endButton = new JButton("End Simulation");
        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Deseja realmente encerrar a simulação?",
                        "Confirmação", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    frame.dispose(); // Fecha o JFrame
                    Stat.statistic(FloatingCircles.conn, "time", "countalive", "healthgoal", "size", "speed");

                    // openStatisticsWindow(FloatingCircles.conn,"time", "countalive"); // Abre a
                    // nova janela de estatísticas
                }
            }
        });
        panelButtons.add(endButton);

        // Create the bottom panel with sliders
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        JLabel sliderLabelFoodSpawn = new JLabel("Food Spawn: ");
        JLabel sliderLabelCircleSpawn = new JLabel("Circle Spawn: ");
        JLabel sliderLabelInitRadius = new JLabel("Initial Radius: ");
        JLabel sliderLabelInitSpeed = new JLabel("Initial Speed: ");
        JLabel sliderLabelInitHealthGoal = new JLabel("Initial Health Goal: ");

        JSlider sliderFoodSpawn = new JSlider(0, 10, 5);
        JSlider sliderCircleSpawn = new JSlider(1, 500, 30);
        JSlider sliderInitRadius = new JSlider(5, 30, 15);
        JSlider sliderInitSpeed = new JSlider(5, 200, 10);
        JSlider sliderInitHealthGoal = new JSlider(0, 255);

        JLabel valueLabelFoodSpawn = new JLabel();
        JLabel valueLabelCircleSpawn = new JLabel();
        JLabel valueLabelInitRadius = new JLabel();
        JLabel valueLabelInitSpeed = new JLabel();
        JLabel valueLabelinitHealthGoal = new JLabel();

        sliderFoodSpawn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = sliderFoodSpawn.getValue();
                valueLabelFoodSpawn.setText(String.valueOf(value));
            }
        });

        sliderCircleSpawn.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = sliderCircleSpawn.getValue();
                valueLabelCircleSpawn.setText(String.valueOf(value));
            }
        });

        sliderInitRadius.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = sliderInitRadius.getValue();
                valueLabelInitRadius.setText(String.valueOf(value));
            }
        });

        sliderInitSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = sliderInitSpeed.getValue();
                valueLabelInitSpeed.setText(String.valueOf(value));
            }
        });

        sliderInitHealthGoal.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = sliderInitHealthGoal.getValue();
                valueLabelinitHealthGoal.setText(String.valueOf(value));
            }
        });

        sliderPanel.add(sliderLabelFoodSpawn);
        sliderPanel.add(valueLabelFoodSpawn);
        sliderPanel.add(sliderFoodSpawn);

        sliderPanel.add(sliderLabelCircleSpawn);
        sliderPanel.add(valueLabelCircleSpawn);
        sliderPanel.add(sliderCircleSpawn);

        sliderPanel.add(sliderLabelInitRadius);
        sliderPanel.add(valueLabelInitRadius);
        sliderPanel.add(sliderInitRadius);

        sliderPanel.add(sliderLabelInitSpeed);
        sliderPanel.add(valueLabelInitSpeed);
        sliderPanel.add(sliderInitSpeed);

        sliderPanel.add(sliderLabelInitHealthGoal);
        sliderPanel.add(valueLabelinitHealthGoal);
        sliderPanel.add(sliderInitHealthGoal);

        JButton buttonBake = new JButton("BAKE");
        sliderPanel.add(buttonBake);

        // Now setting the functionalities of the buttons
        // RESET BUTTON
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setToReset(getToReset());

            }
        });

        // PLAY & PAUSE BUTTON
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setPaused(getPaused());

            }
        });

        // SPAWN UNIT BUTTON
        spawnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSpawnUnit(getSpawnUnit());
            }
        });

        // TURN CALVETTI ON/OFF
        calvettiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSwitchCalvetti(getSwitchCalvetti());
            }
        });

        // Create the JSplitPane for labels and buttons
        JSplitPane labelButtonSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelLabel, panelButtons);
        labelButtonSplitPane.setResizeWeight(0.5);

        // Create the JSplitPane for labelButtonSplitPane and sliderPanel
        JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, labelButtonSplitPane, sliderPanel);
        leftSplitPane.setResizeWeight(0.5);

        // Add the leftSplitPane to panelLeft
        panelLeft.add(leftSplitPane, BorderLayout.CENTER);

        // Create panelTopRight FOR THE INITIAL CONDITIONS OF THE SIM
        JPanel panelTopRight = new JPanel();
        panelTopRight.setLayout(new BoxLayout(panelTopRight, BoxLayout.Y_AXIS));
        panelTopRight.setBackground(new Color(190, 255, 200)); // Set light faded green color

        JLabel labelFoodSpawn = new JLabel("Food Spawn: " + "Nan");
        JLabel labelCircleSpawn = new JLabel("Initial Circles: " + "Nan");
        JLabel labelInitRadius = new JLabel("Initial Radius: " + "Nan");
        JLabel labelInitSpeed = new JLabel("Initial Speed: " + "Nan");
        JLabel labelInitHealthGoal = new JLabel("Initial Health Goal: " + "Nan");

        panelTopRight.add(labelFoodSpawn);
        panelTopRight.add(labelCircleSpawn);
        panelTopRight.add(labelInitRadius);
        panelTopRight.add(labelInitSpeed);
        panelTopRight.add(labelInitHealthGoal);

        // making the BAKE functionality
        buttonBake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                labelFoodSpawn.setText("Food Spawn: " + String.valueOf(sliderFoodSpawn.getValue()));
                labelCircleSpawn.setText("Initial Circles: " + String.valueOf(sliderCircleSpawn.getValue()));
                labelInitRadius.setText("Initial Radius: " + String.valueOf(sliderInitRadius.getValue()));
                labelInitSpeed.setText("Initial Speed: " + String.valueOf(sliderInitSpeed.getValue()));
                labelInitHealthGoal.setText("Initial Health Goal: " + String.valueOf(sliderInitHealthGoal.getValue()));

                FloatingCircles.FOOD_RATE = (Double.parseDouble(String.valueOf(sliderFoodSpawn.getValue())) / 10);
                FloatingCircles.NUM_CIRCLES = sliderCircleSpawn.getValue();
                FloatingCircles.CIRCLE_RADIUS = sliderInitRadius.getValue();
                FloatingCircles.MAX_VELOCITY = sliderInitSpeed.getValue();
                FloatingCircles.HEALTH_GOAL = sliderInitHealthGoal.getValue();

                setToBake(getToBake());
            }
        });

        // Create panelMidRight FOR THE PRESENT CONDITIONS OF THE SIM
        JPanel panelMidRight = new JPanel();
        panelMidRight.setLayout(new BoxLayout(panelMidRight, BoxLayout.Y_AXIS));
        panelMidRight.setBackground(new Color(180, 255, 180)); // Set light faded green color

        JLabel labelMeanRadius = new JLabel();
        JLabel labelMeanHealthGoal = new JLabel();
        JLabel labelMeanSpeed = new JLabel();

        panelMidRight.add(labelMeanRadius);
        panelMidRight.add(labelMeanSpeed);
        panelMidRight.add(labelMeanHealthGoal);

        // Create the JSplitPane for panelTopRight and panelMidRight
        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelTopRight, panelMidRight);
        rightSplitPane.setResizeWeight(0.5);

        // Create panelRight to hold the right side components
        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        panelRight.add(rightSplitPane, BorderLayout.CENTER);

        // Create JSplitPane to split panelLeft and panelRight vertically
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);
        splitPane.setResizeWeight(0.5); // Equal width distribution

        // Add JSplitPane to the frame
        frame.add(splitPane);

        frame.pack();
        frame.setVisible(true);

        //Loop vai garantir que os Labels vão ser atualizados em tempo real para ao usuário
        while (true) {
            try {
                String textAlive = "Alive: " + FloatingCircles.countAlive();
                labelAlive.setText(textAlive);

                String textGen = "Last Gen: " + crowdActions.getGenString();
                labelGen.setText(textGen);

                String textMeanRadius = "Mean Radius: " + FloatingCircles.meanRadius;
                labelMeanRadius.setText(textMeanRadius);

                String textMeanHealthGoal = "Health Goal: " + FloatingCircles.meanHealthGoal;
                labelMeanHealthGoal.setText(textMeanHealthGoal);

                String textMeanSpeed = "Mean Speed: " + FloatingCircles.meanSpeed;
                labelMeanSpeed.setText(textMeanSpeed);
            } catch (Exception e) {
            }

        }

    }

    public boolean getToBake() {
        return FloatingCircles.toBake;
    }

    public void setToBake(boolean state) {
        FloatingCircles.toBake = !state;
    }

    public boolean getSwitchCalvetti() {
        return FloatingCircles.switchCalvetti;
    }

    public void setSwitchCalvetti(boolean state) {
        FloatingCircles.switchCalvetti = !state;
    }

    public boolean getSpawnUnit() {
        return FloatingCircles.toReset;
    }

    public void setSpawnUnit(boolean state) {
        FloatingCircles.spawnUnit = !state;
    }

    public boolean getToReset() {
        return FloatingCircles.toReset;
    }

    public void setToReset(boolean state) {
        FloatingCircles.toReset = !state;
    }

    public boolean getPaused() {
        return FloatingCircles.isPaused;
    }

    public void setPaused(boolean state) {
        FloatingCircles.isPaused = !state;
    }

    public void adjustSlider(int min, int max, JSlider slider) {
        slider.setBounds(max, max, min, max);
    }

}
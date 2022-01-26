package agh.Visualization;

import agh.World.World;
import agh.WorldElements.Animal;
import agh.WorldElements.Square;
import agh.WorldElements.Gene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.io.*;

public class Main extends JPanel implements ActionListener{

    static int allAnimals=0;
    static int allPlants=0;
    static double avgAllEnergy=0;
    static double avgAllKids=0;

    private JFrame frame;
    private static Button pauseButton;
    private static Button pauseButtonSecond;
    private static Button loadToFile;
    private static boolean pause;
    private static boolean pauseSecond;

    private static JPanel statisticsTwo = new Statistics();
    private static double avgLiveDuration=0;
    private static int animalsDead=0;

    private static int numberOfDays;
    private static int numberOfDaysSecond;

    public static Square[][] makeMap(int x, int y) {
        Square[][] map = new Square[x][y];
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                map[i][j] = new Square(i, j);
            }
        return map;
    }

    public static void moveRandomAllAnimals(LinkedList<Animal> animalsList, Square[][] map) {
        for (int i = 0; i < animalsList.size(); i++) {
            animalsList.get(i).makeRotation();
            animalsList.get(i).move(map, animalsList.get(i), World.getMapHeight(), World.getMapWidth());
        }
    }

    public static void removeAllDeadAnimals(LinkedList<Animal> animalsList) {
        for (int i = 0; i < animalsList.size(); i++) {
            if (animalsList.get(i).energy <= 0) {
                animalsList.remove(i);
            }
        }
    }

    public static void energyUse(LinkedList<Animal> animalsList) {
        for (int i = 0; i < animalsList.size(); i++) {
            animalsList.get(i).energy -= World.getDayEnergyCost();
            if(animalsList.get(i).energy < 0)
                animalsList.get(i).energy = 0;
            System.out.println(animalsList.get(i).energy);
        }
    }

    public static void breeding(LinkedList<Animal> animalsList, Square[][] map) {
        for (Square x[] : map) {
            for (Square y : x) {
                if (y.amountOfAnimals > 1 && y.firstAnimal.energy > World.getCopulationMinimumEnergy() && y.secondAnimal.energy > World.getCopulationMinimumEnergy()) {
                    y.firstAnimal.kids++;
                    y.secondAnimal.kids++;
                    Animal child = new Animal(y.firstAnimal, y.secondAnimal);
                    animalsList.add(child);
                    child.move(map, child, World.getMapHeight(), World.getMapWidth());
                }
            }
        }
    }

    public static void eating(LinkedList<Animal> animalsList, Square[][] map) {
        for (Square x[] : map) {
            for (Square y : x) {
                if (y.grass && y.amountOfAnimals > 0) {
                    if (y.amountOfAnimals == 1)
                    {
                        y.firstAnimal.energy += World.getGrassEatingEnergyProfit();
                        if(y.firstAnimal.energy > World.getAnimalsMaxEnergy())
                            y.firstAnimal.energy = World.getAnimalsMaxEnergy();
                    }
                    else if (y.equalAnimals(y.firstAnimal, y.secondAnimal)) {
                        y.firstAnimal.energy += (int) (0.5 * World.getGrassEatingEnergyProfit());
                        y.secondAnimal.energy += (int) (0.5 * World.getGrassEatingEnergyProfit());
                        if(y.firstAnimal.energy>World.getAnimalsMaxEnergy())
                        {
                            y.firstAnimal.energy = World.getAnimalsMaxEnergy();
                            y.secondAnimal.energy = World.getAnimalsMaxEnergy();
                        }
                    } else {
                        y.selectStrongerAnimal(y.firstAnimal, y.secondAnimal).energy += World.getGrassEatingEnergyProfit();
                        if (y.selectStrongerAnimal(y.firstAnimal, y.secondAnimal).energy > World.getAnimalsMaxEnergy())
                            y.selectStrongerAnimal(y.firstAnimal, y.secondAnimal).energy = World.getGrassEatingEnergyProfit();
                    }
                    y.grass = false;
                }
            }
        }
    }

    public static void clearSquares(Square[][] map) {
        for (Square x[] : map) {
            for (Square y : x) {
                y.amountOfAnimals = 0;
            }
        }
    }

    public static void addJungleGrass(Square[][] map) {
        if( (World.getJungleHeight()*World.getJungleWidth()) > sumJungleGrass(map)) {
            int a = (int) (Math.random() * World.getJungleHeight() + (World.getMapHeight() / 2 - World.getJungleHeight() / 2));
            int b = (int) (Math.random() * World.getJungleWidth() + (World.getMapWidth() / 2 - World.getJungleWidth() / 2));
            while (map[a][b].grass || map[a][b].amountOfAnimals != 0) {
                a = (int) (Math.random() * World.getJungleHeight() + (World.getMapHeight() / 2 - World.getJungleHeight() / 2));
                b = (int) (Math.random() * World.getJungleWidth() + (World.getMapWidth() / 2 - World.getJungleWidth() / 2));
            }
            map[a][b].grass = true;
        }
    }

    public static void addDesertGrass(Square[][] map) {
        int a = (int) (Math.random() * World.getMapHeight());
        int b = (int) (Math.random() * World.getMapWidth());
        while (map[a][b].grass || map[a][b].amountOfAnimals != 0 || map[a][b].jungle) {
            a = (int) (Math.random() * World.getMapHeight());
            b = (int) (Math.random() * World.getMapWidth());
        }
        map[a][b].grass = true;
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource()==pauseButton)
        {
            pause= !pause;
        }
        else if (evt.getSource()==pauseButtonSecond)
        {
            pauseSecond= !pauseSecond;
        }
        else if (evt.getSource()==loadToFile)
        {
            try {
                PrintWriter load = new PrintWriter("dane statystyczne.txt");
                load.println("Liczba zwierząt "+  allAnimals/numberOfDays);
                load.println("Liczba roślin "+  allPlants/numberOfDays);
                load.println("Dominujący genotyp "+  Statistics.getDominatingGene());
                load.println("Średni poziom energi "+  avgAllEnergy/numberOfDays);
                load.println("Średnia długość życia "+  Statistics.getAvgLiveDuration());
                load.println("Średnia liczba dzieci "+  avgAllKids/numberOfDays);
                load.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Interval Error #004");
            }
        }
    }

    public static void setStatistics(Square[][] map,LinkedList<Animal> animalsList, int days) {
        int animalsLive=0;
        int plants=0;
        double avgEnergy=0;
        double avgKids=0;


        for (Square x[] : map) {
            for (Square y : x) {
                if(y.grass) plants++;
            }
        }

        for (int i = 0; i < animalsList.size(); i++) {
            animalsList.get(i).liveDuration++;
            if (animalsList.get(i).energy <= 0) {
                avgLiveDuration +=animalsList.get(i).liveDuration;
                animalsDead++;
            }
            animalsLive++;
            avgEnergy += animalsList.get(i).energy;
            avgKids += animalsList.get(i).kids;
        }
        Statistics.setDominatingGene(dominatingGene(animalsList));
        allAnimals+=animalsLive;
        Statistics.setAnimalsLive(animalsLive);
        allPlants+=plants;
        Statistics.setPlants(plants);
        Statistics.setTotalDays(days);
        avgAllEnergy+=avgEnergy/animalsLive;
        avgAllKids+=avgKids/animalsLive;
        Statistics.setAvgEnergy(avgEnergy/animalsLive);
        Statistics.setAvgKids(avgKids/animalsLive);
        Statistics.setAvgLiveDuration(avgLiveDuration/animalsDead);
    }

    public static void day(Square[][] map,LinkedList<Animal> animalsList) {
        clearSquares(map);
        removeAllDeadAnimals(animalsList);
        addJungleGrass(map);
        addDesertGrass(map);
        moveRandomAllAnimals(animalsList, map);
        eating(animalsList, map);
        breeding(animalsList, map);
        energyUse(animalsList);
    }

    public static int sumJungleGrass(Square[][] map) {
        int i=0;
        for (Square x[] : map) {
            for (Square y : x) {
                if(y.jungle && y.grass) i++;
            }
        }
        return i;
    }

    public static String dominatingGene(LinkedList<Animal> animalsList) {

        String genStr = new String();

        HashMap<String, Integer> gens = new HashMap();
        int value;

        for(int i=0; i<animalsList.size(); i++)
        {
            genStr = geneToString(animalsList.get(i).gene);

            if(gens.containsKey(genStr))
            {
            value = gens.get(genStr);
            value++;
            gens.replace(genStr, value);
                System.out.println(gens.get(genStr));
            }
            else {
                gens.put(genStr, 0);
                System.out.println(gens.get(genStr));
            }
        }

        String key = Collections.max(gens.entrySet(), HashMap.Entry.comparingByValue()).getKey();
        System.out.println(key);
        return key;
    }

    public static String geneToString(Gene gen) {
        String genStr = new String();
        for (int i : gen.gene) genStr += "" + i;
        return genStr;
    }

    public static void addAnimals(LinkedList<Animal> animalsList,int number) {
        for(int i=0; i<number; i++)
        {
            animalsList.add(new Animal());
        }
    }

    public Main() {

             LinkedList<Animal> animalsList = new LinkedList<>();
             Square[][] map = makeMap(World.getMapHeight(),World.getMapWidth());

             LinkedList<Animal> animalsListSecond = new LinkedList<>();
             Square[][] mapSecond = makeMap(World.getMapHeight(),World.getMapWidth());

            frame = new JFrame("Evolution Simulator");
            frame.setLayout(new BorderLayout(10,5));
            frame.setSize(1500, 800);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
            panel.setBackground(Color.red);

            pauseButton = new Button("Map one");
            pauseButton.addActionListener(this);
            panel.add(pauseButton);

            pauseButtonSecond = new Button("Map two");
            pauseButtonSecond.addActionListener(this);
            panel.add(pauseButtonSecond);

            loadToFile = new Button("Load To File");
            loadToFile.addActionListener(this);
            panel.add(loadToFile);

            panel.setPreferredSize(new Dimension(30,30));
            frame.add(panel, BorderLayout.SOUTH);


            JPanel board = new GUI(map);
            board.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
            board.setBackground(Color.green);

            JPanel boardSecond = new GUI(mapSecond);
            boardSecond.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
            boardSecond.setBackground(Color.yellow);

            JPanel boards = new JPanel();
            boards.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
            boards.setBackground(Color.black);


            boards.add(board, BorderLayout.NORTH);


            boards.add(boardSecond, BorderLayout.SOUTH);

            boards.setPreferredSize(new Dimension(1000,400));
            frame.add(boards, BorderLayout.WEST);

            JPanel statistics = new Statistics();
            statistics.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
            statistics.setBackground(Color.white);

            statistics.setPreferredSize(new Dimension(400,400));
            frame.add(statistics, BorderLayout.EAST);

            frame.setVisible(true);




             addAnimals(animalsList,World.getStartNumberOfAnimals());
             addAnimals(animalsListSecond,World.getStartNumberOfAnimals());



            for (int i = 0; i < 100; i--) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    System.out.println("Interval Error #001");
                }
                Thread first = new Thread(() -> {
                    if(pause) {
                        numberOfDays++;
                        day(map,animalsList);
                        setStatistics(map,animalsList,numberOfDays);
                        statistics.repaint();
                        board.repaint();
                    }
                });
                first.start();
                Thread second = new Thread(() -> {
                    if(pauseSecond) {
                        numberOfDaysSecond++;
                        day(mapSecond,animalsListSecond);
                        boardSecond.repaint();
                    }
                });
                second.start();
            }
        }
}



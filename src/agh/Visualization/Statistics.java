package agh.Visualization;

import java.awt.*;
import javax.swing.JPanel;


public class Statistics extends JPanel {

    private static int animalsLive;
    private static int plants;
    private static int totalDays;
    private static double avgEnergy;
    private static double avgLiveDuration;
    private static double avgKids;
    private static String dominatingGene;

    public static void setAnimalsLive(int animalsLive) {
        Statistics.animalsLive = animalsLive;
    }

    public static void setPlants(int plants) {
        Statistics.plants = plants;
    }

    public static void setTotalDays(int totalDays) {
        Statistics.totalDays = totalDays;
    }

    public static void setDominatingGene(String dominatingGene) {
        Statistics.dominatingGene = dominatingGene;
    }

    public static String getDominatingGene() {
        return dominatingGene;
    }

    public static double getAvgLiveDuration() {
        return avgLiveDuration;
    }

    public static void setAvgEnergy(double avgEnergy) {
        Statistics.avgEnergy = avgEnergy;
    }

    public static void setAvgLiveDuration(double avgLiveDuration) {
        Statistics.avgLiveDuration = avgLiveDuration;
    }

    public static void setAvgKids(double avgKids) {
        Statistics.avgKids = avgKids;
    }

    public Statistics() {
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawString("Total days: " + totalDays, 10, 20);
        g.drawString("Number of animals: " + animalsLive, 10, 40);
        g.drawString("Number of plants: " + plants, 10, 60);
        g.drawString("Average energy: " + avgEnergy, 10, 80);
        g.drawString("Average live duration: " + avgLiveDuration, 10, 100);
        g.drawString("Average number of kids: " + avgKids, 10, 120);
        g.drawString("Dominating gene: " + dominatingGene, 10, 140);
    }
}
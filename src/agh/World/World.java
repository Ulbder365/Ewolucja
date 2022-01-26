package agh.World;

import agh.WorldElements.Animal;

public class World extends Animal {

    static int mapWidth = 30;
    static int mapHeight = 30;
    static int jungleWidth = 10;
    static int jungleHeight = 10;
    static int grassEatingEnergyProfit = 30;
    static int dayEnergyCost = 2;
    static int copulationMinimumEnergy = 50;
    static int animalsStartEnergy= 100;
    static int animalsMaxEnergy= 100;
    static int startNumberOfAnimals=10;

    public static int getStartNumberOfAnimals() {
        return startNumberOfAnimals;
    }

    public World(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight, int grassEatingEnergyProfit, int dayEnergyCost, int copulationMinimumEnergy, int animalsStartEnergy, int startNumberOfAnimals) {

        World.mapWidth = mapWidth;
        World.mapHeight = mapHeight;
        World.jungleWidth = jungleWidth;
        World.jungleHeight = jungleHeight;
        World.grassEatingEnergyProfit = grassEatingEnergyProfit;
        World.dayEnergyCost = dayEnergyCost;
        World.copulationMinimumEnergy = copulationMinimumEnergy;
        World.animalsStartEnergy = animalsStartEnergy;
        World.startNumberOfAnimals = startNumberOfAnimals;
    }

    public static int getJungleWidth() {
        return jungleWidth;
    }

    public static int getJungleHeight() {
        return jungleHeight;
    }

    public static int getGrassEatingEnergyProfit() { return grassEatingEnergyProfit; }

    public static int getMapWidth() {
        return mapWidth;
    }

    public static int getDayEnergyCost() {
        return dayEnergyCost;
    }

    public static int getAnimalsStartEnergy() {
        return animalsStartEnergy;
    }

    public static int getCopulationMinimumEnergy() {
        return copulationMinimumEnergy;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    public static int getAnimalsMaxEnergy() {
        return animalsMaxEnergy;
    }
}



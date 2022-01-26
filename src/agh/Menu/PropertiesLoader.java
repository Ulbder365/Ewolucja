package agh.Menu;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PropertiesLoader {

    private int mapWidth;
    private int mapHeight;
    private int jungleWidth;
    private int jungleHeight;
    private int grassEatingEnergyProfit;
    private int dayEnergyCost;
    private int copulationMinimumEnergy;
    private int animalsStartEnergy;
    private int startNumberOfAnimals;


    static public PropertiesLoader loadPropFromFile() throws FileNotFoundException,IllegalArgumentException {
        Gson gson = new Gson();
        PropertiesLoader properties =  (PropertiesLoader)gson.fromJson(new FileReader("src\\agh\\Menu\\DefaultProperties.json"), PropertiesLoader.class);
        properties.validate();
        return properties;
    }

    public void validate() throws IllegalArgumentException {
        if(this.mapWidth <= 0){ throw new IllegalArgumentException("Invalid map width");}
        if(this.mapHeight <= 0){ throw new IllegalArgumentException("Invalid map height");}
        if(this.jungleHeight <= 0){ throw new IllegalArgumentException("Invalid jungle width");}
        if(this.jungleWidth<= 0){ throw new IllegalArgumentException("Invalid jungle height");}
        if(this.grassEatingEnergyProfit <= 0){ throw new IllegalArgumentException("Invalid grassEatingEnergyProfit");}
        if(this.dayEnergyCost < 0){ throw new IllegalArgumentException("Invalid dayEnergyCost");}
        if(this.copulationMinimumEnergy <= 0){ throw new IllegalArgumentException("Invalid copulationMinimumEnergy");}
        if(this.animalsStartEnergy < 0){ throw new IllegalArgumentException("Invalid animalsStartEnergy");}
        if(this.startNumberOfAnimals < 0){ throw new IllegalArgumentException("startNumberOfAnimals");}
    }

    public int getMapWidth() {
        return mapWidth;
    }
    public int getMapHeight() {
        return mapHeight;
    }
    public int getJungleWidth() {
        return jungleWidth;
    }
    public int getJungleHeight() {
        return jungleHeight;
    }
    public int getGrassEatingEnergyProfit() {
        return grassEatingEnergyProfit;
    }
    public int getDayEnergyCost() {
        return dayEnergyCost;
    }
    public int getCopulationMinimumEnergy() { return copulationMinimumEnergy; }
    public int getAnimalsStartEnergy() { return animalsStartEnergy; }
    public int getStartNumberOfAnimals() { return startNumberOfAnimals; }

}

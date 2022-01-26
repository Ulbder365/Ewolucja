package agh.World;

import agh.Menu.PropertiesLoader;
import agh.Visualization.Main;

import java.io.FileNotFoundException;

public class Simulation {

    public static void main(String[] args) {

        try {

            PropertiesLoader properties = PropertiesLoader.loadPropFromFile();

            Integer[] defaultMapProperties = {
                    properties.getMapWidth(),
                    properties.getMapHeight(),
                    properties.getJungleWidth(),
                    properties.getJungleHeight(),
                    properties.getGrassEatingEnergyProfit(),
                    properties.getDayEnergyCost(),
                    properties.getCopulationMinimumEnergy(),
                    properties.getAnimalsStartEnergy(),
                    properties.getStartNumberOfAnimals(),
            };

            World map = new World(

                    Integer.parseInt(defaultMapProperties[0].toString()),
                    Integer.parseInt(defaultMapProperties[1].toString()),
                    Integer.parseInt(defaultMapProperties[2].toString()),
                    Integer.parseInt(defaultMapProperties[3].toString()),
                    Integer.parseInt(defaultMapProperties[4].toString()),
                    Integer.parseInt(defaultMapProperties[5].toString()),
                    Integer.parseInt(defaultMapProperties[6].toString()),
                    Integer.parseInt(defaultMapProperties[7].toString()),
                    Integer.parseInt(defaultMapProperties[8].toString())
            );
            Main main = new Main();

        } catch (IllegalArgumentException ex) { System.out.println("Interval Error #002"); return; }
        catch (FileNotFoundException ex) { System.out.println("Interval Error #003"); return;
        }
    }

}
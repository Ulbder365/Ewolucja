package agh.WorldElements;

import agh.EnumClasses.Direction;
import agh.World.World;


import java.lang.reflect.Array;

public class Animal {

    public Direction direction;
    public double energy;
    public Gene gene;
    public int x;
    public int y;
    public int kids = 0;
    public int liveDuration = 0;


    public Animal() {
    randomDirection();
    energy = World.getAnimalsStartEnergy();
    gene = new Gene();
    x = (int) (0.5 * World.getMapHeight());
    y = (int) (0.5 * World.getMapWidth());
    }

    public Animal(Animal firstParent, Animal secondParent) {
        energy = 0.25 * (firstParent.energy + secondParent.energy);
        gene = new Gene(firstParent.gene, secondParent.gene);
        x = secondParent.x;
        y = secondParent.y;
        randomDirection();

    }

    public boolean outOfBorder(int x, int y, int mapHeight, int mapWidth)
    {
        if(x>=0 && x<mapHeight && y>=0 && y<mapWidth) return true;
        else return  false;
    }

    public void selectTwoStrongestAnimals(Square[][] map, Animal animal)
    {
        if(map[x][y].amountOfAnimals == 1) map[x][y].firstAnimal=animal;
        else if (map[x][y].amountOfAnimals == 2) map[x][y].secondAnimal=animal;
        else
        {
            if(map[x][y].firstAnimal.energy >= map[x][y].secondAnimal.energy)
            {
                if(map[x][y].secondAnimal.energy < animal.energy) map[x][y].secondAnimal=animal;
            }
            else
            {
                if(map[x][y].firstAnimal.energy < animal.energy) map[x][y].firstAnimal=animal;
            }
        }
    }

    public void moveCase (int xChange, int yChange, Square[][] map, Animal animal, int mapHeight, int mapWidth)
    {
        if(outOfBorder((x+xChange),(y+yChange), mapHeight, mapWidth) && map[x+xChange][y+yChange].amountOfAnimals < 3)
        {
            x = x + xChange;
            y = y + yChange;
            map[x][y].amountOfAnimals++;
            selectTwoStrongestAnimals(map, animal);
        }
       else
       {
           randomDirection();
           move(map, animal,World.getMapHeight(), World.getMapWidth());
       }
    }

    public void makeRotation()
    {
        int counter = 0;
        String[] table = {"NORTH", "NORTHEAST", "EAST", "SOUTHEAST", "SOUTH", "SOUTHWEST", "WEST", "NORTHWEST"};
        for(int i =0; i < 8; i++)
        {
            if(table[i].equals(String.valueOf(direction)))
            {
                counter = (i + gene.randomGene()) % 8;
            }
        }

        switch (counter) {
            case 0:
                direction = Direction.NORTH;
                break;
            case 1:
                direction = Direction.NORTHEAST;
                break;
            case 2:
                direction = Direction.EAST;
                break;
            case 3:
                direction = Direction.SOUTHEAST;
                break;
            case 4:
                direction = Direction.SOUTH;
                break;
            case 5:
                direction = Direction.SOUTHWEST;
                break;
            case 6:
                direction = Direction.WEST;
                break;
            case 7:
                direction = Direction.NORTHWEST;
                break;
        }
    }

    public void randomDirection() {
        switch ((int) (Math.random() * 8)) {
            case 0:
                direction = Direction.NORTH;
                break;
            case 1:
                direction = Direction.NORTHEAST;
                break;
            case 2:
                direction = Direction.EAST;
                break;
            case 3:
                direction = Direction.SOUTHEAST;
                break;
            case 4:
                direction = Direction.SOUTH;
                break;
            case 5:
                direction = Direction.SOUTHWEST;
                break;
            case 6:
                direction = Direction.WEST;
                break;
            case 7:
                direction = Direction.NORTHWEST;
                break;
        }
    }


    public void move(Square[][] map, Animal animal, int mapHeight, int mapWidth) {

        switch (direction) {
            case NORTH:
                moveCase( 1,0, map, animal, mapHeight, mapWidth);
                break;
            case NORTHEAST:
                moveCase( 1,1, map, animal, mapHeight, mapWidth);
                break;
            case EAST:
                moveCase( 0,1, map, animal, mapHeight, mapWidth);
                break;
            case SOUTHEAST:
                moveCase(-1,1, map, animal, mapHeight, mapWidth);
                break;
            case SOUTH:
                moveCase(-1,0, map, animal, mapHeight, mapWidth);
                break;
            case SOUTHWEST:
                moveCase(-1,-1, map, animal, mapHeight, mapWidth);
                break;
            case WEST:
                moveCase( 0,-1, map, animal, mapHeight, mapWidth);
                break;
            case NORTHWEST:
                moveCase( 1,-1, map, animal, mapHeight, mapWidth);
                break;
        }
    }

}
package agh.WorldElements;

import agh.World.World;

public class Square {
    public int amountOfAnimals;
    public boolean grass;
    public Animal firstAnimal;
    public Animal secondAnimal;
    public boolean jungle;

    public Animal selectStrongerAnimal(Animal first, Animal second)
    {
        if(first.energy>second.energy) return  first;
        else return second;
    }

    public boolean equalAnimals (Animal first, Animal second)
    {
        if(first.energy==second.energy) return true;
        else return false;
    }

    public Square(int x, int y)
    {
        amountOfAnimals = 0;
        grass = false;

        if(x < World.getMapHeight()/2 + World.getJungleHeight()/2 && x >= World.getMapHeight()/2 - World.getJungleHeight()/2 &&
        y < World.getMapWidth()/2 + World.getJungleWidth()/2 && y >= World.getMapWidth()/2 - World.getJungleWidth()/2)
        {
            jungle=true;
        }
    }

}

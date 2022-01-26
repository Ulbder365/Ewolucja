package agh.Visualization;

import java.awt.*;
import javax.swing.*;

import agh.World.World;
import agh.WorldElements.Square;
import agh.Visualization.Main;

public class GUI extends JPanel {

    public Square[][] map;
    public GUI(Square[][] map) {
        this.map=map;
    }

    Color Desert = new Color(100, 70, 40);
    Color desertGrass = new Color(100, 120, 40);
    Color Jungle = new Color(30, 150, 70);
    Color jungleGrass = new Color(30, 200, 70);


    @Override
    public void paint(Graphics g)
    {
        int i=0,j=0;
        int energy=0;
        for(Square x[]: map)
        {
            j=0;
            for(Square y:x) {
                if (y.amountOfAnimals > 0) {
                    energy = (int) (y.firstAnimal.energy/World.getAnimalsMaxEnergy()*255);
                    g.setColor(new Color(energy, energy, energy));
                    g.fillRect(i * 10, j * 10, 10, 10);
                }

                else if (y.jungle && !y.grass) {
                    g.setColor(Jungle);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }

                else if(y.jungle){
                    g.setColor(jungleGrass);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }

                else if(!y.jungle && y.grass) {
                    g.setColor(desertGrass);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }

                else {
                    g.setColor(Desert);
                    g.fillRect(i * 10, j * 10, 10, 10);
                }


                j++;
            }
            i++;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(World.getMapHeight()*10, World.getMapWidth()*10);
    }
}




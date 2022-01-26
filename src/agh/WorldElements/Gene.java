package agh.WorldElements;

import java.util.Arrays;

public class Gene {


    public int[] gene;
    private static int size =32;
    private final int numOfGene = 8;

    public static int getSize() {
        return size;
    }

    public Gene() {
        gene = new int[size];
        createGene();
        repairGene();
    }

    public Gene(Gene firstParent, Gene secondParent) {

        gene = firstParent.gene;

        int firstPartingOfGen  = (int) (Math.random() * (size - 2) +1);
        int secondPartingOfGen = (int) (Math.random() * (size - 2) +1);
        while(firstPartingOfGen == secondPartingOfGen)
        {
            secondPartingOfGen = (int) (Math.random() * (size - 2) +1);
        }

        if(firstPartingOfGen > secondPartingOfGen)
        {
            int holder = firstPartingOfGen;
            firstPartingOfGen = secondPartingOfGen;
            secondPartingOfGen = holder;
        }

        for(int i = 0; i <= firstPartingOfGen; i++)
        {
            gene[i] = firstParent.getGene()[i];
        }
        for(int i = firstPartingOfGen; i <= secondPartingOfGen; i++)
        {
            gene[i] = secondParent.getGene()[i];
        }
        for(int i = secondPartingOfGen; i < size; i++)
        {
            gene[i] = firstParent.getGene()[i];
        }
        repairGene();
    }

    private void createGene()
    {
        for (int i = 0; i < size; i++) {
            gene[i] = (int) (Math.random() * numOfGene);
        }
    }

    private void repairGene()
    {
        int[] table = new int[numOfGene];
        System.arraycopy(numberOfGenes(), 0, table, 0, numOfGene);

        boolean correctGene = true;
        for(int i =0; i < numOfGene; i++)
        {
            if(table[i] == 0)
            {
                gene[(int) (Math.random() * size)] = i;
                correctGene = false;
            }
        }

        if(!correctGene) repairGene();
        Arrays.sort(gene);
    }

    private int[] numberOfGenes()
    {
        int[] table = new int[numOfGene];
        for(int i = 0; i < size; i++)
        {
            table[gene[i]]++;
        }
        return table;
    }

    public int randomGene()
    {
        int i = (int) (Math.random() * size);
        return gene[i];
    }

    public void showGene()
    {
        for (int i : gene) System.out.println(i);
    }

    public int[] getGene() {
        return gene;
    }

}

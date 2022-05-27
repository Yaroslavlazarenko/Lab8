package com.classes;

public class Parallelepipeds {
    private final Parallelepiped[] parallelepipeds;
    private int index;

    public Parallelepipeds(int amount) {
        parallelepipeds = new Parallelepiped[amount];
        index = -1;
    }

    public void setParallelepiped(Parallelepiped pyramid) {
        if (++index < parallelepipeds.length)
            this.parallelepipeds[index] = pyramid;
    }

    public Parallelepiped getParallelepiped(int index) {
        return this.parallelepipeds[index];
    }

    public int getCountOfCubes() {
        int countOfCubes = 0;
        for (int i = 0; i < index; i++) {
            if ((parallelepipeds[i].getParallelepipedHeight() == parallelepipeds[i].getParallelepipedWidth()) &&
                    (parallelepipeds[i].getParallelepipedWidth() == parallelepipeds[i].getParallelepipedLength()))
                countOfCubes++;
        }
        return countOfCubes;
    }
}

package com.classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParallelepipedsDatabase implements Serializable {
    private List<Parallelepiped> parallelepipedsList;

    public ParallelepipedsDatabase() {
        parallelepipedsList = new ArrayList<>();
    }

    public boolean add(Parallelepiped parallelepiped) {
        return parallelepipedsList.add(parallelepiped);
    }

    /**
     * Save data to file with given name (with Gson lib)
     *
     * @param {String} fileName — filename
     */
    public void save(String fileName) {
        Gson gson = new GsonBuilder().create();
        JsonArray json = gson.toJsonTree(parallelepipedsList).getAsJsonArray();
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(json.toString());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Retrieving data from a file into an object (with Gson lib)
     *
     * @param {String} fileName — filename
     */
    public void load(String fileName) {
        this.clear();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            this.parallelepipedsList = new Gson().fromJson(reader, new TypeToken<List<Parallelepiped>>() {
            }.getType());

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Save data to file with given name (JAVA Native)
     *
     * @param {String} fileName — filename
     */
    public void serialize(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(parallelepipedsList);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Retrieving data from a file into an object (JAVA Native)
     *
     * @param {String} fileName — filename
     */
    public void deserialize(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.parallelepipedsList = (ArrayList<Parallelepiped>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
        }
    }

    public int getCountOfCubes() {
        int countOfCubes = 0;
        for (Parallelepiped parallelepiped : parallelepipedsList) {
            if ((parallelepiped.getParallelepipedHeight() == parallelepiped.getParallelepipedWidth()) &&
                    (parallelepiped.getParallelepipedWidth() == parallelepiped.getParallelepipedLength()))
                countOfCubes++;
        }
        return countOfCubes;
    }

    public Parallelepiped getParallelepipedFromList(int index) {
        return parallelepipedsList.get(index);
    }

    public List<Parallelepiped> getParallelepipedsList() {
        return parallelepipedsList;
    }

    public void clear() {
        this.parallelepipedsList.clear();
    }

    @Override
    public String toString() {
        return "ParallelepipedsDatabase{" +
                "parallelepipedsList=" + parallelepipedsList +
                '}';
    }
}

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


public class RectanglesDatabase implements Serializable {
    private List<Rectangle> rectanglesList;

    public RectanglesDatabase() {
        rectanglesList = new ArrayList<Rectangle>();
    }

    public boolean add(Rectangle rectangle) {
        return rectanglesList.add(rectangle);
    }

    public List<Rectangle> getRectanglesList() {
        return rectanglesList;
    }

    public Rectangle getRectangleFromList(int index) {
        return rectanglesList.get(index);
    }

    /**
     * Save data to file with given name (with Gson lib)
     *
     * @param {String} fileName — filename
     */
    public void save(String fileName) {
        Gson gson = new GsonBuilder().create();
        JsonArray json = gson.toJsonTree(rectanglesList).getAsJsonArray();
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
            this.rectanglesList = new Gson().fromJson(reader, new TypeToken<List<Rectangle>>() {
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
            out.writeObject(rectanglesList);
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
            this.rectanglesList = (ArrayList<Rectangle>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("class not found");
            c.printStackTrace();
        }
    }

    public List<Rectangle> getPyramidList() {
        return rectanglesList;
    }

    public int getRectanglesMoreAverageArea() {
        int count = 0;
        double AverageArea = 0;
        for (Rectangle value : rectanglesList)
            AverageArea += value.getRectangleArea();
        AverageArea /= rectanglesList.size();
        for (Rectangle rectangle : rectanglesList)
            if (rectangle.getRectangleArea() > AverageArea)
                count++;
        return count;
    }

    /**
     * Clear this. ArrayList<>
     */
    public void clear() {
        this.rectanglesList.clear();
    }

    @Override
    public String toString() {
        return "RectanglesDatabase{" +
                "rectanglesList=" + rectanglesList +
                '}';
    }
}

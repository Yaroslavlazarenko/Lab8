package com.company;

import com.classes.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException {
        task1();
    }

    /**
     * Создать класс прямоугольник, члены класса – длины сторон a и b. Предусмотреть в классе
     * методы вычисления и вывода сведений о фигуре – длины сторон, диагоналей, периметр, площадь.
     * Создать производный класс – параллелепипед с высотой с, добавить в класс метод определения
     * объема фигуры, перегрузить методы расчета площади и вывода сведений о фигуре.
     * <p>
     * Написать программу, демонстрирующую работу с классом: дано N прямоугольников и M параллелепипедов,
     * найти количество прямоугольников, у которых площадь больше средней площади прямоугольников и
     * количество кубов (все ребра равны).
     */
    private static void task1() throws IOException {
        final int N = 3;
        final int M = 3;

        final Rectangles rectangles = new Rectangles(N);
        final RectanglesDatabase rectanglesDatabase = new RectanglesDatabase();
        final Parallelepipeds parallelepipeds = new Parallelepipeds(M);
        final ParallelepipedsDatabase parallelepipedsDatabase = new ParallelepipedsDatabase();

        int method = 1;
        int rectanglesIndex = 0;
        int parallelepipedsIndex = 0;

        //method -> (0 || 1) {0 - JAVA Native, 1 - Gson lib}

        if (getLastSession() == null) {
            setRectanglesInfo(rectanglesDatabase, rectanglesIndex, rectangles, N);
            setParallelepipedsInfo(parallelepipedsDatabase, parallelepipedsIndex, parallelepipeds, M);
            printAllObjects(rectanglesDatabase);
            printAllObjects(parallelepipedsDatabase);
            closeProgram(rectanglesDatabase, method);
        } else {
            File file = new File(getLastSession());
            try {
                if (file.exists()) {
                    if (method == 0) {
                        rectanglesDatabase.deserialize(getLastSession());
                        parallelepipedsDatabase.deserialize(getLastSession());
                    } else {
                        parallelepipedsDatabase.load(getLastSession());
                    }
                } else {
                    setRectanglesInfo(rectanglesDatabase, rectanglesIndex, rectangles, N);
                    setParallelepipedsInfo(parallelepipedsDatabase, parallelepipedsIndex, parallelepipeds, M);
                }
                printAllObjects(rectanglesDatabase);
                printAllObjects(parallelepipedsDatabase);
                closeProgram(rectanglesDatabase, method);
                closeProgram(parallelepipedsDatabase,method);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Количество прямоугольников площадь которых больше средней площади всех: " + rectangles.getRectanglesMoreAverageArea());

        System.out.println("Количество кубов: " + parallelepipeds.getCountOfCubes());
    }

    private static void closeProgram(RectanglesDatabase rectanglesDatabase, int method) {
        String timeStamp = (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date())).replaceAll("[-.?!)(,:]", "-");

        try (FileWriter writer = new FileWriter("./src/database/db.txt", false)) {
            if (method == 0) {
                writer.write("./src/database/" + timeStamp + "--native.json");
                rectanglesDatabase.serialize("./src/database/" + timeStamp + "--native.json");
            } else {
                writer.write("./src/database/" + timeStamp + "--gson.json");
                rectanglesDatabase.save("./src/database/" + timeStamp + "--gson.json");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.exit(0);
    }

    private static void closeProgram(ParallelepipedsDatabase parallelepipedsDatabase, int method) {
        String timeStamp = (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date())).replaceAll("[-.?!)(,:]", "-");

        try (FileWriter writer = new FileWriter("./src/database/db.txt", false)) {
            if (method == 0) {
                writer.write("./src/database/" + timeStamp + "--native.json");
                parallelepipedsDatabase.serialize("./src/database/" + timeStamp + "--native.json");
            } else {
                writer.write("./src/database/" + timeStamp + "--gson.json");
                parallelepipedsDatabase.save("./src/database/" + timeStamp + "--gson.json");
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        System.exit(0);
    }

    private static String getLastSession() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./src/database/db.txt"));
        String s = br.readLine();
        br.close();
        return s;
    }

    private static void setRectanglesInfo(RectanglesDatabase trianglesDatabase,
                                        int trianglesIndex,
                                        Rectangles rectangles,
                                        int amount) {
        //init
        double rectangleWidth;
        double rectangleLength;

        //create other triangles(random)
        while (trianglesIndex < amount) {
            rectangleWidth = Math.random() * 20 + 1;
            rectangleLength = Math.random() * 20 + 1;
            rectangles.setRectangle(new Rectangle(rectangleLength,rectangleWidth));
            trianglesDatabase.add(new Rectangle(rectangleLength,rectangleWidth));
            trianglesIndex++;
        }
    }

    private static void setParallelepipedsInfo(ParallelepipedsDatabase pyramidsDatabase,
                                               int parallelepipedIndex,
                                               Parallelepipeds parallelepipeds,
                                               int amount) {
        //init
        double width;
        double length;
        double height;

        //create other pyramids(random)
        while (parallelepipedIndex < amount) {
            height = Math.random() * 20 + 1;
            width = Math.random() * 20 + 1;
            length = Math.random() * 20 + 1;

            parallelepipeds.setParallelepiped(new Parallelepiped(length, width, height));
            pyramidsDatabase.add(new Parallelepiped(length, width, height));
            parallelepipedIndex++;
        }
    }

    private static void printAllObjects(ParallelepipedsDatabase parallelepipedsDatabase) {
        for (int i = 0; i < parallelepipedsDatabase.getParallelepipedsList().size(); i++) {
            System.out.println(parallelepipedsDatabase.getParallelepipedsList().get(i));
        }
    }

    private static void printAllObjects(RectanglesDatabase rectanglesDatabase) {
        for (int i = 0; i < rectanglesDatabase.getRectanglesList().size(); i++) {
            System.out.println(rectanglesDatabase.getRectanglesList().get(i));
        }
    }
}

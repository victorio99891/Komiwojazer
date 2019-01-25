import javafx.util.Pair;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {


        int[][] mojeDane = {{0, 99, 24, 88, 64}, {89, 0, 75, 73, 82}, {7, 98, 0, 31, 37}, {11, 7, 39, 0, 15}, {36, 77, 98, 65, 0}};


        int loopSize = 0;
        Map<Integer, ArrayList<Integer>> mapa = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //String fileName = br.readLine();

        String fileName = "dane120";

        String path = "C:\\Users\\Wiktor\\IdeaProjects\\Komwojazer\\" + fileName + ".txt";

        File dane = new File(path);

        br = new BufferedReader(new FileReader(dane));

        loopSize = Integer.valueOf(br.readLine());

        for (int i = 0; i < loopSize; i++) {
            String input = br.readLine();
            String[] array = input.split("\\s");
            ArrayList<Integer> lista = new ArrayList<>();
            for (String element : array) {
                lista.add(Integer.valueOf(element));
            }
            mapa.put(i, lista);
        }

        ArrayList<Pair<Integer, ArrayList<Integer>>> wyniki = new ArrayList<>();
        ArrayList<Integer> indeksy = new ArrayList<>();

        for (int i = 0; i < mapa.size(); i++) {
            int wynik = 0;
            ArrayList<Integer> lista = mapa.get(i);
            //System.out.println("Lista: " + lista);
            for (int j = 0; j < lista.size() - 1; j++) {
                wynik += mojeDane[lista.get(j) - 1][lista.get(j + 1) - 1];
                // System.out.println("Element: " + mojeDane[lista.get(j) - 1][lista.get(j + 1) - 1]);
            }
            wyniki.add(new Pair<>(wynik, mapa.get(i)));
            indeksy.add(wynik);
            // System.out.println("Wynik: " + wynik);
        }


        System.out.println(wyniki);

        System.out.println();
        System.out.println();

        ArrayList<String> outputFile = new ArrayList<>();

        int numbers = 0;
        for (Pair entry : wyniki) {
            numbers++;
            String tmp = numbers + ". Droga: " + entry.getValue() + " długość: " + entry.getKey();
            System.out.println(tmp);
            outputFile.add(tmp);
        }

        String tmp1 = "Ile możliwych dróg: " + numbers;
        System.out.println(tmp1);
        outputFile.add(tmp1);
        System.out.println();

        outputFile.add("\n");

        Pair<Integer, ArrayList<Integer>> shortest = wyniki.stream().min(Comparator.comparing(Pair::getKey)).get();
        String tmp2 = "Najkrótsza: " + shortest.getValue() + " o długości: " + shortest.getKey();
        System.out.println(tmp2);
        outputFile.add(tmp2);

        outputFile.add("\n");

        Pair<Integer, ArrayList<Integer>> longest = wyniki.stream().max(Comparator.comparing(Pair::getKey)).get();
        String tmp3 = "Najdłuższa: " + longest.getValue() + " o długości: " + longest.getKey();
        System.out.println(tmp3);
        outputFile.add(tmp3);

        outputFile.add("\n");


        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("If you want to save file type 1 and ENTER:");
        String saveInput = br.readLine();
        switch (saveInput) {
            case "1":

                File fileOutput = new File("C:\\Users\\Wiktor\\IdeaProjects\\Komwojazer\\wyniki-" + new Timestamp(System.currentTimeMillis()).getTime() + ".txt");
                Path path1 = Paths.get(fileOutput.getPath());

                try (BufferedWriter writer = Files.newBufferedWriter(path1)) {
                    for (String line : outputFile) {
                        writer.write(line + "\n");
                    }
                }
                break;
            default:
                break;
        }


    }
}

package application;

import entity.Products;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        List<Products> list = new ArrayList<>();

        System.out.println("Enter the folder path:");
        String folderPath = sc.nextLine();

        File inPath = new File(folderPath);
        String sourceFolder = inPath.getParent();

        boolean outFile = new File(sourceFolder + "\\out").mkdir();

        String targetFile = sourceFolder + "\\out\\summary.csv";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inPath))) {

            String item = bufferedReader.readLine();
            while (item != null) {
                String[] fields = item.split(",");

                String productName = fields[0];
                double price = Double.parseDouble(fields[1]);
                int quantity = Integer.parseInt(fields[2]);

                list.add(new Products(productName, price, quantity));

                item = bufferedReader.readLine();
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile))) {

                for (Products p : list) {
                    bufferedWriter.write(p.getName() + "," + String.format("%.2f", p.total()));
                    bufferedWriter.newLine();
                }

                System.out.println(targetFile + " CREATED!");

            } catch (IOException e) {
                System.out.println("Error writing file:  " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        sc.close();
    }
}






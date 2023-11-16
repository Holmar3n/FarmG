package org.example;

import com.sun.source.tree.TryTree;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm {

    ArrayList<Animal> AnimalList = new ArrayList<>();
    ArrayList<Crop> CropList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    Animal animal;

    public Farm() {
        if(file.exists() || file2.exists()){
            load();
        }
        else {
            AnimalList.add(new Animal("Håkan", "Cow"));
            AnimalList.add(new Animal("Moa", "Goat"));
            AnimalList.add(new Animal("Kiddi", "Goat"));
            CropList.add(new Crop("Wheat", "Veg", 55));
            CropList.add(new Crop("Potato", "Veg", 32));
            CropList.add(new Crop("Egg", "Meat", 23));
        }
    }
    public void MainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome To Holm's Farm!");
            System.out.println("1. View Crops");
            System.out.println("2. Remove Crop");
            System.out.println("3. Add Crop");
            System.out.println("4. View Animals");
            System.out.println("5. Add Animal");
            System.out.println("6. Feed Animal");
            System.out.println("7. Remove Animal");
            System.out.println("8. Save");
            System.out.println("Q. Quit");
            System.out.println("========================");
            System.out.print("Choose A Number: ");
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("q")) {
                running = false;
            }
            switch (input) {
                case "1":
                    ViewCrops();
                    break;
                case "2":
                    RemoveCrop();
                    break;
                case "3":
                    AddCrop();
                    break;
                case "4":
                    ViewAnimals();
                    break;
                case "5":
                    AddAnimal();
                    break;
                case "6":
                    FeedAnimal();
                    break;
                case "7":
                    RemoveAnimal();
                    break;
                case "8":
                    Save();
                    break;
                default:

                    break;
            }
        }
    }

    public void load() {
        try {

            Scanner ReadScanner = new Scanner(file);
            while (ReadScanner.hasNextLine()) {
                String csv = ReadScanner.nextLine();
                String[] variables2 = csv.split(",");
                int id = Integer.parseInt(variables2[0]);
                String name = variables2[1];
                String species = variables2[2];
                AnimalList.add(new Animal(id, name, species));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Scanner ReadScanner = new Scanner(file2);
            while (ReadScanner.hasNextLine()) {
                String csv = ReadScanner.nextLine();
                String[] variables = csv.split(",");
                int id = Integer.parseInt(variables[0]);
                String name = variables[1];
                String croptype = variables[2];
                int quantity = Integer.parseInt(variables[3]);
                CropList.add(new Crop(id, name, croptype, quantity));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    File folder = new File("folder");
    File file = new File("folder/Animals.txt");
    File file2 = new File("folder/Crops.txt");

    private void Save() {
        folder.mkdir();
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Animal n: AnimalList) {
                bw.write(n.GetCsv());
                bw.newLine();

            }
            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter fw = new FileWriter(file2);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Crop n: CropList) {
                bw.write(n.GetCsv());
                bw.newLine();

            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void FeedAnimal() {
        ViewAnimals();
        System.out.println("What Animal Would You Like To Feed?");
        System.out.print("Type Animal ID: ");
        int animalId = Integer.parseInt(scanner.nextLine());
        ViewCrops();
        System.out.println("What Crop Would You Like To Feed With?: ");
        System.out.print("Type Crop ID: ");
        int cropId = Integer.parseInt(scanner.nextLine());
        for (Animal n: AnimalList) {
            if (animalId == n.getId()) {
                for (Crop c : CropList) {
                    if (cropId == c.getId()) {
                        n.Feed(c);
                        break;
                    }
                }
            }
        }
    }
    private void RemoveAnimal() {
        for (Animal n: AnimalList) {
            System.out.println(n.GetDescription());
        }

        try {
            System.out.println("What Animal Would You Like To Remove?");
            System.out.print("Type Animal ID: ");
            int input = Integer.parseInt(scanner.nextLine());
            boolean tf = false;
            for (Animal n : AnimalList) {
                if (input == n.getId()) {
                    AnimalList.remove(n);
                    tf = true;
                    break;
                }
            } if (tf == false) {
                System.out.println("\nAnimal Dosn't Exist...");
            }
        }
        catch(NumberFormatException e) {
            System.out.println("\nNot A Valid Number...");
        }
    }
    private void RemoveCrop() {
        for (Crop n: CropList) {
            System.out.println(n.GetDescription());
        }

        try {
            System.out.println("What Crop Would You Like To Remove?");
            System.out.print("Type Crop ID: ");
            int input = Integer.parseInt(scanner.nextLine());
            boolean tf = false;
            for (Crop n : CropList) {
                if (input == n.getId()) {
                    CropList.remove(n);
                    tf = true;
                    break;
                }
            }
            if (tf == false){
                System.out.println("Crop Does Not Exist!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nYou Can Only Type Numbers...\n");
        }
    }
    private void ViewCrops() {
        System.out.println("");
        System.out.println("= CROPLIST:");
        for (Crop n: CropList) {
            System.out.println("= " + n.GetDescription());;
        }
        System.out.println("");
        System.out.println("");
    }
    private void AddCrop() {
        System.out.println("Add A Crop!");
        System.out.print("Name: ");
        String input = scanner.nextLine();
        for (Crop n: CropList) {
            if (input.toLowerCase().equals(n.name.toLowerCase())) {
                System.out.println("Total Amount Of " + n.name + ": " + n.getQuantity());
                boolean validInput = false;
                while(!validInput) {
                    System.out.println("\nHow much would you like to Add?");
                    try {
                        String input4 = scanner.nextLine();
                        int Intinput4 = Integer.parseInt(input4);
                        if (Intinput4 >= 0) {
                        int newQuantity = n.getQuantity() + Intinput4;
                        n.setQuantity(newQuantity);
                        MainMenu();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Not A Valid Quantity!");
                        AddCrop();
                    }
                }
            }

            }

        System.out.print("CropType: ");
        String input2 = scanner.nextLine();
        System.out.print("Quantity: ");
        try {
            String input3 = scanner.nextLine();
            int IntInput3 = Integer.parseInt(input3);
            if (IntInput3 > 0) {
                CropList.add(new Crop(input, input2, IntInput3));
                System.out.println("");
                MainMenu();
            }
            else {
                System.out.println("");
                System.out.println("You Can Not Enter Negative Numbers...\n");
                MainMenu();
            }
        } catch (NumberFormatException e){
            System.out.println("\nPlease Write A Number!\n");
            AddCrop();
        }
    }
    private void ViewAnimals() {
        System.out.println("= AnimalList");
        for (Animal n: AnimalList) {
            System.out.println("= " + n.GetDescription());
        }
        System.out.println("\n");
    }
    private void AddAnimal() {
        System.out.println("\nAdd Animal!");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Species: ");
        String species = scanner.nextLine();
        for (Animal n: AnimalList) {
            if (name.equals(n.name) && species.equals(n.species)) {
                System.out.println("Animal Already Exists!");
                System.out.println("You Can Not Add A Copy Of Another Animal\n");

            }
        }
        AnimalList.add(new Animal(name, species));
        MainMenu();
    }
}

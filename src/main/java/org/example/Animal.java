package org.example;

import java.util.Scanner;

public class Animal extends Entity{
    Scanner scanner = new Scanner(System.in);
    public String species;
    private static int nextAnimalId = 1;

    public Animal(int id, String name, String species){
        super(id, name);
        this.species = species;
        if (id >= nextAnimalId) {
            nextAnimalId = id + 1;
        }
    }

    public Animal(String name, String species){
        super(nextAnimalId, name);
        nextAnimalId++;
        this.species = species;
    }

    public String GetCsv(){
        return getId() + "," + name + "," + species;
    }
    @Override
    public String GetDescription() {
        return "ID: " + getId() + "   NAME: " + name + "   SPECIES: " + species;
    }

    public void Feed(Crop crop) {
        System.out.println("From 1 To " + crop.getQuantity());
        System.out.print("How Much Crops Would You Like To Feed " + name + " With?: ");
        try {
            int amount = Integer.parseInt(scanner.nextLine());
            if (amount <= crop.getQuantity()){
                int quantity = crop.getQuantity();
                quantity -= amount;
                crop.setQuantity(quantity);
            } else {
                System.out.println("Not Enough Crops!");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Not A Valid Number!");
        }
    }
    // heheh

    public static int getNextAnimalId() {
        return nextAnimalId;
    }

    public static void setNextAnimalId(int nextAnimalId) {
        Animal.nextAnimalId = nextAnimalId;
    }
}
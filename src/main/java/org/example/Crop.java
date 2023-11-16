package org.example;

public class Crop extends Entity {
    private String cropType;
    private int quantity;
    private static int nextCropId = 1;

    public Crop (int id, String name, String cropType, int quantity) {
        super(id, name);
        if (id >= nextCropId) {
            nextCropId = id + 1;
        }
        this.cropType = cropType;
        this.quantity = quantity;
    }
    public Crop (String name, String cropType, int quantity) {
        super(nextCropId, name);
        nextCropId ++;
        this.cropType = cropType;
        this.quantity = quantity;
    }

    public String GetCsv() {
        return getId() + "," + name + "," + cropType + "," + quantity;
    }
    @Override
    public String GetDescription(){
        return ("ID: " + getId() + "   NAME: " + name + "   CROPTYPE: " + getCropType() + "   QUANTITY: " + getQuantity());
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

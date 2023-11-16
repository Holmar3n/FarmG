package org.example;

public class Entity {
    private int id;
    protected String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }



    public String GetDescription(){
        return "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

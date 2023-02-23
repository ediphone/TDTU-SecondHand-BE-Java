package com.example.TDTUSecondhandShop.models;

public class Type {
    private String typeID;
    private String name;

    public Type(String typeID, String name) {
        this.typeID = typeID;
        this.name = name;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeID=" + typeID +
                ", name='" + name + '\'' +
                '}';
    }
}

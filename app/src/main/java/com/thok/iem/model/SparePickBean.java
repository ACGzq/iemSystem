package com.thok.iem.model;

public class SparePickBean {
    private String id;
    private String componentName;
    private String  modelType;
    private int number;

    public String getEqiopmentName() {

        return componentName;
    }

    public void setEqiopmentName(String eqiopmentName) {
        this.componentName = eqiopmentName;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

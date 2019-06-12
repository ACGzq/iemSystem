package com.thok.iem.model;

public class Component {
    public static final String KEY_WORD_COMPONENT_NAME = "KEY_WORD_COMPONENT_NAME";
    public static final String KEY_WORD_MODEL_TYPE = "KEY_WORD_MODEL_TYPE";
    public static final String KEY_WORD_AMOUNT = "KEY_WORD_AMOUNT";
    private String componentName;
    private String  modelType;
    private int amount;

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

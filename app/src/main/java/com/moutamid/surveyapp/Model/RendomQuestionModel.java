package com.moutamid.surveyapp.Model;
import java.util.List;


import java.util.List;

public class RendomQuestionModel {
    private String Fragetext;
    private List<String> options;
    private int selectedOptionIndex = -1;

    public RendomQuestionModel(String Fragetext, List<String> options, int selectedOptionIndex) {
        this.Fragetext = Fragetext;
        this.options = options;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getFragetext() {
        return Fragetext;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    // Add this setter method
    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }
}

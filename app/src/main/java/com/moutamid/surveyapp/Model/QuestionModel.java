package com.moutamid.surveyapp.Model;
import java.util.List;

public class QuestionModel {
    private String Fragetext;
    private List<String> options;
    private int selectedOptionIndex = -1; // Default value indicating no option selected
    private String userInput;

    public QuestionModel(String Fragetext, List<String> options) {
        this.Fragetext = Fragetext;
        this.options = options;
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

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}

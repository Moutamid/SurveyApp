package com.moutamid.surveyapp.Model;
import java.util.List;


import java.util.List;

public class QuestionModel {
    private String questionText;
    private List<String> options;
    private int selectedOptionIndex = -1;

    public QuestionModel(String questionText, List<String> options, int selectedOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
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

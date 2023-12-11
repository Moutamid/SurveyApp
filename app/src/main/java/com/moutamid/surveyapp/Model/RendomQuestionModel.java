package com.moutamid.surveyapp.Model;
import java.util.List;

public class RendomQuestionModel {
    private String questionText;
    private List<String> options;
    private int selectedOptionIndex;

    public RendomQuestionModel(String questionText, List<String> options, int selectedOptionIndex) {
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
}

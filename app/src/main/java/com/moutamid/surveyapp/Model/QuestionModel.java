package com.moutamid.surveyapp.Model;
import java.util.List;

public class QuestionModel {
    private String questionText;
    private List<String> options;
    private int selectedOptionIndex = -1; // Default value indicating no option selected
    private String userInput;

    public QuestionModel(String questionText, List<String> options) {
        this.questionText = questionText;
        this.options = options;
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

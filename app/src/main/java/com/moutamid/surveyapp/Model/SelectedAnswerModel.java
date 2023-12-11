package com.moutamid.surveyapp.Model;

public class SelectedAnswerModel {
    private int questionPosition;
    private int selectedOptionIndex;

    public SelectedAnswerModel(int questionPosition, int selectedOptionIndex) {
        this.questionPosition = questionPosition;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public int getQuestionPosition() {
        return questionPosition;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }
}

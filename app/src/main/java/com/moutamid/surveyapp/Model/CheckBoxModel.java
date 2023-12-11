package com.moutamid.surveyapp.Model;

public class CheckBoxModel {
    private int questionPosition;
    private int optionIndex;

    public CheckBoxModel(int questionPosition, int optionIndex) {
        this.questionPosition = questionPosition;
        this.optionIndex = optionIndex;
    }

    public int getQuestionPosition() {
        return questionPosition;
    }

    public int getOptionIndex() {
        return optionIndex;
    }
}

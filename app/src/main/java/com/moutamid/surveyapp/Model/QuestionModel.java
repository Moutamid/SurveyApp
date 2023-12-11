package com.moutamid.surveyapp.Model;


import java.util.List;

public class QuestionModel {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuestionModel(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

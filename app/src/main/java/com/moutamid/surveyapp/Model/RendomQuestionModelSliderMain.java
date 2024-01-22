package com.moutamid.surveyapp.Model;

import java.util.List;

public class RendomQuestionModelSliderMain {

    private String fragetext;
    private List<String> options;
    private int selectedOptionIndex;

    public RendomQuestionModelSliderMain(String fragetext, List<String> options, int selectedOptionIndex) {
        this.fragetext = fragetext;
        this.options = options;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public String getFragetext() {
        return fragetext;
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
}

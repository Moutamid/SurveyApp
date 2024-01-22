package com.moutamid.surveyapp.Model;

import java.util.List;

public class RendomQuestionModelSlider extends RendomQuestionModelSliderMain {

    private float sliderValue=-1.0f;

    public RendomQuestionModelSlider(String fragetext, List<String> options, int selectedOptionIndex, float sliderValue) {
        super(fragetext, options, selectedOptionIndex);
        this.sliderValue = sliderValue;
    }

    public float getSliderValue() {
        return sliderValue;
    }

    public void setSliderValue(float sliderValue) {
        this.sliderValue = sliderValue;
    }
}

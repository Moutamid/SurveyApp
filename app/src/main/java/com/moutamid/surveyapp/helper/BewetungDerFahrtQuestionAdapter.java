package com.moutamid.surveyapp.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.moutamid.surveyapp.Model.RendomQuestionModelSlider;
import com.moutamid.surveyapp.R;

import java.util.List;

public class BewetungDerFahrtQuestionAdapter extends PagerAdapter {

    private List<RendomQuestionModelSlider> questions;
    private Context context;
    public static int progress_main = 0;
    public static String question_main;

    public BewetungDerFahrtQuestionAdapter(List<RendomQuestionModelSlider> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_question_slider, container, false);

        TextView questionText = view.findViewById(R.id.questionText);
        CustomSeekBar answerSeekBar = view.findViewById(R.id.answerSeekBar);

        RendomQuestionModelSlider question = questions.get(position);
        question_main = question.getFragetext();
        questionText.setText(question.getFragetext());
        answerSeekBar.setProgress(question.getSelectedOptionIndex());

        answerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                question.setSelectedOptionIndex(progress);
                progress_main = progress;
                question.setSliderValue(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    public List<RendomQuestionModelSlider> getQuestions() {
        return questions;
    }
    public RendomQuestionModelSlider getItem(int position) {
        return questions.get(position);
    }
}

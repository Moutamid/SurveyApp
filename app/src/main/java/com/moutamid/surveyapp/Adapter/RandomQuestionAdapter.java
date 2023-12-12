package com.moutamid.surveyapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.surveyapp.Model.RendomQuestionModel;
import com.moutamid.surveyapp.R;

import java.util.List;

public class RandomQuestionAdapter extends RecyclerView.Adapter<RandomQuestionAdapter.ViewHolder> {
    private List<RendomQuestionModel> questions;

    public RandomQuestionAdapter(List<RendomQuestionModel> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RendomQuestionModel question = questions.get(position);

        holder.questionText.setText(question.getQuestionText());

        // Set options using radio buttons
        holder.optionsRadioGroup.removeAllViews();
        for (int i = 0; i < question.getOptions().size(); i++) {
            RadioButton radioButton = new RadioButton(holder.itemView.getContext());
            radioButton.setText(question.getOptions().get(i));
            radioButton.setId(i);
            holder.optionsRadioGroup.addView(radioButton);

            if (question.getSelectedOptionIndex() == i) {
                radioButton.setChecked(true);
            }
        }

        holder.optionsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                question.setSelectedOptionIndex(checkedId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public List<RendomQuestionModel> getQuestions() {
        return questions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        RadioGroup optionsRadioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            optionsRadioGroup = itemView.findViewById(R.id.optionsRadioGroup);
        }
    }
}

package com.moutamid.surveyapp.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.surveyapp.Model.QuestionModel;
import com.moutamid.surveyapp.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<QuestionModel> questionList;

    public QuestionAdapter(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionModel question = questionList.get(position);

        holder.questionTextView.setText(question.getQuestionText());

        if (question.getOptions() != null && !question.getOptions().isEmpty()) {
            holder.radioGroup.setVisibility(View.VISIBLE);
            holder.editText.setVisibility(View.GONE);

            holder.radioGroup.removeAllViews();

            for (int positionOption = 0; positionOption < question.getOptions().size(); positionOption++) {
                RadioButton radioButton = new RadioButton(holder.itemView.getContext());
                radioButton.setText(question.getOptions().get(positionOption));
                radioButton.setId(positionOption);

                holder.radioGroup.addView(radioButton);
            }

            holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                // Save the selected option
                question.setSelectedOptionIndex(checkedId);
            });

        } else {
            holder.radioGroup.setVisibility(View.GONE);
            holder.editText.setVisibility(View.VISIBLE);

            holder.editText.setText(question.getUserInput());
            holder.editText.setOnFocusChangeListener((v, hasFocus) -> {
                // Save user input when focus changes
                if (!hasFocus) {
                    question.setUserInput(holder.editText.getText().toString());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView questionTextView;
        private RadioGroup radioGroup;
        private EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.textQuestion);
            radioGroup = itemView.findViewById(R.id.radioGroupOptions);
            editText = itemView.findViewById(R.id.editTextAnswer);
        }
    }
}

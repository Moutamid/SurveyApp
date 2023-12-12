//package com.moutamid.surveyapp.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.moutamid.surveyapp.Model.QuestionModel;
//import com.moutamid.surveyapp.Model.SelectedAnswerModel;
//import com.moutamid.surveyapp.R;
//
//import java.util.List;
//
//public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
//
//    private final List<QuestionModel> questions;
//    private final List<SelectedAnswerModel> selectedAnswers;
//
//    public QuestionAdapter(List<QuestionModel> questions, List<SelectedAnswerModel> selectedAnswers) {
//        this.questions = questions;
//        this.selectedAnswers = selectedAnswers;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        QuestionModel questionModel = questions.get(position);
//        holder.questionText.setText(questionModel.getQuestion());
//        holder.radioButtonOption1.setText(questionModel.getOptions().get(0));
//        holder.radioButtonOption2.setText(questionModel.getOptions().get(1));
//        holder.radioButtonOption3.setText(questionModel.getOptions().get(2));
//        holder.radioButtonOption4.setText(questionModel.getOptions().get(3));
//        holder.radioButtonOption5.setText(questionModel.getOptions().get(4));
//        holder.radioButtonOption6.setText(questionModel.getOptions().get(5));
//
//        // Set up radio buttons and their listeners here
//        holder.radioButtonOption1.setTag(position);
//        holder.radioButtonOption2.setTag(position);
//        holder.radioButtonOption3.setTag(position);
//        holder.radioButtonOption4.setTag(position);
//        holder.radioButtonOption5.setTag(position);
//        holder.radioButtonOption6.setTag(position);
//
//        // Check the corresponding radio button based on selected answers
////        checkSelectedAnswers(holder, position);
//
//        // Check the corresponding radio button based on selected answers
//        checkSelectedAnswers(holder, position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return questions.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView questionText;
//        RadioGroup radioGroupOptions;
//        RadioButton radioButtonOption1, radioButtonOption2, radioButtonOption3, radioButtonOption4, radioButtonOption5, radioButtonOption6;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            questionText = itemView.findViewById(R.id.questionText);
//            radioGroupOptions = itemView.findViewById(R.id.radioGroupOptions);
//            radioButtonOption1 = itemView.findViewById(R.id.radioButtonOption1);
//            radioButtonOption2 = itemView.findViewById(R.id.radioButtonOption2);
//            radioButtonOption3 = itemView.findViewById(R.id.radioButtonOption3);
//            radioButtonOption4 = itemView.findViewById(R.id.radioButtonOption4);
//            radioButtonOption5 = itemView.findViewById(R.id.radioButtonOption5);
//            radioButtonOption6 = itemView.findViewById(R.id.radioButtonOption6);
//        }
//    }
//
//    private void checkSelectedAnswers(ViewHolder holder, int position) {
//        for (SelectedAnswerModel selectedAnswer : selectedAnswers) {
//            if (selectedAnswer.getQuestionPosition() == position) {
//                int selectedOptionIndex = selectedAnswer.getSelectedOptionIndex();
//                switch (selectedOptionIndex) {
//                    case 0:
//                        holder.radioButtonOption1.setChecked(true);
//                        break;
//                    case 1:
//                        holder.radioButtonOption2.setChecked(true);
//                        break;
//                    case 2:
//                        holder.radioButtonOption3.setChecked(true);
//                        break;
//                    case 3:
//                        holder.radioButtonOption4.setChecked(true);
//                        break;
//                    case 4:
//                        holder.radioButtonOption5.setChecked(true);
//                        break;
//                    case 5:
//                        holder.radioButtonOption6.setChecked(true);
//                        break;
//                }
//            }
//        }
//    }
//
//    public QuestionModel getQuestionAtPosition(int position) {
//        return questions.get(position);
//    }
//}

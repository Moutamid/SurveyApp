package com.moutamid.surveyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.moutamid.surveyapp.Adapter.AbschlussfragebogenQuestionAdapter;
import com.moutamid.surveyapp.Adapter.QuestionAdapter;
import com.moutamid.surveyapp.Model.QuestionModel;
import com.moutamid.surveyapp.Model.SelectedAnswerModel;
import com.moutamid.surveyapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbschlussfragebogenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AbschlussfragebogenQuestionAdapter adapter;
    private List<SelectedAnswerModel> selectedAnswers = new ArrayList<>();
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abschlussfragebogen);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        List<QuestionModel> questionList = generateQuestionList();
        adapter = new AbschlussfragebogenQuestionAdapter(questionList, selectedAnswers); // Pass the selectedAnswers list
        recyclerView.setAdapter(adapter);

       List<QuestionModel> questionList1 = generateSecondQuestionList();
        adapter = new AbschlussfragebogenQuestionAdapter(questionList1, selectedAnswers); // Pass the selectedAnswers list
        recyclerView1.setAdapter(adapter);

       List<QuestionModel> questionList2 = generateThirdQuestionList();
        adapter = new AbschlussfragebogenQuestionAdapter(questionList2, selectedAnswers); // Pass the selectedAnswers list
        recyclerView2.setAdapter(adapter);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnswers();
            }
        });
    }
    private List<QuestionModel> generateQuestionList() {
        List<QuestionModel> questions = new ArrayList<>();
        // Add your questions to the list with options and correct answer index

        List<String> options = new ArrayList<>();

        options.add("Sehr gering");
        options.add("gering");
        options.add("mittel");
        options.add("hoch");
        options.add("Sehr hoch");

        String[] questionTexts = {
                "Wie gut konnten Sie das Verhalten des Systems in den eben erlebten Situationen vorhersagen?",
                "Wie sehr konnten Sie sich in den eben erlebten Situationen darauf verlassen, dass das System funktioniert?",
                "Wie hoch ist Ihr Glaube daran, dass das System mit Situationen dieser Art jederzeit umgehen kann?",
                "Wie hoch ist Ihr Vertrauen in das System nach der eben erlebten Fahrt?",
        };


        for (String questionText : questionTexts) {
            questions.add(new QuestionModel(questionText, options, 0));
        }
        // Add other questions
        return questions;
    }
    private List<QuestionModel> generateSecondQuestionList() {
        List<QuestionModel> questions = new ArrayList<>();
        // Add your questions to the list with options and correct answer index

        List<String> options = new ArrayList<>();

        options.add("Stimme überhaupt nicht zu");
        options.add("Stimme nicht zu");
        options.add("Weder noch");
        options.add("Stimme zu");
        options.add("Stimme voll und ganz zu");
        String[] questionTexts = {
                "Dass das System verschiedene Ausprägungen hat, erachte ich als sinnvoll.",
                "Die Spreizung der Varianten erachte ich als zu groß.",
                "Die Spreizung der Varianten erachte ich als zu klein."
        };


        for (String questionText : questionTexts) {
            questions.add(new QuestionModel(questionText, options, 0));
        }
        // Add other questions
        return questions;
    }
    private List<QuestionModel> generateThirdQuestionList() {
        List<QuestionModel> questions = new ArrayList<>();

        List<String> options = new ArrayList<>();
        options.add("Stimme überhaupt nicht zu");
        options.add("Stimme nicht zu");
        options.add("Weder noch");
        options.add("Stimme zu");
        options.add("Stimme voll und ganz zu");

        String[] questionTexts = {
                "Wie bewerten Sie das System insgesamt?",
                "Wie weit ist das System von Ihrem idealen Spurhalteassistenten entfernt?",
                "Wie wahrscheinlich ist es, dass Sie ein solches System in Zukunft nutzen würden?",
                "Nach dem Versuch verspüre ich Übelkeit/Unwohlsein.",
                "Wie bewerten Sie das Fahrerlebnis insgesamt?"
        };

        for (String questionText : questionTexts) {
            questions.add(new QuestionModel(questionText, options, 0));
        }

        return questions;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        int questionPosition = (int) view.getTag(); // Retrieve the question position from the tag

        // Handle radio button click here
        if (view.getId() == R.id.radioButtonOption1 && checked) {
            selectedAnswers.add(new SelectedAnswerModel(questionPosition, 0));
        } else if (view.getId() == R.id.radioButtonOption2 && checked) {
            selectedAnswers.add(new SelectedAnswerModel(questionPosition, 1));
        } else if (view.getId() == R.id.radioButtonOption3 && checked) {
            selectedAnswers.add(new SelectedAnswerModel(questionPosition, 2));
        } else if (view.getId() == R.id.radioButtonOption4 && checked) {
            selectedAnswers.add(new SelectedAnswerModel(questionPosition, 3));
        } else if (view.getId() == R.id.radioButtonOption5 && checked) {
            selectedAnswers.add(new SelectedAnswerModel(questionPosition, 4));
        }
    }

    private void submitAnswers() {
        // Implement code to submit answers to Firebase
        // For simplicity, let's assume answers are stored in a Map<String, String>
        Map<String, String> answersMap = new HashMap<>();

        // Iterate through the selected answers and add them to the map
        for (SelectedAnswerModel selectedAnswer : selectedAnswers) {
            int questionPosition = selectedAnswer.getQuestionPosition();
            int selectedOptionIndex = selectedAnswer.getSelectedOptionIndex();

            // You can access the actual question and its options from the adapter
            QuestionModel questionModel = adapter.getQuestionAtPosition(questionPosition);
            String questionText = questionModel.getQuestion();
            String selectedOption = questionModel.getOptions().get(selectedOptionIndex);

            // Add the question and its selected option to the map
            answersMap.put(questionText, selectedOption);
        }

        Log.d("SurveyApp", "answers" + answersMap + "");

        // Upload the answers to Firebase
//        databaseReference.child("UserResponses").setValue(answersMap);
    }


}
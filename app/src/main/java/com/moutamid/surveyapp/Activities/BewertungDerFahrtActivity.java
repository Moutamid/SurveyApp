package com.moutamid.surveyapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.fxn.stash.Stash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutamid.surveyapp.Adapter.AbschlussfragebogenQuestionAdapter;
import com.moutamid.surveyapp.Adapter.BewetungDerFahrtQuestionAdapter;
import com.moutamid.surveyapp.Model.RendomQuestionModel;
import com.moutamid.surveyapp.Model.SelectedAnswerModel;
import com.moutamid.surveyapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BewertungDerFahrtActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BewetungDerFahrtQuestionAdapter adapter;
    private List<SelectedAnswerModel> selectedAnswers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung_der_fahrt);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<RendomQuestionModel> questionList = generateDynamicQuestionList();
        adapter = new BewetungDerFahrtQuestionAdapter(questionList); // Pass the selectedAnswers list
        recyclerView.setAdapter(adapter);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClicked", "Submit button clicked");
                if (validateAllQuestions()) {
                  finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please select all questions", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private List<RendomQuestionModel> generateDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        // Question 1
        List<String> options1 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModel("Wie hat das System Ihren subjektiv empfundenen Fahrkomfort verändert?", options1, 7));

        // Question 2
        List<String> options2 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModel("Wie hat das System insgesamt/ bei Gegenverkehr Ihre subjektiv empfundene Fahrsicherheit verändert?", options2, 7));

        // Question 3
        List<String> options3 = Arrays.asList(
                "ungenügend",
                "ausreichend",
                "befriedigend",
                "gut",
                "sehr gut"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie die Unterstützung beim Einhalten der Fahrspur in der Kurve/ während der Geradeausfahrt?", options3, 7));

        // Question 4
        List<String> options4 = Arrays.asList(
                "zu schwach",
                "schwach",
                "angemessen",
                "stark",
                "zu stark"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie die Intensität der Lenkeingriffe?", options4, 7));

        // Question 5
        List<String> options5 = Arrays.asList(
                "ungenügend",
                "ausreichend",
                "befriedigend",
                "gut",
                "sehr gut"
        );
        questions.add(new RendomQuestionModel("Wie gut konnten Sie die Lenkeingriffe des Systems vorhersehen?", options5, 7));

        // Question 6
        List<String> options6 = Arrays.asList(
                "sehr klein",
                "klein",
                "weder noch",
                "groß",
                "sehr groß"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie Ihren manuellen Korrekturaufwand nach den Eingriffen des Systems?", options6, 7));

        // Question 7
        List<String> options7 = Arrays.asList(
                "sehr störend",
                "störend",
                "neutral",
                "hilfreich",
                "sehr hilfreich"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie die Unterstützung bei der Fahrzeugführung insgesamt?", options7, 7));

        // Question 8
        List<String> options8 = Arrays.asList(
                "viel schlechter",
                "schlechter",
                "gleich",
                "besser",
                "viel besser"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie das Fahrzeugverhalten im Vergleich zum vorherigen System?", options8, 7));

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



    private boolean validateAllQuestions() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SurveyResponses").child("BewertungDerFahrt").child(Stash.getString("device_id"));

        for (RendomQuestionModel question : adapter.getQuestions()) {
            if (question.getSelectedOptionIndex() == 7) {
                Log.d("Validation", "Question not answered: " + question.getQuestionText());
                return false;
            } else {
                Log.d("Validation", "Question answered - Selected option index: " + question.getSelectedOptionIndex());

                // Create a map to represent the data you want to store in Realtime Database
                Map<String, Object> questionData = new HashMap<>();
                questionData.put("questionText", question.getQuestionText());
                questionData.put("selectedOptionText", question.getOptions().get(question.getSelectedOptionIndex()));

                // Add the data to Realtime Database
                databaseReference.push().setValue(questionData)
                        .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                        .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
            }
        }
        // All questions are answered
        return true;
    }


}






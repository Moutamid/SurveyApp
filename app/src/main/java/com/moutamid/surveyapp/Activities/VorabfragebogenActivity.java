package com.moutamid.surveyapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.surveyapp.Adapter.QuestionAdapter;
import com.moutamid.surveyapp.Adapter.RandomQuestionAdapter;
import com.moutamid.surveyapp.Model.QuestionModel;
import com.moutamid.surveyapp.Model.RendomQuestionModel;
import com.moutamid.surveyapp.Model.SelectedAnswerModel;
import com.moutamid.surveyapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VorabfragebogenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<SelectedAnswerModel> selectedAnswers = new ArrayList<>();
    private List<RendomQuestionModel> rendomQuestionModels;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RandomQuestionAdapter randomQuestionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<QuestionModel> questionList = generateQuestionList();
        adapter = new QuestionAdapter(questionList, selectedAnswers); // Pass the selectedAnswers list
        recyclerView.setAdapter(adapter);

        //Dynamic
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        rendomQuestionModels = generateDynamicQuestionList();
        randomQuestionAdapter = new RandomQuestionAdapter(rendomQuestionModels);
        recyclerView1.setAdapter(randomQuestionAdapter);

        // 3rd recyclerView

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        rendomQuestionModels = generateSecondDynamicQuestionList();
        randomQuestionAdapter = new RandomQuestionAdapter(rendomQuestionModels);
        recyclerView2.setAdapter(randomQuestionAdapter);
// 4th recyclerview
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        rendomQuestionModels = generateThirdDynamicQuestionList();
        randomQuestionAdapter = new RandomQuestionAdapter(rendomQuestionModels);
        recyclerView3.setAdapter(randomQuestionAdapter);
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
        options.add("Stimmt gar nicht");
        options.add("Stimmt weit-gehend nicht");
        options.add("Stimmt eher nicht");
        options.add("Stimmt eher");
        options.add("Stimmt weitgehend");
        options.add("Stimmt völlig");

        String[] questionTexts = {
                "Das Fahren auf kurvigen Bergstrecken macht mir am meisten Spaß.",
                "Ich fahre am liebsten auf geraden, komfortablen Autobahnen.",
                "Ich beschleunige mein Auto gern richtig kräftig.",
                "Ich beschleunige mein Auto eher verhalten.",
                "Ich fahre in der Regel immer sehr schnell und sportlich.",
                "Ich fahre eher ziemlich langsam und zurückhaltend.",
                "Ich versuche möglichstspritsparend zu fahren.",
                "Ich lasse mich durch den Spritverbrauch in meiner Fahrweise nicht beeinflussen.",
                "Ich neige dazu, Kurven zu schneiden.",
                "Das Fahren auf kurvigen Landstraßen macht mir am meisten Spaß.",
                "Ich beschäftige mich gern genauer mit technischen Systemen.",
                "Ich probiere gern die Funktionen neuer technischer Systeme aus.",
                "In erster Linie beschäftige ich mich mit technischen Systemen, weil ich muss.",
                "Wenn ich ein neues technisches System vor mir habe, probiere ich es intensiv aus.",
                "Ich verbringe sehr gern Zeit mit dem Kennenlernen eines neuen technischen Systems.",
                "Es genügt mir, dass ein technisches System funktioniert, mir ist es egal, wie oder warum.",
                "Ich versuche zu verstehen, wie ein technisches System genau funktioniert.",
                "Es genügt mir, die Grundfunktionen eines technischen Systems zu kennen.",
                "Ich versuche, die Möglichkeiten eines technischen Systems vollständig auszunutzen."
        };

        for (String questionText : questionTexts) {
            questions.add(new QuestionModel(questionText, options, 7));
        }
        // Add other questions
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
        } else if (view.getId() == R.id.radioButtonOption6 && checked) {
            selectedAnswers.add(new SelectedAnswerModel(questionPosition, 5));
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

    private List<RendomQuestionModel> generateDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        // Question 1
        List<String> options1 = Arrays.asList(
                "keine Erfahrung vorhanden",
                "Park Assistent",
                "Stauassistent",
                "Abstandsregel-Tempomat (ACC)",
                "Notbremsassistent",
                "Spurhalteassistent",
                "Spurverlassenswarnung",
                "Spurwechselassistent/ Totwinkelassistent"
        );
        questions.add(new RendomQuestionModel("Mit welchen Fahrerassistenzsystemen haben Sie bereits Erfahrung?", options1, 7));

        // Question 2
        List<String> options2 = Arrays.asList("sehr negativ", "eher negativ", "neutral", "eher positiv", "sehr positiv");
        questions.add(new RendomQuestionModel("Wie waren Ihre bisherigen Erfahrungen mit Fahrerassistenzsystemen im Allgemeinen?", options2, 7));


        return questions;
    }

    private List<RendomQuestionModel> generateSecondDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        // Question 1

        // Question 3
        List<String> options3 = Arrays.asList("seltene Nutzung", "häufige Nutzung", "ständige/tägliche Nutzung");
        questions.add(new RendomQuestionModel("Wie oft haben Sie einen Spurhalteassistenten in der Vergangenheit benutzt?", options3, 7));

        // Question 4
        List<String> options4 = Arrays.asList("sehr negativ", "eher negativ", "neutral", "eher positiv", "sehr positiv");
        questions.add(new RendomQuestionModel("Wie waren Ihre bisherigen Erfahrungen mit einem Spurhalteassistenten?", options4, 7));


        return questions;
    }

    private List<RendomQuestionModel> generateThirdDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        // Question 1
        List<String> options1 = Arrays.asList("Stimme überhaupt nicht zu", "stimme voll und ganz  zu");
        questions.add(new RendomQuestionModel("Ein idealer Spurhalteassistent greift stark in die Fahrzeugführung ein.", options1, 7));

        // Question 3
        List<String> options3 = Arrays.asList("Stimme überhaupt nicht zu", "stimme voll und ganz  zu");
        questions.add(new RendomQuestionModel("Ein idealer Spurhalteassistent trägt für mich zur Freude am Autofahren bei.", options3, 7));

        // Question 4
        List<String> options4 = Arrays.asList("Stimme überhaupt nicht zu", "stimme voll und ganz  zu");
        questions.add(new RendomQuestionModel("Ein idealer Spurhalteassistent trägt für mich zur Erhöhung der Fahrsicherheit bei.", options4, 7));


        return questions;
    }

}

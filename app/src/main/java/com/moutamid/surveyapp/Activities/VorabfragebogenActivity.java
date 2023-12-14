package com.moutamid.surveyapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutamid.surveyapp.Adapter.RandomQuestionAdapter;
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
    private RandomQuestionAdapter adapter1;
    private RandomQuestionAdapter adapter2;
    private RandomQuestionAdapter adapter3;
    private RandomQuestionAdapter adapter4;
    private RandomQuestionAdapter adapter5;
    private List<SelectedAnswerModel> selectedAnswers = new ArrayList<>();
    private List<RendomQuestionModel> RendomQuestionModels;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    EditText editTextKommentare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        editTextKommentare = findViewById(R.id.editTextKommentare);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<RendomQuestionModel> questionList = generateQuestionList();
        adapter1 = new RandomQuestionAdapter(questionList); // Pass the selectedAnswers list
        recyclerView.setAdapter(adapter1);

        //Dynamic
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        RendomQuestionModels = generateDynamicQuestionList();
        adapter2 = new RandomQuestionAdapter(RendomQuestionModels);
        recyclerView1.setAdapter(adapter2);

        // 3rd recyclerView

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        RendomQuestionModels = generateSecondDynamicQuestionList();
        adapter3 = new RandomQuestionAdapter(RendomQuestionModels);
        recyclerView2.setAdapter(adapter3);


// 4th recyclerview
        recyclerView3 = findViewById(R.id.recyclerView3);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        RendomQuestionModels = generateThirdDynamicQuestionList();
        adapter4 = new RandomQuestionAdapter(RendomQuestionModels);
        recyclerView3.setAdapter(adapter4);


        recyclerView4 = findViewById(R.id.recyclerView4);
        recyclerView4.setLayoutManager(new LinearLayoutManager(this));

        RendomQuestionModels = generateForthDynamicQuestionList();
        adapter5 = new RandomQuestionAdapter(RendomQuestionModels);
        recyclerView4.setAdapter(adapter5);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClicked", "Submit button clicked");
                if (validateAllQuestions1()) {
                    Intent intent = new Intent(VorabfragebogenActivity.this, AbschlussfragebogenActivity.class);
                    startActivity(intent);
                finish();}
                else {
                    Toast.makeText(getApplicationContext(), "Bitte wählen Sie alle Fragen aus.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private List<RendomQuestionModel> generateQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();
        // Add your questions to the list with options and correct answer index

        List<String> options = new ArrayList<>();
        options.add("Stimmt gar nicht");
        options.add("Stimmt weit-gehend nicht");
        options.add("Stimmt eher nicht");
        options.add("Stimmt eher");
        options.add("Stimmt weitgehend");
        options.add("Stimmt völlig");

        String[] Fragetexts = {
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

        for (String Fragetext : Fragetexts) {
            questions.add(new RendomQuestionModel(Fragetext, options, 8));
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
        questions.add(new RendomQuestionModel("Mit welchen Fahrerassistenzsystemen haben Sie bereits Erfahrung?", options1, 8));

        // Question 2
        List<String> options2 = Arrays.asList("sehr negativ", "eher negativ", "neutral", "eher positiv", "sehr positiv");
        questions.add(new RendomQuestionModel("Wie waren Ihre bisherigen Erfahrungen mit Fahrerassistenzsystemen im Allgemeinen?", options2, 8));


        return questions;
    }

    private List<RendomQuestionModel> generateSecondDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        // Question 1

        // Question 3
        List<String> options3 = Arrays.asList("seltene Nutzung", "häufige Nutzung", "ständige/tägliche Nutzung");
        questions.add(new RendomQuestionModel("Wie oft haben Sie einen Spurhalteassistenten in der Vergangenheit benutzt?", options3, 8));

        // Question 4
        List<String> options4 = Arrays.asList("sehr negativ", "eher negativ", "neutral", "eher positiv", "sehr positiv");
        questions.add(new RendomQuestionModel("Wie waren Ihre bisherigen Erfahrungen mit einem Spurhalteassistenten?", options4, 8));


        return questions;
    }

    private List<RendomQuestionModel> generateThirdDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        // Question 1
        List<String> options1 = Arrays.asList("Stimme überhaupt nicht zu", "stimme voll und ganz  zu");
        questions.add(new RendomQuestionModel("Ein idealer Spurhalteassistent greift stark in die Fahrzeugführung ein.", options1, 8));

        // Question 3
        List<String> options3 = Arrays.asList("Stimme überhaupt nicht zu", "stimme voll und ganz  zu");
        questions.add(new RendomQuestionModel("Ein idealer Spurhalteassistent trägt für mich zur Freude am Autofahren bei.", options3, 8));

        // Question 4
        List<String> options4 = Arrays.asList("Stimme überhaupt nicht zu", "stimme voll und ganz  zu");
        questions.add(new RendomQuestionModel("Ein idealer Spurhalteassistent trägt für mich zur Erhöhung der Fahrsicherheit bei.", options4, 8));


        return questions;
    }

    private List<RendomQuestionModel> generateForthDynamicQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();
        // Question 1
        List<String> options1 = Arrays.asList("öfter", "3-5x pro Woche", "1-2x pro Woche", "seltener");
        questions.add(new RendomQuestionModel("Wie oft fahren Sie im Schnitt mit dem Auto?", options1, 8));

        // Question 3
        List<String> options3 = Arrays.asList("<5000", "5.000-10.000", "10.000-20.000", ">20.000");
        questions.add(new RendomQuestionModel("Wie schätzen Sie Ihre jährliche Fahrleistung ein? (in km/Jahr)", options3, 8));

        // Question 4
        List<String> options4 = Arrays.asList("Nein", "Ja, im Fahrsimulator", "Ja, im Realfahrzeug");
        questions.add(new RendomQuestionModel("Haben Sie bereits an einem Fahrversuch zum Thema „automatisiertes Fahren“/ „Fahrerassistenzsysteme“ teilgenommen? Wenn ja, wo fand dieser statt?", options4, 8));
        List<String> options5 = Arrays.asList("überhaupt nicht", "sehr schnell");
        questions.add(new RendomQuestionModel("Tendieren Sie dazu, schnell reisekrank zu werden?", options5, 8));
        List<String> options6 = Arrays.asList("weiblich", "männlich", "nicht binär");
        questions.add(new RendomQuestionModel("Geschlecht", options6, 8));


        return questions;
    }

    private boolean validateAllQuestions1() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SurveyResponses")
                .child("Vorabfragebogen");
        String key =databaseReference.push().getKey();

        for (RendomQuestionModel question : adapter1.getQuestions()) {
            if (question.getSelectedOptionIndex() == 8) {
                Log.d("Validation", "Question not answered: " + question.getFragetext());
                return false;
            }

            Map<String, Object> questionData = new HashMap<>();
            questionData.put("Fragetext", question.getFragetext());
            questionData.put("AusgewählterOptionstext", question.getOptions().get(question.getSelectedOptionIndex()));

            // Add the data to Realtime Database
           
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData)
                    .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                    .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        }

        for (RendomQuestionModel question2 : adapter2.getQuestions()) {
            if (question2.getSelectedOptionIndex() == 8) {
                Log.d("Validation", "Question not answered: " + question2.getFragetext());
                return false;
            }

            Map<String, Object> questionData2 = new HashMap<>();
            questionData2.put("Fragetext", question2.getFragetext());
            questionData2.put("AusgewählterOptionstext", question2.getOptions().get(question2.getSelectedOptionIndex()));

            // Add the data to Realtime Database
           
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData2)
                    .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                    .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        }

        for (RendomQuestionModel question3 : adapter3.getQuestions()) {

            if (question3.getSelectedOptionIndex() == 8) {
                Log.d("Validation", "Question not answered: " + question3.getFragetext());
                return false;
            }

            Map<String, Object> questionData3 = new HashMap<>();
            questionData3.put("Fragetext", question3.getFragetext());
            questionData3.put("AusgewählterOptionstext", question3.getOptions().get(question3.getSelectedOptionIndex()));

            // Add the data to Realtime Database
           
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData3)
                    .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                    .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        }

        for (RendomQuestionModel question4 : adapter4.getQuestions()) {
            if (question4.getSelectedOptionIndex() == 8) {
                Log.d("Validation", "Question not answered: " + question4.getFragetext());
                return false;
            }

            Map<String, Object> questionData4 = new HashMap<>();
            questionData4.put("Fragetext", question4.getFragetext());
            questionData4.put("AusgewählterOptionstext", question4.getOptions().get(question4.getSelectedOptionIndex()));

            // Add the data to Realtime Database
           
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData4)
                    .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                    .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        }

        for (RendomQuestionModel question5 : adapter5.getQuestions()) {
            if (question5.getSelectedOptionIndex() == 8) {
                Log.d("Validation", "Question not answered: " + question5.getFragetext());
                return false;
            }

            Map<String, Object> questionData5 = new HashMap<>();
            questionData5.put("Fragetext", question5.getFragetext());
            questionData5.put("AusgewählterOptionstext", question5.getOptions().get(question5.getSelectedOptionIndex()));

            // Add the data to Realtime Database
           
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData5)
                    .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                    .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        }

        Map<String, Object> questionData4 = new HashMap<>();
        questionData4.put("Kommentare", editTextKommentare.getText().toString());

        // Add the data to Realtime Database
       
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData4)
                .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));

        // All questions are answered
        return true;
    }

}

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
import com.moutamid.surveyapp.Adapter.AbschlussfragebogenQuestionAdapter;
import com.moutamid.surveyapp.Model.RendomQuestionModel;
import com.moutamid.surveyapp.Model.SelectedAnswerModel;
import com.moutamid.surveyapp.R;
import com.moutamid.surveyapp.helper.CompleteDialogClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AbschlussfragebogenActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AbschlussfragebogenQuestionAdapter adapter1;
    private AbschlussfragebogenQuestionAdapter adapter2;
    private AbschlussfragebogenQuestionAdapter adapter3;
    private List<SelectedAnswerModel> selectedAnswers = new ArrayList<>();
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;

    EditText editTextKommentare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abschlussfragebogen);

        editTextKommentare = findViewById(R.id.editTextKommentare);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        List<RendomQuestionModel> questionList = generateQuestionList();
        adapter1 = new AbschlussfragebogenQuestionAdapter(questionList); // Pass the selectedAnswers list
        recyclerView.setAdapter(adapter1);

        List<RendomQuestionModel> questionList1 = generateSecondQuestionList();
        adapter2 = new AbschlussfragebogenQuestionAdapter(questionList1); // Pass the selectedAnswers list
        recyclerView1.setAdapter(adapter2);

        List<RendomQuestionModel> questionList2 = generateThirdQuestionList();
        adapter3 = new AbschlussfragebogenQuestionAdapter(questionList2); // Pass the selectedAnswers list
        recyclerView2.setAdapter(adapter3);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonClicked", "Submit button clicked");
                if (validateAllQuestions1()) {
                    Intent intent = new Intent(AbschlussfragebogenActivity.this, MainOptionActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Bitte wählen Sie alle Fragen aus.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private List<RendomQuestionModel> generateQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        List<String> options1 = Arrays.asList(
                "Sehr gering",
                "gering",
                "mittel",
                "hoch",
                "Sehr hoch"
        );
        questions.add(new RendomQuestionModel("Wie gut konnten Sie das Verhalten des Systems in den eben erlebten Situationen vorhersagen?", options1, 7));
        List<String> options2 = Arrays.asList(
                "Sehr gering",
                "gering",
                "mittel",
                "hoch",
                "Sehr hoch"
        );
        questions.add(new RendomQuestionModel("Wie sehr konnten Sie sich in den eben erlebten Situationen darauf verlassen, dass das System funktioniert?", options2, 7));
        List<String> options3 = Arrays.asList(
                "Sehr gering",
                "gering",
                "mittel",
                "hoch",
                "Sehr hoch"
        );
        questions.add(new RendomQuestionModel("Wie hoch ist Ihr Glaube daran, dass das System mit Situationen dieser Art jederzeit umgehen kann?", options3, 7));
        List<String> options4 = Arrays.asList(
                "Sehr gering",
                "gering",
                "mittel",
                "hoch",
                "Sehr hoch"
        );
        questions.add(new RendomQuestionModel("Wie hoch ist Ihr Vertrauen in das System nach der eben erlebten Fahrt?", options4, 7));


//        for (String Fragetext : questions) {
//            questions.add(new RendomQuestionModel(Fragetext, options, 7));
//        }
        // Add other questions
        return questions;
    }

    private List<RendomQuestionModel> generateSecondQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        List<String> options1 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Dass das System verschiedene Ausprägungen hat, erachte ich als sinnvoll.", options1, 7));

        List<String> options2 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Die Spreizung der Varianten erachte ich als zu groß.", options2, 7));

        List<String> options3 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Die Spreizung der Varianten erachte ich als zu klein.", options3, 7));

        // Add other questions if needed

        return questions;
    }

    private List<RendomQuestionModel> generateThirdQuestionList() {
        List<RendomQuestionModel> questions = new ArrayList<>();

        List<String> options1 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie das System insgesamt?", options1, 7));

        List<String> options2 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Wie weit ist das System von Ihrem idealen Spurhalteassistenten entfernt?", options2, 7));

        List<String> options3 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Wie wahrscheinlich ist es, dass Sie ein solches System in Zukunft nutzen würden?", options3, 7));

        List<String> options4 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Nach dem Versuch verspüre ich Übelkeit/Unwohlsein.", options4, 7));

        List<String> options5 = Arrays.asList(
                "Stimme überhaupt nicht zu",
                "Stimme nicht zu",
                "Weder noch",
                "Stimme zu",
                "Stimme voll und ganz zu"
        );
        questions.add(new RendomQuestionModel("Wie bewerten Sie das Fahrerlebnis insgesamt?", options5, 7));

        // Add other questions if needed

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


    private boolean validateAllQuestions1() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("SurveyResponses")
                .child("Abschlussfragebogen");
        String key =  databaseReference.push().getKey();
        for (RendomQuestionModel question : adapter1.getQuestions()) {
            if (question.getSelectedOptionIndex() == 7) {
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
            if (question2.getSelectedOptionIndex() == 7) {
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
            if (question3.getSelectedOptionIndex() == 7) {
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

        Map<String, Object> questionData4 = new HashMap<>();
        questionData4.put("Kommentare", editTextKommentare.getText().toString());

        // Add the data to Realtime Database
       
            databaseReference.child(Stash.getString("device_id") + key + "___" +Stash.getString("name")).push().setValue(questionData4)
                .addOnSuccessListener(aVoid -> Log.d("RealtimeDatabase", "Data added successfully"))
                .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        List<RendomQuestionModel> allQuestions = new ArrayList<>();

        // Merge questions from all adapters
        allQuestions.addAll(adapter1.getQuestions());
        allQuestions.addAll(adapter2.getQuestions());
        allQuestions.addAll(adapter3.getQuestions());

        saveDataToCSV(allQuestions);

        return true;
    }


    private void saveDataToCSV(List<RendomQuestionModel> questions) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String name = Stash.getString("name");
        String filename = "survey_data_" +name+ ".csv"; // Append timestamp to the file name
        String title = "\nAbschlussfragebogen\n\n";
        String csvHeader = "Question Number,Fragetext,AusgewählterOptionstext\n";

        File csvFile = new File(getExternalFilesDir(null), filename);

        try {
            FileWriter writer = new FileWriter(csvFile, true); // Open the file in append mode
            writer.append(title);
            writer.append(csvHeader);

            for (int i = 0; i < questions.size(); i++) {
                RendomQuestionModel question = questions.get(i);
                String questionText = question.getFragetext().replaceAll(",", ""); // Escape double quotes
                writer.append(String.valueOf(i + 1)).append(",\"") // Append question number and start quote
                        .append(questionText).append("\",\"") // Append question text and start quote
                        .append(question.getOptions().get(question.getSelectedOptionIndex())).append("\"") // Append selected option
                        .append("\n");
            }

            // Add comment to CSV
            writer.append(String.valueOf(questions.size() + 1)).append(",\"Kommentare\",\"")
                    .append(editTextKommentare.getText().toString()).append("\"\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            Log.e("CSV", "Error writing CSV file: " + e.getMessage());
        }
    }

}
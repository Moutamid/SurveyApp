package com.moutamid.surveyapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.fxn.stash.Stash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutamid.surveyapp.Model.RendomQuestionModel;
import com.moutamid.surveyapp.Model.RendomQuestionModelSlider;
import com.moutamid.surveyapp.R;
import com.moutamid.surveyapp.helper.BewetungDerFahrtQuestionAdapter;
import com.moutamid.surveyapp.helper.CompleteDialogClass;
import com.moutamid.surveyapp.helper.CompleteQuizDialogClass;
import com.moutamid.surveyapp.helper.NonSwipeableViewPager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BewertungDerFahrtActivity extends AppCompatActivity {

    private NonSwipeableViewPager viewPager;
    private BewetungDerFahrtQuestionAdapter adapter;
    private List<RendomQuestionModelSlider> selectedAnswers = new ArrayList<>();
    int page_position = 0;
    DatabaseReference databaseReference;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung_der_fahrt);

        viewPager = findViewById(R.id.viewPager);
        List<RendomQuestionModelSlider> questionList = generateDynamicQuestionList();
        adapter = new BewetungDerFahrtQuestionAdapter(questionList, this);
        viewPager.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("SurveyResponses").child("BewertungDerFahrt");
        key = databaseReference.push().getKey();
        viewPager.setActivated(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                page_position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    validateAllQuestions();
                }
            }
        });        findViewById(R.id.submitButton).setOnClickListener(view -> {
            if (page_position == adapter.getCount() - 1) {
                // Last question, save data and move to another screen
                saveDataAndMoveToNextScreen();
            } else {
                // Not the last question, move to the next question
                viewPager.setCurrentItem(page_position + 1);
            }
        });    }

    private void saveDataAndMoveToNextScreen() {
        for (int i = 0; i < adapter.getCount(); i++) {
            RendomQuestionModelSlider question = adapter.getItem(i);
            Map<String, Object> questionData = new HashMap<>();
            questionData.put("Fragetext", question.getFragetext());
            questionData.put("AusgewählterOptionstext", question.getSelectedOptionIndex());
            int finalI = i;
            databaseReference.child(Stash.getString("device_id") + key + "___" + Stash.getString("name")).push().setValue(questionData)
                    .addOnSuccessListener(aVoid -> {
                        if (finalI == adapter.getCount() - 1) {
                            BewetungDerFahrtQuestionAdapter.progress_main = 0;
                            CompleteQuizDialogClass cdd = new CompleteQuizDialogClass(BewertungDerFahrtActivity.this);
                            cdd.show();
                            saveDataToCSV(adapter.getQuestions());
                        }
                    })
                    .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
        }
    }

    private boolean validateAllQuestions() {

//        for (RendomQuestionModelSlider question : adapter.getQuestions()) {
//            float sliderValue = question.getSliderValue();

        Map<String, Object> questionData = new HashMap<>();
        questionData.put("Fragetext", BewetungDerFahrtQuestionAdapter.question_main);
        questionData.put("AusgewählterOptionstext", BewetungDerFahrtQuestionAdapter.progress_main + "");

        databaseReference.child(Stash.getString("device_id") + key + "___" + Stash.getString("name")).push().setValue(questionData)
                .addOnSuccessListener(aVoid ->

                        BewetungDerFahrtQuestionAdapter.progress_main = 0)
                .addOnFailureListener(e -> Log.w("RealtimeDatabase", "Error adding data", e));
//            }


        return true;
    }

    private List<RendomQuestionModelSlider> generateDynamicQuestionList() {
        List<RendomQuestionModelSlider> questions = new ArrayList<>();

        List<String> options1 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie hat das System Ihren subjektiv empfundenen Fahrkomfort verändert?", options1, 0, -1.0f));

        List<String> options2 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie hat das System insgesamt/ bei Gegenverkehr Ihre subjektiv empfundene Fahrsicherheit verändert?", options2, 0, -1.0f));
        List<String> options3 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie bewerten Sie die Unterstützung beim Einhalten der Fahrspurin der Kurve/ während der Geradeausfahrt?", options3, 0, -1.0f));
        List<String> options4 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie bewerten Sie die Intensität der Lenkeingriffe?", options4, 0, -1.0f));
        List<String> options5 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie gut konnten Sie die Lenkeingriffe des Systems vorhersehen?", options5, 0, -1.0f));
        List<String> options6 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie bewerten Sie Ihren manuellen Korrekturaufwand nach den Eingriffen des Systems?", options6, 0, -1.0f));
        List<String> options7 = Arrays.asList(
                "sehr negativ",
                "negativ",
                "keinen Einfluss",
                "positiv",
                "sehr positiv"
        );
        questions.add(new RendomQuestionModelSlider("Wie bewerten Sie die Unterstützung bei der Fahrzeugführung insgesamt?", options7, 0, -1.0f));


        return questions;
    }
    private void saveDataToCSV(List<RendomQuestionModelSlider> questions) {
        String filename = "survey_data.csv";
        String title = "\nBewertungDerFahrt\n\n";
        String csvHeader = "Question Number,Fragetext,AusgewählterOptionstext\n";

        File csvFile = new File(getExternalFilesDir(null), filename);

        try {
            FileWriter writer = new FileWriter(csvFile, true); // Open the file in append mode
            writer.append(title);
            writer.append(csvHeader);yy

            for (int i = 0; i < questions.size(); i++) {
                RendomQuestionModelSlider question = questions.get(i);
                String questionText = question.getFragetext().replace("\"", "\"\""); // Escape double quotes
                writer.append(String.valueOf(i + 1)).append(",\"") // Append question number and start quote
                        .append(questionText).append("\",\"") // Append question text and start quote
                        .append(question.getSelectedOptionIndex() + "").append("\"") // Append selected option
                        .append("\n");
            }

            writer.flush();
            writer.close();
            Toast.makeText(getApplicationContext(), "Data saved to CSV file", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("CSV", "Error writing CSV file: " + e.getMessage());
        }
    }

}

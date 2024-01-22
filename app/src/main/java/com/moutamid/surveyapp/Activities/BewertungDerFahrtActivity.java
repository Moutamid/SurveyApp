package com.moutamid.surveyapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.fxn.stash.Stash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moutamid.surveyapp.Model.RendomQuestionModelSlider;
import com.moutamid.surveyapp.R;
import com.moutamid.surveyapp.helper.BewetungDerFahrtQuestionAdapter;
import com.moutamid.surveyapp.helper.CompleteDialogClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BewertungDerFahrtActivity extends AppCompatActivity {

    private ViewPager viewPager;
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

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (page_position < position) {
                    page_position = position;
                    validateAllQuestions();
                }
                if (position == 6) {
                    findViewById(R.id.submitButton).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        findViewById(R.id.submitButton).setOnClickListener(view -> submitButtonClick());
    }

    private void submitButtonClick() {

        CompleteDialogClass cdd = new CompleteDialogClass(BewertungDerFahrtActivity.this);
        cdd.show();
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

        // Add more questions as needed...

        return questions;
    }
}

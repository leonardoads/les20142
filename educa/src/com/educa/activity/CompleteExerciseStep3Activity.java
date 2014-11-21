
package com.educa.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.educa.R;
import com.educa.entity.CompleteExercise;
import com.educa.entity.Exercise;
import com.educa.persistence.DataBaseStorage;
import com.educa.validation.Correction;
import com.educa.validation.FieldValidation;
import com.educa.validation.Status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompleteExerciseStep3Activity extends Activity {
    private EditText et_name;
    private ImageButton bt_save, bt_back;
    private ArrayList<CharSequence> exerciseData;
    private String question, word, hiddenIndexes;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_exercise_step3);
        bt_save = (ImageButton) findViewById(R.id.bt_save);
        bt_back = (ImageButton) findViewById(R.id.bt_previous_step);
        et_name = (EditText) findViewById(R.id.et_name);

        Intent i = getIntent();
        exerciseData = i.getCharSequenceArrayListExtra("AnswersStep2Complete");

        bt_save.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    question = exerciseData.get(0).toString();
                    word = exerciseData.get(1).toString().replace(" ", "");
                    hiddenIndexes = exerciseData.get(2).toString();
                    String name = et_name.getText().toString();
                    Date currentDate = new Date();
                    String fDate = new SimpleDateFormat("dd-MM-yyyy").format(currentDate);
                    CompleteExercise exercise = new CompleteExercise(name, DataBaseStorage
                            .getCompleteExerciseTypecode(), fDate, String
                            .valueOf(Status.NEW),
                            String.valueOf(Correction.NOT_RATED), question,
                            word,
                            hiddenIndexes);

                    // exercise.getStatus().setStatus(getApplicationContext().getResources().getString(R.string.status_new));
                    // exercise.getCorrection().setCorrection(getApplicationContext().getResources().getString(R.string.correction_not_rated));

                    if (exerciseNameAlreadyExists(exercise)) {

                        // ExerciseStorage.getListExercise().add(exercise);
                        MainActivity.teacherDataBaseHelper.addExercise(exercise);

                        Intent intent = new Intent(CompleteExerciseStep3Activity.this,
                                TeacherHomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                getApplicationContext().getResources().getString(
                                        R.string.exercise_name_already_exists), Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }
        });

        bt_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
            case R.id.help:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private boolean checkValidation() {
        boolean ret = true;
        FieldValidation validation = new FieldValidation(this);
        if (!validation.hasText(et_name)) {
            ret = false;
        }
        return ret;
    }

    private boolean exerciseNameAlreadyExists(Exercise exercise) {
        List<Exercise> exercises = MainActivity.teacherDataBaseHelper.getExercises();

        for (Exercise exerciseOnStorage : exercises) {
            if (exerciseOnStorage.getName().equals(exercise.getName())) {
                return false;
            }
        }
        return true;
    }
}

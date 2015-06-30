
package com.educaTio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import com.educaTio.R;

import java.util.ArrayList;
import java.util.List;

public class MultipleCorrectChoiceExerciseStep2Activity extends Activity {
    private RadioGroup radioGroup;
    private List<CharSequence> answersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplechoice_exercise_step2);
        ImageButton bt_ok = (ImageButton) findViewById(R.id.confirm);
        ImageButton bt_back = (ImageButton) findViewById(R.id.bt_previous_step);

        TextView tv_question = (TextView) findViewById(R.id.tv_question);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton answer1 = (RadioButton) findViewById(R.id.rb_answer1);
        RadioButton answer2 = (RadioButton) findViewById(R.id.rb_answer2);
        RadioButton answer3 = (RadioButton) findViewById(R.id.rb_answer3);
        RadioButton answer4 = (RadioButton) findViewById(R.id.rb_answer4);

        Intent i = getIntent();
        answersList = i.getCharSequenceArrayListExtra("AnswersStep1Match");

        tv_question.setText(answersList.get(0));
        answer1.setText(answersList.get(1));
        answer2.setText(answersList.get(2));
        answer3.setText(answersList.get(3));
        answer4.setText(answersList.get(4));

        bt_ok.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int id = radioGroup.getCheckedRadioButtonId();

                if (id != -1) {
                    View radioButton = radioGroup.findViewById(id);
                    int radioId = radioGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);
                    String rightAnswer = (String) btn.getText();

                    answersList.add(rightAnswer);
                    Intent intent = new Intent(MultipleCorrectChoiceExerciseStep2Activity.this,
                            MultipleCorrectChoiceExerciseStep3Activity.class);
                    intent.putCharSequenceArrayListExtra("AnswersStep2Match", (ArrayList<CharSequence>) answersList);

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getApplicationContext().getString(R.string.choose_the_right_answer),
                            Toast.LENGTH_SHORT).show();

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
    public void onBackPressed() {
        super.onBackPressed();
    }

}
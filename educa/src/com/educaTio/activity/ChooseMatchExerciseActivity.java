
package com.educaTio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.educaTio.R;

public class ChooseMatchExerciseActivity extends Activity {
    ImageButton bt_color_match, bt_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_match_exercise_model);
        bt_color_match = (ImageButton) findViewById(R.id.bt_color_match);
        bt_numero = (ImageButton) findViewById(R.id.bt_numero);

        bt_color_match.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ColorMatchExerciseStep1Activity.class);
                startActivity(intent);
            }
        });
        
		bt_numero.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(),
					NumMatchExerciseStep1Activity.class);
			startActivity(intent);
			}
		});
    }

   
}
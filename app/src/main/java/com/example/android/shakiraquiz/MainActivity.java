package com.example.android.shakiraquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        nameEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Button startBtn = (Button) findViewById(R.id.start_button);
                startBtn.setEnabled(true);
            }
        });


    }

    public void startQuiz(View view) {

        RelativeLayout welcomeLayout = (RelativeLayout) findViewById(R.id.welcome_layout);
        welcomeLayout.setVisibility(View.GONE);

        LinearLayout questionLayout = (LinearLayout) findViewById(R.id.questions_layout);
        questionLayout.setVisibility(View.VISIBLE);


        ScrollView scWiew = (ScrollView) findViewById(R.id.scroll_view);
        scWiew.smoothScrollTo(0, questionLayout.getTop());

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(welcomeLayout.getWindowToken(), 0);
    }


    public void endQuiz(View view) {
        int points = calculatePoints();

        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String playerName = nameEditText.getText().toString();
        String messageStart = getMessage(points);
        String message = messageStart + " " + playerName + ". " + getResources().getString(R.string.your_score) + " " +
                points + "/7 " + " " + getResources().getString(R.string.points);
        Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
    }

    public void backToWelcome(View view) {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public int calculatePoints() {
        int points = 0;

        //1. question
        RadioButton rBtnQ1 = (RadioButton) findViewById(R.id.radio4_q1);
        if (rBtnQ1.isChecked())
            points++;

        //2. question
        RadioButton rBtnQ2 = (RadioButton) findViewById(R.id.radio1_q2);
        if (rBtnQ2.isChecked())
            points++;

        //3. question
        RadioButton rBtnQ3 = (RadioButton) findViewById(R.id.radio2_q3);
        if (rBtnQ3.isChecked())
            points++;

        //4. question
        EditText editTextQ4 = (EditText) findViewById(R.id.edit1_q4);
        if (editTextQ4.getText().toString().toLowerCase().equals("isabel"))
            points++;

        //5. question
        CheckBox cB1Q5 = (CheckBox) findViewById(R.id.checkbox1_q5);
        CheckBox cB2Q5 = (CheckBox) findViewById(R.id.checkbox2_q5);
        CheckBox cB3Q5 = (CheckBox) findViewById(R.id.checkbox3_q5);
        CheckBox cB4Q5 = (CheckBox) findViewById(R.id.checkbox4_q5);
        if (cB1Q5.isChecked() && cB2Q5.isChecked() && !cB3Q5.isChecked() && !cB4Q5.isChecked())
            points++;

        //6. question
        EditText editTextQ6 = (EditText) findViewById(R.id.edit1_q6);
        if (editTextQ6.getText().toString().equals("15"))
            points++;

        //7. question
        RadioButton rBtnQ7 = (RadioButton) findViewById(R.id.radio3_q7);
        if (rBtnQ7.isChecked())
            points++;

        return points;
    }

    public String getMessage(int points) {
        StringBuilder sb = new StringBuilder();

        if (points == 0)
            sb.append(getResources().getString(R.string.ouch));
        else if (points < 7)
            sb.append(getResources().getString(R.string.well_done));
        else
            sb.append(getResources().getString(R.string.congrats));

        return sb.toString();
    }

}


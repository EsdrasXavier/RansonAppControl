package com.example.ransonappcontrol;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class NewPatientActivity extends AppCompatActivity {
    private final String TAG = "NewPatientActivity";

    private EditText nameText;
    private EditText ageText;
    private EditText leucocitosText;
    private EditText glicemiaText;
    private EditText astAndTgoText;
    private EditText ldhText;
    private CheckBox isLitiaseBiliar;
    private Button addNewPatient;
    private TextView mPoints;
    private TextView mDeath;

    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_patient_activity);

        nameText = findViewById(R.id.name);
        ageText = findViewById(R.id.age);
        leucocitosText = findViewById(R.id.leu);
        glicemiaText = findViewById(R.id.glic);
        astAndTgoText = findViewById(R.id.ast);
        ldhText = findViewById(R.id.ldh);
        isLitiaseBiliar = findViewById(R.id.isLit);
        addNewPatient = findViewById(R.id.addPat);
        mPoints = findViewById(R.id.points);
        mDeath = findViewById(R.id.mortalidade);

        mPoints.setVisibility(View.INVISIBLE);
        mDeath.setVisibility(View.INVISIBLE);

        addNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = nameText.getText().toString();
                    int age = Integer.parseInt(ageText.getText().toString());
                    double leukocytesNumber = Double.parseDouble(leucocitosText.getText().toString());
                    double bloodGlucoseValue = Double.parseDouble(glicemiaText.getText().toString());
                    double astAndTgoValue = Double.parseDouble(astAndTgoText.getText().toString());
                    double ldhValue = Double.parseDouble(ldhText.getText().toString());
                    boolean hasBiliaryLithiasis = isLitiaseBiliar.isChecked();

                    int points = 0;

                    if (hasBiliaryLithiasis) {
                        if (age > 70) points++;
                        if (leukocytesNumber > 18000) points++;
                        if (bloodGlucoseValue > 12.2) points++;
                        if (astAndTgoValue > 250) points++;
                        if (ldhValue > 400) points++;
                    } else {
                        if (age > 55) points++;
                        if (leukocytesNumber > 16000) points++;
                        if (bloodGlucoseValue > 11) points++;
                        if (astAndTgoValue > 250) points++;
                        if (ldhValue > 350) points++;
                    }

                    double death = 0;
                    if (0 <= points && points <= 2) death = 2;
                    else if (3 <= points && points <= 4) death = 15;
                    else if (5 <= points && points <= 6) death = 40;
                    else if (7 <= points && points <= 8) death = 100;

                    showHideInfo(true);

                    mPoints.setText("Pontuação: " + points);
                    mDeath.setText("Mortalidade: " + death + "%");

                    Patient patient = new Patient(name, age, leukocytesNumber, bloodGlucoseValue, astAndTgoValue, ldhValue, hasBiliaryLithiasis, points, death);
                    addPatient(patient);

                    Log.i(TAG, "" + name + " - " + age + " - " +
                    leukocytesNumber + " - " +
                    bloodGlucoseValue + " - " +
                    astAndTgoValue + " - " +
                    ldhValue  + " - " +
                    hasBiliaryLithiasis + " - " +
                    points + " - " +
                    death);
                } catch (Exception e) {
                    showError("Houve um erro ao adicionar novo paciente", 4);
                    Log.i(TAG, "Ouve um erro ao adicionar novo paciente: " + e);
                }
            }
        });
    }

    private void showHideInfo(boolean s) {
        if (s) {
            mPoints.setVisibility(View.VISIBLE);
            mDeath.setVisibility(View.VISIBLE);
        } else {
            mPoints.setVisibility(View.INVISIBLE);
            mDeath.setVisibility(View.INVISIBLE);
        }
    }

    private void addPatient(Patient p) {
        try {
            DAL dal = new DAL(this);
            dal.insert(p);
        } catch (Exception e) {
            Log.i(TAG, "Error on patient insert: " + e);
            showError("Erro ao inserir paciente", 3);
        }
    }
    private void showError(String err, int time) {
        Toast.makeText(this, err, time).show();
    }
}

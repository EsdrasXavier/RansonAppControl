package com.example.ransonappcontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ArrayAdapter<String> patientArrayAdapter;
    private ListView mListView;
    private Cursor cursor;
    private ArrayList<String> patients = new ArrayList<String>();
    private Runnable run;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        run = new Runnable() {
            public void run() {
                //reload content
                loadPatients();
                patientArrayAdapter.notifyDataSetChanged();
                mListView.invalidateViews();
                mListView.refreshDrawableState();
            }
        };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), NewPatientActivity.class);;
                startActivityForResult(mIntent, 0);
            }
        });

        mListView = findViewById(R.id.listView);


        patientArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patients);
        mListView.setAdapter(patientArrayAdapter);

        if (hasPatients()) {
            loadPatients();
        } else {
            Toast.makeText(this, "Sem pacientes no banco de dados", 4).show();
        }
    }


    private boolean hasPatients() {
        DAL dal = new DAL(this);
        cursor = dal.loadAll();
        if (cursor == null) {
            return false;
        } else if (cursor.getCount() == 0) {
            return false;
        }

        return true;
    }

    private void loadPatients() {
        DAL dal = new DAL(this);
        cursor = dal.loadAll();

        if (cursor == null) {
            return ;
        }

        patients.clear();

        while (!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndex(CreateDatabase.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(CreateDatabase.AGE));
            double death = cursor.getDouble(cursor.getColumnIndex(CreateDatabase.DEATH));
            patients.add("Nome: " + name + "\nIdade: " + age + "\nMortalidade: "+death+"%");
            Log.i(TAG, "Nome: " + name + "\nIdade: " + age + "\nMortalidade: "+death+"%");
            cursor.moveToNext();
        }

        Log.i(TAG, "Número de registros: " + cursor.getCount());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        runOnUiThread(run);
        Log.i(TAG, "New patients add.");
    }
}

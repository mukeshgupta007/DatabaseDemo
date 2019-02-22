package com.example.databasedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInsert;
    Button btnDelete;
    Button btnSelect;
    Button btnSearch;
    EditText editName;
    EditText editAge;
    EditText editDelete;
    EditText editSearch;
    // This is our DataManager instance
    DataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DataManager(this);
        // get a reference to the UI item
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);
        btnSearch = findViewById(R.id.btnSearch);
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editDelete = findViewById(R.id.editDelete);
        editSearch = findViewById(R.id.editSearch);
        // Register MainActivity as a listener
        btnSelect.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    // Output the cursor contents to the log
    public void showData(Cursor c) {
        while (c.moveToNext()) {
            Log.i(c.getString(1), c.getString(2));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInsert:
                dm.insert(editName.getText().toString(), editAge.getText().toString());
                break;
            case R.id.btnSelect:
                showData(dm.selectAll());
                break;
            case R.id.btnSearch:
                showData(dm.searchName(editSearch.getText().toString()));
                break;
            case R.id.btnDelete:
                dm.delete(editDelete.getText().toString());
                break;
        }
    }
}

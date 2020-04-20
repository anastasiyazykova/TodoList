package nsu.android.todolist.View;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Presenter.Presenter;
import nsu.android.todolist.R;

public class NotesList extends AppCompatActivity {

    Presenter presenter;

    private SQLiteStorage sqliteStorage;

    private FloatingActionButton createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        presenter = new Presenter("NotesListPresenter");

        sqliteStorage = new SQLiteStorage(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        createButton = findViewById(R.id.create_fab);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesList.this, CreateNoteActivity.class);
                startActivity(intent);
            }
        });
    }
}

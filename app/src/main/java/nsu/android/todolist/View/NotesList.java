package nsu.android.todolist.View;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import nsu.android.todolist.Presenter.PresenterList;
import nsu.android.todolist.R;

public class NotesList extends AppCompatActivity {

    PresenterList presenter;

    private FloatingActionButton createButton;
    //private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);
        //ItemActivity.onCreate(savedInstanceState);

        createButton = findViewById(R.id.create_button);
        //deleteButton = findViewById(R.id.delete_button);

        presenter = new PresenterList(this);
        presenter.onCreateEvent();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newNoteEvent();
                //presenter.recreateEvent();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.recreateEvent();
    }

    public void changedTask() {

    }
}

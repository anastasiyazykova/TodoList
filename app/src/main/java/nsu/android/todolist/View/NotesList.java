package nsu.android.todolist.View;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import nsu.android.todolist.MyAdapter;
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

        /*deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.deleteEvent();
            }
        });*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.recreateEvent();
    }
}

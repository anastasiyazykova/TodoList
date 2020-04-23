package nsu.android.todolist.Presenter;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Model.Task;
import nsu.android.todolist.MyAdapter;
import nsu.android.todolist.R;
import nsu.android.todolist.View.CreateNoteActivity;
import nsu.android.todolist.View.NotesList;

public class PresenterList {

    private NotesList notesList;
    private SQLiteStorage sqliteStorage;

    private RecyclerView taskList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public PresenterList(NotesList notesList) {
        this.notesList = notesList;
        setModel();
    }

    public void setModel() {
        sqliteStorage = new SQLiteStorage(notesList);
    }

    public void newNoteEvent() {
        Intent intent = new Intent(notesList, CreateNoteActivity.class);
        notesList.startActivity(intent);
    }


    public void onCreateEvent() {
        taskList = notesList.findViewById(R.id.notes_recyc);

        layoutManager = new LinearLayoutManager(notesList);
        taskList.setLayoutManager(layoutManager);

        List<Task> tasks = sqliteStorage.getAllTasks();

        CoordinatorLayout c = notesList.findViewById(R.id.coordinator);
        List<CoordinatorLayout> myDataset = new ArrayList<>();

        for (Task t : tasks) {
            TextView item_task_name =  notesList.findViewById(R.id.item_task_name);
            TextView item_short_text = notesList.findViewById(R.id.item_short_text);
            TextView item_full_text = notesList.findViewById(R.id.item_full_text);
            TextView item_create_data = notesList.findViewById(R.id.item_create_data);
            TextView item_change_data = notesList.findViewById(R.id.item_change_data);
            CheckBox is_done = notesList.findViewById(R.id.is_done);

            item_task_name.setText(t.getName());
            item_short_text.setText(t.getShortText());
            item_full_text.setText(t.getFullText());
            item_create_data.setText(t.getDateCreate());
            item_change_data.setText(t.getDateChange());

            if (t.getIsDone()) {
                is_done.setChecked(true);
            } else {
                is_done.setChecked(false);
            }

            myDataset.add(c);
        }

        mAdapter = new MyAdapter(myDataset);
        taskList.setAdapter(mAdapter);
    }
}

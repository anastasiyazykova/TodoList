package nsu.android.todolist.Presenter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Model.Task;
import nsu.android.todolist.R;
import nsu.android.todolist.View.CreateNoteActivity;
import nsu.android.todolist.View.NotesList;

public class PresenterList {

    private NotesList notesList;
    private SQLiteStorage sqliteStorage;

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
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
        recyclerView = notesList.findViewById(R.id.notes_recyc);
        layoutManager = new LinearLayoutManager(notesList);
        recyclerView.setLayoutManager(layoutManager);
        List<Task> tasks = sqliteStorage.getAllTasks();

        mAdapter = new MyAdapter(tasks, notesList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setPresenter(this);
    }

    public void recreateEvent() {
        onCreateEvent();
    }

    public void changeBox(boolean b, String name) {
        List<Task> tasks = sqliteStorage.getAllTasks();
        Task task = null;
        for (Task t : tasks) {
            if (name.equals(t.getName())) {
                task = t;
            }
        }

        if (b) {
            task.setIsDone("true");
        } else {
            task.setIsDone("false");
        }

        sqliteStorage.updateTask(name, task);
        recreateEvent();
    }
}

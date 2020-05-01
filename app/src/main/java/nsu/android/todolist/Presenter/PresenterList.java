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
    //private RecyclerView recyclerViewDone;

    private MultipleMyAdapter mAdapter;
    //private MyAdapter mAdapterDone;

    private RecyclerView.LayoutManager layoutManager;
    //private RecyclerView.LayoutManager layoutManagerDone;


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
        //recyclerViewDone = notesList.findViewById(R.id.notes_recyc_done);

        layoutManager = new LinearLayoutManager(notesList);
        recyclerView.setLayoutManager(layoutManager);
        //layoutManagerDone = new LinearLayoutManager(notesList);
        //recyclerViewDone.setLayoutManager(layoutManagerDone);

        List<Task> tasks = sqliteStorage.getAllTasks();

        mAdapter = new MultipleMyAdapter(tasks, notesList);
        //mAdapterDone = new MyAdapter(tasks, notesList, true);

        recyclerView.setAdapter(mAdapter);
        //recyclerViewDone.setAdapter(mAdapterDone);

        mAdapter.setPresenter(this);
        //mAdapterDone.setPresenter(this);
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

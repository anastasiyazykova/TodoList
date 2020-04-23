package nsu.android.todolist.Presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Model.Task;
import nsu.android.todolist.View.CreateNoteActivity;
import nsu.android.todolist.View.NotesList;

public class PresenterCreate {

    private CreateNoteActivity createNoteActivity;
    private SQLiteStorage sqliteStorage;

    public PresenterCreate(CreateNoteActivity createNoteActivity) {
        this.createNoteActivity = createNoteActivity;
        setModel();
    }

    public void newNoteEvent(String n, String s, String f) {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        sqliteStorage.addTask(new Task(dateText, dateText, n, s, f, false));

        createNoteActivity.onBackPressed();
    }

    public void setModel() {
        sqliteStorage = new SQLiteStorage(createNoteActivity);
    }
}

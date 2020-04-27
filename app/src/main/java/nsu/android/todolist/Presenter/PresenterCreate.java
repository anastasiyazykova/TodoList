package nsu.android.todolist.Presenter;

import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Model.Schema;
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
        List<Task> tasks = sqliteStorage.getAllTasks();

        boolean flag = false;
        for (Task t : tasks) {
            if (t.getName().equals(n)) {
                Toast toast = Toast.makeText(createNoteActivity.getApplicationContext(),
                        "Такое имя уже есть, придумайте другое!", Toast.LENGTH_SHORT);
                toast.show();
                flag = true;
            }
        }

        if (!flag) {
            sqliteStorage.addTask(new Task(dateText, dateText, n, s, f, false));
        }

        createNoteActivity.onBackPressed();
    }

    public void setModel() {
        sqliteStorage = new SQLiteStorage(createNoteActivity);
    }

    public void setData(Bundle b) {
        createNoteActivity.name.setText(b.getString("name"));
        createNoteActivity.shortText.setText(b.getString("shortText"));
        createNoteActivity.fullText.setText(b.getString("fullText"));
        createNoteActivity.oldName = createNoteActivity.name.getText().toString();
        createNoteActivity.oldDate = b.getString("oldDate");
    }

    public void changeEvent(String n, String s, String f) {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        List<Task> tasks = sqliteStorage.getAllTasks();
        Task myTask = null;

        for (Task t : tasks) {
            if (t.getName().equals(createNoteActivity.oldName)) {
                myTask = t;
            }
        }

        if (myTask != null) {
            myTask.setName(n);
            myTask.setShortText(s);
            myTask.setFullText(f);
            myTask.setChangeData(dateText);
        }

        sqliteStorage.updateTask(createNoteActivity.oldName, myTask);

        createNoteActivity.onBackPressed();
    }

}
